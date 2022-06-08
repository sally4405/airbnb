//
//  ImageDataCacheManager.swift
//  airbnb
//
//  Created by Bibi on 2022/06/08.
//

import Foundation
import UIKit
import RxSwift

public class ImageDataCacheManager {
    
    public static let publicCacheManager = ImageDataCacheManager() // 싱글톤?
    // 사용 시 ImageCache.publicCacheManager.load()와 같이 사용..
    
    // URL에 대한 이미지를 저장하는 캐시 프로퍼티
    private let cachedImages = NSCache<NSURL, UIImage>()
    // key값으로 클래스를 요구하므로 URL대신 NSURL을, Data 대신 UIImage를 사용
    
    // 캐시에 이미지가 없는 경우, urlSession을 통해 이미지를 얻어오기 위해 response를 받은 후 결과값을 전달받기 위해 선언한 딕셔너리
    private var loadingResponses = [NSURL: [(Data?) -> Swift.Void]]()
    
// URL을 인수로 받아 캐시된 이미지를 획득하는 메서드
    private final func getCachedImage(url: NSURL) -> UIImage? {
        return cachedImages.object(forKey: url)
    }
    
// 이미지가 있다면 캐싱된 이미지를 반환하고, 그렇지 않으면 비동기적으로 이미지를 로드 및 캐시합니다.
// image(url:)의 결과로 이미지가 있다면 그 이미지를 completion에 전달, 없으면 URLSession을 통해 받아와서 completion에 전달
    final func loadImage(url: NSURL, completion: @escaping (Data?) -> Swift.Void) {
        // 해당 이미지가 캐시되어 있다면 찾은 이미지를 반환합니다.
        if let cachedImage = getCachedImage(url: url) {
            DispatchQueue.main.async { // 왜 비동기?
                completion(cachedImage.pngData())
            }
            return
        }
        
        if loadingResponses[url] != nil { // 해당 이미지에 대해 둘 이상의 요청이 있는 경우, loadingResponses에 completion 블록을 추가합니다.
            loadingResponses[url]?.append(completion)
            return
        } else { // 해당 이미지에 대해 첫 요청인 경우 loadingResponses에 completion 등록
            loadingResponses[url] = [completion]
        }
        
        // (URL로부터) 이미지를 받아옵니다.
        // URLSession(.ephimeral) : NSCache를 따로 사용하므로 URLSession의 캐시를 사용하지 않기 위한 설정
        let urlSession = URLSession(configuration: URLSessionConfiguration.ephemeral)
        urlSession.dataTask(with: url as URL) { (data, response, error) in
            // error와 data를 체크하고 이미지 생성을 시도합니다.
            guard let responseData = data,
                  let image = UIImage(data: responseData),
                  let responseBlocks = self.loadingResponses[url],
                  error == nil else { // 넷 중에 하나라도 nil이면 completion으로 nil을 전달하며 리턴
                DispatchQueue.main.async {
                    completion(nil)
                }
                return
            }
            // 받아온 이미지를 캐시에 저장합니다.
            self.cachedImages.setObject(image, forKey: url, cost: responseData.count)
            // 해당 이미지의 요청들에 대해 반복해서 이미지를 전달합니다.
            for block in responseBlocks {
                DispatchQueue.main.async {
                    block(image.pngData())
                }
                return
            }
        }.resume()
    }
    
    final func loadImageRx(url: NSURL) -> Observable<Data?> {
        return Observable.create { emitter in
            self.loadImage(url: url) { uiImage in
                emitter.onNext(uiImage)
                emitter.onCompleted()
            }
            return Disposables.create()
        }
    }
}

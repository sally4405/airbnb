//
//  SearchViewModel.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import Foundation
import RxSwift
import RxRelay

class SearchViewModel {
    
    private let titleText: String = "슬기로운 \n자연생활"
    private let contentText: String = "에어비앤비가 엄선한 \n위시리스트를 만나보세요."
    private let buttonText: String = "여행 아이디어 얻기"
    
    private var model: HeroImageDTO? = nil // 아니 이생각을 왜 못,.흑흑
    
    //바인딩할만큼 중요한 정보가 아닌?듯? API에서 한번 받아서 뿌려주면 끝인데..
//    private lazy var modelRelay = BehaviorRelay<HeroImage?>(value: model)
    // 여기서 BehaviorRelay<Model> 이것만 써서 바인딩을 해보자.
    // 네트워크로 가져온 값을 릴레이에 넣고
    // 릴레이를 뷰에서 바인딩해 업데이트된 값을 갱신
    // **input/output 구분 말고 일단 바인딩을 해보자.
    
    private let networkManager = NetworkManager.publicNetworkManager
    private let imageCacheManager = ImageCacheManager.publicCacheManager
    private let imageDataCacheManager = ImageDataCacheManager.publicCacheManager
    
    var heroImageData = PublishSubject<Data>()
    
    init() {
        getHeroImageURL().subscribe(onNext: { url in
            self.getHeroImageData(imageURL: url).subscribe(onNext: { data in
                self.heroImageData.onNext(data)
            })
        })
        
    }
    
    func getHeroImageURL() -> Observable<String> {
        return Observable.create { emitter in
            self.networkManager.getHeroImage { heroImage in
                guard let image = heroImage else {
                    return
                }
//                self.model = HeroImage(imageURL: image.imageURL)
                emitter.onNext(image.imageURL)
                emitter.onCompleted()
            }
            return Disposables.create()
        }
    }
    
    func getHeroImageData(imageURL: String) -> Observable<Data> {
        return Observable.create { emitter in
            if let url = URL(string: imageURL) {
                self.imageDataCacheManager.loadImage(url: url as NSURL) { imageData in
                    if let data = imageData {
                        emitter.onNext(data)
                        emitter.onCompleted()
                    }
                }
            }
            return Disposables.create()
        }
    }
}

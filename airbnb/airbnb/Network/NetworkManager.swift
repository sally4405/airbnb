//
//  NetworkManager.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import Foundation
import RxSwift

// HTTPManager와 JSONConverter를 사용해 HTTP요청을 보내고, 그 결과를 관리하는 클래스
final class NetworkManager {
    
    public static let publicNetworkManager = NetworkManager()
    
    static let identifier = "NetworkManager"
    
    private let baseURL = "http://louie-03.com"
    
    func getHeroImage(completion: @escaping (HeroImage?) -> Void) { // escaping 클로저 : 리턴타입처럼 사용
        let urlStub = "/banners/main"
        HTTPManager.requestGET(url: baseURL + urlStub) { data in
            // get요청의 CompletionHandler로 JSON Decoder를 보냄 : 응답 정보를 Swift객체로 변환하기 위해
            // 응답 정보가 단일객체이면 decodeJson()을, 배열이면 decodeJsonArray()를 사용
            guard let data: HeroImage = JSONConverter.decodeJson(data: data) else {
                return
            }
            completion(data)
        }
    }
    
    func getHeroImageRx() -> Observable<HeroImage?> {
        return Observable.create { emitter in
            self.getHeroImage { heroImage in
                emitter.onNext(heroImage)
                emitter.onCompleted()
            }
            return Disposables.create()
        }
    }
    
    func getPopularLocation(completion: @escaping ([PopularLocationElement]) -> Void) {
        let urlStub = "/travels/popular"
        HTTPManager.requestGET(url: baseURL + urlStub) { data in
            guard let data: [PopularLocationElement] = JSONConverter.decodeJsonArray(data: data) else {
                return
            }
            completion(data)
        }
    }
    
    func getPopularLoactaionRx() -> Observable<[PopularLocationElement]?> {
        return Observable.create { emitter in
            self.getPopularLocation { location in
                emitter.onNext(location)
                emitter.onCompleted()
            }
            return Disposables.create()
        }
    }
}

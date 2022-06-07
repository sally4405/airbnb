//
//  SearchViewModel.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import Foundation
import RxSwift

class SearchViewModel {
    
    private var model = HeroImage(imageURL: "")
    
    private let networkManager = NetworkManager.publicNetworkManager
    
    func getHeroImageURL() -> Observable<String> {
        return Observable.create { emitter in
            self.networkManager.getHeroImage { heroImage in
                guard let image = heroImage else {
                    return
                }
                self.model = HeroImage(imageURL: image.imageURL)
                emitter.onNext(image.imageURL)
                emitter.onCompleted()
            }
            return Disposables.create()
        }
    }
}

//
//  PopularLocationViewModel.swift
//  airbnb
//
//  Created by Bibi on 2022/06/07.
//

import Foundation
import RxSwift

class PopularLocationViewModel {
    
    let popularLocation = PublishSubject<[PopularLocationElement]>()
    private let networkManager = NetworkManager.publicNetworkManager
    
    init() {
        getPopularLocation().subscribe(onNext: {
            self.popularLocation.onNext($0)
        })
    }
    
    func getPopularLocation() -> Observable<[PopularLocationElement]> {
        return Observable.create { emitter in
            self.networkManager.getPopularLocation { location in
                emitter.onNext(location)
                emitter.onCompleted()
            }
            return Disposables.create()
        }
    }

}

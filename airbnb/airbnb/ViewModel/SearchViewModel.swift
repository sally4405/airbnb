//
//  SearchViewModel.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import Foundation
import UIKit

class SearchViewModel { // 모델을 통해 받아온 데이터를 가공
    
    private(set) var heroImage = UIImage()
    private(set) var titleText: String = "슬기로운 \n자연생활"
    private(set) var contentText: String = "에어비앤비가 엄선한 \n위시리스트를 만나보세요."
    private(set) var buttonText: String = "여행 아이디어 얻기"
    
    private let networkManager = NetworkManager.publicNetworkManager
    private let imageCacheManager = ImageCacheManager.publicCacheManager
    
    init() {}
    
    private func loadHeroImage(completion: @escaping (UIImage?) -> Void) {
        networkManager.getHeroImage { heroImage in
            guard let image = heroImage,
                  let heroImageURL = URL(string: image.imageURL) else {
                return
            }
            let heroImageItem = ImageItem(image: nil, url: heroImageURL)
            self.imageCacheManager.loadImage(url: heroImageURL as NSURL, imageItem: heroImageItem) { imageItem, uiImage in
                if let uiImage = uiImage {
                    print("이미지 : \(uiImage)")
                    self.heroImage = uiImage
                    completion(uiImage)
                }
            }
        }
    }
}

extension SearchViewModel {
    func configure(_ view: SearchView) {
        loadHeroImage(completion: { image in
            guard let image = image else {
                return
            }
            view.setHeroImage(image)
        })
    }
}

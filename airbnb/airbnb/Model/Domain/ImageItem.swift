//
//  ImageItem.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import Foundation
import UIKit

class ImageItem { // struct?
    
    var image: UIImage? = nil // 이미지
    let url: URL // 이미지URL
    let identifier = UUID() // 이미지의 ID
    
    init(image: UIImage? = nil, url: URL) {
        self.image = image
        self.url = url
    }
}

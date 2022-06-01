//
//  UIImageExtension.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import UIKit

extension UIImage {
    func scalePreservingAspectRatio(targetSize: CGSize) -> UIImage {
        // Determine the scale factor that preserves aspect ratio
        let widthRatio = targetSize.width / size.width
        let heightRatio = targetSize.height / size.height
        
        let scaleFactor = min(widthRatio, heightRatio)
        
        // Compute the new image size that preserves aspect ratio
        let scaledImageSize = CGSize(
            width: size.width * scaleFactor,
            height: size.height * scaleFactor
        )
        
        // Draw and return the resized UIImage
        let renderer = UIGraphicsImageRenderer(
            size: scaledImageSize
        )
        
        let scaledImage = renderer.image { _ in
            self.draw(in: CGRect(
                origin: .zero,
                size: scaledImageSize
            ))
        }
        
        return scaledImage
    }
    
    func scalePreservingAspectRatio(targetWidth: CGFloat) -> UIImage {
        // Determine the scale factor that preserves aspect ratio
        let widthRatio = targetWidth / size.width
        // Compute the new image size that preserves aspect ratio
        let scaledImageSize = CGSize(
            width: size.width * widthRatio,
            height: size.height * widthRatio
        )
        // Draw and return the resized UIImage
        let renderer = UIGraphicsImageRenderer(
            size: scaledImageSize
        )
        let scaledImage = renderer.image { _ in
            self.draw(in: CGRect(
                origin: .zero,
                size: scaledImageSize
            ))
        }
        return scaledImage
    }
    
    func scalePreservingAspectRatio(targetHeight: CGFloat) -> UIImage {
        // Determine the scale factor that preserves aspect ratio
        let heightRatio = targetHeight / size.height
        // Compute the new image size that preserves aspect ratio
        let scaledImageSize = CGSize(
            width: size.width * heightRatio,
            height: size.height * heightRatio
        )
        // Draw and return the resized UIImage
        let renderer = UIGraphicsImageRenderer(
            size: scaledImageSize
        )
        let scaledImage = renderer.image { _ in
            self.draw(in: CGRect(
                origin: .zero,
                size: scaledImageSize
            ))
        }
        return scaledImage
    }
}


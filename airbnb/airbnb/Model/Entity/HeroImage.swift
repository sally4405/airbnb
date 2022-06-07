import Foundation

// GET /banners/main
// MARK: - HeroImage
struct HeroImage: Codable {
    let imageURL: String

    enum CodingKeys: String, CodingKey {
        case imageURL = "imageUrl"
    }
    
//    func toDTO() -> HeroImageDTO {
//        return HeroImageDTO(imageURL: self.imageURL)
//    }
}

//struct HeroImageDTO { // 뷰에 필요한 데이터
//    let imageURL: String
//    let titleText: String = "슬기로운 \n자연생활"
//    let contentText: String = "에어비앤비가 엄선한 \n위시리스트를 만나보세요."
//    let buttonText: String = "여행 아이디어 얻기"
//}

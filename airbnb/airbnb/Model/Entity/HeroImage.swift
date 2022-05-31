import Foundation

// GET /banners/main
// MARK: - HeroImage
struct HeroImage: Codable {
    let imageURL: String

    enum CodingKeys: String, CodingKey {
        case imageURL = "imageUrl"
    }
}

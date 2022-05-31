import Foundation

// GET /regions/search?country=양재동
typealias SearchRegion = [SearchRegionElement]

// MARK: - SearchRegionElement
struct SearchRegionElement: Codable {
    let country, imageURL: String

    enum CodingKeys: String, CodingKey {
        case country
        case imageURL = "imageUrl"
    }
}



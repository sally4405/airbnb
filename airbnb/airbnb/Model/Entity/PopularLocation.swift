import Foundation

// GET /travels/popular
typealias PopularLocation = [PopularLocationElement]

// MARK: - PopularLocationElement
struct PopularLocationElement: Codable {
    let name, imageURL, distance: String

    enum CodingKeys: String, CodingKey {
        case name
        case imageURL = "imageUrl"
        case distance
    }
}


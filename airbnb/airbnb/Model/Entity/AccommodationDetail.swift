import Foundation

// GET /accommodations/{id}
// MARK: - AccommodationDetail
struct AccommodationDetail: Codable {
    let id: Int
    let name: String
    let imageURL: String
    let rating: Double
    let reviewCount: Int
    let country, hostName: String
    let hostImageURL: String
    let maxGuestCount: Int
    let roomCount: String
    let bedroomCount, bathroomCount: Int
    let accommodationDetailDescription: String
    let price: Int
    let wishlist: Bool

    enum CodingKeys: String, CodingKey {
        case id, name
        case imageURL = "imageUrl"
        case rating, reviewCount, country, hostName
        case hostImageURL = "hostImageUrl"
        case maxGuestCount, roomCount, bedroomCount, bathroomCount
        case accommodationDetailDescription = "description"
        case price, wishlist
    }
}

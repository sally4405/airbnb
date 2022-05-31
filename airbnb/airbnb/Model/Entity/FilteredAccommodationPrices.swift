import Foundation

//  GET /accommodations?country=양재동&checkIn=2022-05-17&checkOut=2022-06-12&minPrice=10000&maxPrice=100000&guestCount=3
// MARK: - FilteredAccommodationPrices
struct FilteredAccommodationPrices: Codable {
    let count: Int
    let accommodations: [Accommodation]
}

// MARK: - Accommodation
struct Accommodation: Codable {
    let id: Int
    let name: String
    let imageURL: String
    let rating: Double
    let reviewCount, price, totalPrice: Int
    let wishlist: Bool
    let latitude: Double // 위도
    let longitude: Double // 경도

    enum CodingKeys: String, CodingKey {
        case id, name
        case imageURL = "imageUrl"
        case rating, reviewCount, price, totalPrice, wishlist, latitude, longitude
    }
}

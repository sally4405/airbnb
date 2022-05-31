import Foundation

// GET /wishlists
// MARK: - WishListElement
struct WishListElement: Codable {
    let id: Int
    let name: String
    let imageURL: String
    let rating: Double
    let reviewCount, price: Int
    let wishlist: Bool

    enum CodingKeys: String, CodingKey {
        case id, name
        case imageURL = "imageUrl"
        case rating, reviewCount, price, wishlist
    }
}

typealias WishList = [WishListElement]

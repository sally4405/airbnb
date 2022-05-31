import Foundation

// POST /wishlists
// MARK: - WishRequest
struct WishRequest: Codable {
    let memberID, accommodationID: Int

    enum CodingKeys: String, CodingKey {
        case memberID = "memberId"
        case accommodationID = "accommodationId"
    }
}

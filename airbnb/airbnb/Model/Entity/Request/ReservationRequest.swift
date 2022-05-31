import Foundation

// POST /books
// MARK: - ReservationRequest
struct ReservationRequest: Codable {
    let accommodationID: Int
    let checkIn, checkOut: String
    let guestCount: Int

    enum CodingKeys: String, CodingKey {
        case accommodationID = "accommodationId"
        case checkIn, checkOut, guestCount
    }
}

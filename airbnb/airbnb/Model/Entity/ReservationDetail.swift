import Foundation

// GET /books/{id}
// MARK: - ReservationDetail
struct ReservationDetail: Codable {
    let bookID: Int
    let accommodationImageUrls: [String]
    let checkIn, checkOut, accommodationCountry, accommodationName: String
    let guestCount: Int
    let roomCount, hostName: String
    let finalPrice: Int

    enum CodingKeys: String, CodingKey {
        case bookID = "bookId"
        case accommodationImageUrls, checkIn, checkOut, accommodationCountry, accommodationName, guestCount, roomCount, hostName, finalPrice
    }
}

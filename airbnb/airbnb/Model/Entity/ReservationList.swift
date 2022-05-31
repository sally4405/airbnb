import Foundation

// GET /books
// MARK: - ReservationListElement
struct ReservationListElement: Codable {
    let bookID: Int
    let accommodationImageURL: String
    let checkIn, checkOut, accommodationCountry, accommodationName: String

    enum CodingKeys: String, CodingKey {
        case bookID = "bookId"
        case accommodationImageURL = "accommodationImageUrl"
        case checkIn, checkOut, accommodationCountry, accommodationName
    }
}

typealias ReservationList = [ReservationListElement]

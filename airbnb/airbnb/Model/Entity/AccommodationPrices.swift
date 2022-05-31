import Foundation

//GET /accommodations/prices?country=양재동
// MARK: - AccommodationPrices
struct AccommodationPrices: Codable {
    let average: Int
    let prices: [Int]

    enum CodingKeys: String, CodingKey {
        case average = "average"
        case prices
    }
}

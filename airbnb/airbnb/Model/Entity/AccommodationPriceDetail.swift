import Foundation

// GET /accommodations/1/detail-price?checkIn=2022-10-23&checkOut=2022-10-30&guestCount=3
// MARK: - AccommodationPriceDetail
struct AccommodationPriceDetail: Codable {
    let price, date, totalPrice, discountRate: Int
    let discountPrice, cleaningFee, serviceFee, accommodationFee: Int
    let finalPrice: Int
}

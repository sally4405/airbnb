package louie.dong.airbnb.accommodation.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import louie.dong.airbnb.accommodation.Accommodation;

@Getter
@AllArgsConstructor
public class AccommodationDetailPriceResponse {

	private int price;
	private int date;
	private int totalPrice;
	private int discountRate;
	private int discountPrice;
	private int cleaningFee;
	private int serviceFee;
	private int accommodationFee;
	private int finalPrice;

	public AccommodationDetailPriceResponse(LocalDate checkIn, LocalDate checkOut, Accommodation accommodation) {
		this.price = accommodation.getPrice();
		this.date = accommodation.getDate(checkIn, checkOut);
		this.totalPrice = accommodation.getTotalPrice(date);
		this.discountRate = accommodation.getDiscountRate(date);
		this.discountPrice = accommodation.getDiscountPrice(totalPrice, discountRate);
		this.cleaningFee = accommodation.getCleaningFee();
		this.serviceFee = accommodation.getServiceFee();
		this.accommodationFee = accommodation.getAccommodationFee();
		this.finalPrice = accommodation.getFinalPrice(totalPrice, discountPrice);
	}
}

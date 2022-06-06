package louie.dong.airbnb.accommodation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}

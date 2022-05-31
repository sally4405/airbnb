package louie.dong.airbnb.accommodation.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationDetailPriceRequest {

	private LocalDate checkIn;
	private LocalDate checkOut;
	private int guestCount;
}

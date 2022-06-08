package louie.dong.airbnb.accommodation.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class AccommodationSearchRequest {

	private String country;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;

	private int minPrice;
	private int maxPrice;
	private int guestCount;
}


package louie.dong.airbnb.accommodation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

	public LocalDateTime getCheckInWithTime() {
		return LocalDateTime.of(checkIn, LocalTime.of(0, 0));
	}

	public LocalDateTime getCheckOutWithTime() {
		return LocalDateTime.of(checkOut, LocalTime.of(0, 0));
	}
}


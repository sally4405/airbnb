package louie.dong.airbnb.accommodation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class AccommodationSearchRequest {

	@NotBlank
	private String country;

	@FutureOrPresent
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;

	@FutureOrPresent
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;

	@PositiveOrZero
	private Integer minPrice;

	@PositiveOrZero
	private Integer maxPrice;

	@Positive
	private Integer guestCount;

	public LocalDateTime getCheckInWithTime() {
		return LocalDateTime.of(checkIn, LocalTime.of(0, 0));
	}

	public LocalDateTime getCheckOutWithTime() {
		return LocalDateTime.of(checkOut, LocalTime.of(0, 0));
	}
}


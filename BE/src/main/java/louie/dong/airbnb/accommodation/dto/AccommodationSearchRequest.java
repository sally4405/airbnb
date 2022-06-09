package louie.dong.airbnb.accommodation.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class AccommodationSearchRequest {

	@NotEmpty
	private String country;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;

	private Integer minPrice;
	private Integer maxPrice;
	private Integer guestCount;
}


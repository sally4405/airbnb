package louie.dong.airbnb.book.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
public class BookSaveRequest {

	private Long accommodationId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;

	private int guestCount;
	private int finalPrice;
}

package louie.dong.airbnb.book.dto;

import java.time.LocalDate;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
public class BookSaveRequest {

	private String accommodationId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;

	private int guestCount;
}

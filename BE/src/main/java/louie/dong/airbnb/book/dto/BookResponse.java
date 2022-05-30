package louie.dong.airbnb.book.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponse {

	private Long bookId;
	private String accommodationImageUrl;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String accommodationCountry;
	private String accommodationName;
}

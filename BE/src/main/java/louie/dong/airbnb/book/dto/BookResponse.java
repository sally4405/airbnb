package louie.dong.airbnb.book.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponse {

	private Long bookId;
	private String accommodationImageUrl;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private String accommodationCountry;
	private String accommodationName;
}

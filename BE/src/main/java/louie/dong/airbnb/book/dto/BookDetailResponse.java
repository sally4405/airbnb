package louie.dong.airbnb.book.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDetailResponse {

	private Long bookId;
	private List<String> accommodationImageUrls;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private String accommodationCountry;
	private String accommodationName;
	private int guestCount;
	private String roomCount;
	private String hostName;
	private int finalPrice;
}

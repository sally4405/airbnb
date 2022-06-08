package louie.dong.airbnb.book.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import louie.dong.airbnb.accommodation.AccommodationImage;
import louie.dong.airbnb.accommodation.RoomType;
import louie.dong.airbnb.book.Book;

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
	private RoomType roomType;
	private String hostName;
	private int finalPrice;

	public BookDetailResponse(Book book) {
		this.bookId = book.getId();
		this.accommodationImageUrls = book.getAccommodation().getAccommodationImages().stream()
			.map(AccommodationImage::getImageUrl).collect(Collectors.toList());
		this.checkIn = book.getCheckIn();
		this.checkOut = book.getCheckOut();
		this.accommodationCountry = book.getAccommodation().getCountry();
		this.accommodationName = book.getAccommodation().getName();
		this.guestCount = book.getGuestCount();
		this.roomType = book.getAccommodation().getRoomInformation().getRoomType();
		this.hostName = book.getAccommodation().getHostName();
		this.finalPrice = book.getFinalPrice();
	}

}

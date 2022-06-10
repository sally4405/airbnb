package louie.dong.airbnb.book.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import louie.dong.airbnb.accommodation.Accommodation;
import louie.dong.airbnb.book.Book;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
public class BookSaveRequest {

    private Long accommodationId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;

    private int guestCount;
    private int finalPrice;

    public Book toEntity(Accommodation accommodation) {
        LocalDateTime checkIn = LocalDateTime.of(checkInDate, accommodation.getCheckInTime());
        LocalDateTime checkOut = LocalDateTime.of(checkOutDate, accommodation.getCheckOutTime());
        return new Book(accommodation, checkIn, checkOut, guestCount, finalPrice);
    }
}

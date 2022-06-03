package louie.dong.airbnb.book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.accommodation.Accommodation;
import louie.dong.airbnb.accommodation.AccommodationRepository;
import louie.dong.airbnb.book.dto.BookDetailResponse;
import louie.dong.airbnb.book.dto.BookResponse;
import louie.dong.airbnb.book.dto.BookSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

	private final BookRepository bookRepository;
	private final AccommodationRepository accommodationRepository;

	public BookDetailResponse findById(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
		return new BookDetailResponse(book);
	}

	public List<BookResponse> findAll() {
		List<Book> books = bookRepository.findAll();
		List<BookResponse> bookResponses = new ArrayList<>();

		for (Book book : books) {
			bookResponses.add(new BookResponse(book.getId(),
				book.getAccommodation().getAccommodationImages().get(0).getImageUrl(),
				book.getCheckIn(), book.getCheckOut(), book.getAccommodation().getCountry(),
				book.getAccommodation().getName()));
		}
		return bookResponses;
	}

	@Transactional
	public void cancel(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
		book.changeCanceled(true);
	}

	@Transactional
	public void save(BookSaveRequest bookSaveRequest) {
		Accommodation accommodation = accommodationRepository.findById(
				bookSaveRequest.getAccommodationId())
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 숙소 id입니다."));

		LocalDateTime checkIn = LocalDateTime.of(bookSaveRequest.getCheckIn(),
			accommodation.getCheckInTime());
		LocalDateTime checkOut = LocalDateTime.of(bookSaveRequest.getCheckOut(),
			accommodation.getCheckOutTime());

		Book book = new Book(accommodation, checkIn, checkOut, bookSaveRequest.getGuestCount(),
			bookSaveRequest.getFinalPrice());

		bookRepository.save(book);
	}
}

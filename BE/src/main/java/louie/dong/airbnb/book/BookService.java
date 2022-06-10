package louie.dong.airbnb.book;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.accommodation.Accommodation;
import louie.dong.airbnb.accommodation.AccommodationService;
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
	private final AccommodationService accommodationService;

	@Transactional
	public void save(BookSaveRequest bookSaveRequest) {
		Accommodation accommodation = accommodationService.getAccommodationOrThrow(
			bookSaveRequest.getAccommodationId());
		Book book = bookSaveRequest.toEntity(accommodation);
		bookRepository.save(book);
	}

	@Transactional
	public void cancel(Long id) {
		findByIdOrThrow(id).cancel();
	}

	public BookDetailResponse findById(Long id) {
		return new BookDetailResponse(findByIdOrThrow(id));
	}

	public List<BookResponse> findAll() {
		return bookRepository.findAll().stream()
			.map(BookResponse::new)
			.collect(Collectors.toList());
	}

	private Book findByIdOrThrow(Long id) {
		return bookRepository.findById(id)
			.orElseThrow(NoSuchElementException::new);
	}
}

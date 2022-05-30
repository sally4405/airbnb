package louie.dong.airbnb.book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import louie.dong.airbnb.book.dto.BookDetailResponse;
import louie.dong.airbnb.book.dto.BookResponse;
import louie.dong.airbnb.book.dto.BookSaveRequest;
import org.springframework.stereotype.Service;

@Service
public class MockBockService {

	public void save(BookSaveRequest bookSaveRequest) {

	}

	public List<BookResponse> findAll() {
		return List.of(
			new BookResponse(1L, "http://~~~~~ 1",
				LocalDate.of(2022, 3, 5),
				LocalDate.of(2022, 3, 11),
				"서초구, 서울, 한국",
				"Spacious and Comfortable cozy house #1"
			),
			new BookResponse(
				2L, "http://~~~~~ 2",
				LocalDate.of(2022, 3, 11),
				LocalDate.of(2022, 3, 17),
				"서초구, 서울, 한국",
				"Spacious and Comfortable cozy house #2"
			),
			new BookResponse(
				3L, "http://~~~~~ 3",
				LocalDate.of(2022, 3, 22),
				LocalDate.of(2022, 3, 25),
				"서초구, 서울, 한국",
				"Spacious and Comfortable cozy house #3"
			));
	}

	public BookDetailResponse findById(Long id) {
		return new BookDetailResponse(1L,
			List.of("http://~~~~~ 1", "http://~~~~~ 2", "http://~~~~~ 3", "http://~~~~~ 4"),
			LocalDateTime.of(2022, 3, 5, 16, 0),
			LocalDateTime.of(2022, 3, 11, 10, 0),
			"서초구, 서울, 한국",
			"Spacious and Comfortable cozy house #1",
			3,
			"원룸",
			"Jong",
			1488195);
	}

	public void delete(Long id) {

	}
}

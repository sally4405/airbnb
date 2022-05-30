package louie.dong.airbnb.book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import louie.dong.airbnb.book.dto.BookDetailResponse;
import org.springframework.stereotype.Service;

@Service
public class MockBockService {

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

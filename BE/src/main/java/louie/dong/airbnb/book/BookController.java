package louie.dong.airbnb.book;

import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.book.dto.BookDetailResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookController {

	private final MockBockService mockBockService;

	@GetMapping("/{id}")
	public BookDetailResponse getBookDetail(@PathVariable Long id) {
		return mockBockService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		mockBockService.delete(id);
	}
}

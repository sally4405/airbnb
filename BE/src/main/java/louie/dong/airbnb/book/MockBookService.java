package louie.dong.airbnb.book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import louie.dong.airbnb.book.dto.BookDetailResponse;
import louie.dong.airbnb.book.dto.BookResponse;
import louie.dong.airbnb.book.dto.BookSaveRequest;
import org.springframework.stereotype.Service;

@Service
public class MockBookService {

	public void save(BookSaveRequest bookSaveRequest) {

	}

	public List<BookResponse> findAll() {
		return List.of(
			new BookResponse(1L, "https://s3-alpha-sig.figma.com/img/2b24/5101/2265996f97a70986d95f1d6fdd40009f?Expires=1655078400&Signature=D2aMRMQGvcfCORWvl9jrqgr6Rubf2044ovniztRQgTreOhAr8yzz~xQ7m0FHd1NC9DD0Z1C69F8XAODD44kfW9NDc~EHKhjdNzgBdgWZnYZRnB41avlSb2auh6ynKlaKD8yPs-St-sgrNy9QANbQguoDmDLixQXMRI1osY8zYNc4QPtUonohHD5Td71wqsLzMAWqSWsqWq5YquBmVG8iZ3nc9KjiujFSWhotD7bt0SpwoUl59w0WeuO~G~sSU7ns1FexmtPW8KYL~UIyiVXGGux23EqwOIIOZJRBWXkoSasdpczl0l4gwaXHDkpbFWSDE7lmc23B7waBAsF2I9SraA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
				LocalDate.of(2022, 3, 5),
				LocalDate.of(2022, 3, 11),
				"서초구, 서울, 한국",
				"Spacious and Comfortable cozy house #1"
			),
			new BookResponse(
				2L, "https://s3-alpha-sig.figma.com/img/6922/44b3/5c34d655bb2bab8f252f8742ee10b2c0?Expires=1655078400&Signature=fhXsW20hxN0uoDN899YKaWakMnhf6R0wzpatwbUNXYTo02mrK518vP7kNRE5P8rxN6-3Tawww7NQTFfB1cTU~cZRsR2Gfeuj~6oyEQIYEqERdjIKd6raeQaDR19bml-qUo3jDBGH2EubyzWtDeM0wMfPnaHOrG-XZOh1UGvWM921EuNI9b8AZ4CJ6KEE71SS7iq08HyyQscmkmrM2-c89HNqBH7keKXwfozKU6m2nMYiBe427qPxqP2IeKr0yU6K81fMHHuVM2gai5NKJ5vGpis74Omt4JcgQ1o5GuFfa9NbcBlAiI0rpeR2-aq1swK4gLuQp6IEdTJzmglpFSQy8Q__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
				LocalDate.of(2022, 3, 11),
				LocalDate.of(2022, 3, 17),
				"서초구, 서울, 한국",
				"Spacious and Comfortable cozy house #2"
			),
			new BookResponse(
				3L, "https://s3-alpha-sig.figma.com/img/8316/2d4d/96d08957d6dfd6d333ba85f1dbf37478?Expires=1655078400&Signature=BpfyP25F6AAnJP-N9m9MnY1nZlskWpqAqLvYMzdOhacHDcG30XXqBfmrQhkchAXU~Q1oUY3xvd-IOr2759zsvKLPrvMNiT-1xV9fnyNw07b~2ylpxCAfQgY5JQVSvZ-TE6bFj-47xrQjsBG2spcmX-MJHe3CX3eM6gr0XS-qPagLBfdQXm9TgNQg7GQrumfjm-sOnl0eHqMwcvleLZ-tRiNZsfttHKQXN1HKuc3Y50iDnUgwz-5jNaUi8ohcoDBT6rMr5dR6RPN4tn4pUNYnMFr~eTSjm2REN9koMPf9n1G6HkDT9an1THCpWyc5isntxQRZFtZDfDfwlT6cBQXMcQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
				LocalDate.of(2022, 3, 22),
				LocalDate.of(2022, 3, 25),
				"서초구, 서울, 한국",
				"Spacious and Comfortable cozy house #3"
			));
	}

	public BookDetailResponse findById(Long id) {
		return new BookDetailResponse(1L,
			List.of("https://a0.muscache.com/im/pictures/8a5dded5-25fb-4686-90fd-c4f916fcb9fb.jpg", "https://a0.muscache.com/im/pictures/miso/Hosting-48760969/original/09926f6f-92b8-4341-8bcf-e0b6660d3c24.jpeg", "https://a0.muscache.com/im/pictures/0b313b69-79ac-41f4-a520-79e06063c17f.jpg"),
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

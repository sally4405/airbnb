package louie.dong.airbnb.book;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import louie.dong.airbnb.accommodation.Accommodation;

@Entity
public class Book {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Accommodation accommodation;

	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private int guestCount;
}

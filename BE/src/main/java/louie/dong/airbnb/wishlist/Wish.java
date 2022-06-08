package louie.dong.airbnb.wishlist;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import louie.dong.airbnb.accommodation.Accommodation;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Wish {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Accommodation accommodation;

	public Wish(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

}

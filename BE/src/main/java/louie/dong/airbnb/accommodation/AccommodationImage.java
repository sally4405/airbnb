package louie.dong.airbnb.accommodation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class AccommodationImage {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Accommodation accommodation;

	@Column(length = 1000)
	private String imageUrl;

	@Enumerated(EnumType.STRING)
	private ImageType imageType;
}

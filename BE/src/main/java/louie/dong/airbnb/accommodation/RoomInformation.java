package louie.dong.airbnb.accommodation;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomInformation {

	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	private int maxGuestCount;
	private int bedroomCount;
	private int bathroomCount;
}

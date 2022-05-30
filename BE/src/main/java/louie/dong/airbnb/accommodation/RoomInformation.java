package louie.dong.airbnb.accommodation;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomInformation {

	private int maxGuestCount;
	private RoomType roomType;
	private int bedroomCount;
	private int bathroomCont;
}

package louie.dong.airbnb.accommodation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccommodationPriceResponse {

	private int average;
	private List<Integer> prices;

}

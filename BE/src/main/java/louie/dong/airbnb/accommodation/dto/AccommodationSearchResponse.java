package louie.dong.airbnb.accommodation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccommodationSearchResponse {

	private int count;
	private List<AccommodationResponse> accommodations;
}

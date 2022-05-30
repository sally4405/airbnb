package louie.dong.airbnb.accommodation;

import java.util.List;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import org.springframework.stereotype.Service;

@Service
public class MockAccommodationService {

	public AccommodationPriceResponse findPrices(String country) {
		return new AccommodationPriceResponse(165556, List.of(12345, 56780, 12455, 12350));
	}
}

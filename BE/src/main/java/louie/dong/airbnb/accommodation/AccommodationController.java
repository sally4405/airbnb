package louie.dong.airbnb.accommodation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

	private final MockAccommodationService mockAccommodationService;

	@GetMapping("/accommodations/prices")
	public AccommodationPriceResponse getAccommodationPrice(String country) {
		return mockAccommodationService.findPrices(country);
	}

}

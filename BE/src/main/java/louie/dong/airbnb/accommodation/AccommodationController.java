package louie.dong.airbnb.accommodation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/accommodations")
@RequiredArgsConstructor
@RestController
public class AccommodationController {

	private final MockAccommodationService mockAccommodationService;

	@GetMapping("/prices")
	public AccommodationPriceResponse getAccommodationPrice(String country) {
		return mockAccommodationService.findPrices(country);
	}

	@GetMapping("/{id}")
	public AccommodationDetailResponse getAccommodationDetail(@PathVariable Long id) {
		return mockAccommodationService.findById(id);
	}

	@GetMapping("/{id}/detail-price")
	public AccommodationDetailPriceResponse getDetailPrice(@PathVariable Long id,
		String checkIn, String checkOut, int guestCount) {
		return mockAccommodationService.findDetailPrice(id, checkIn, checkOut, guestCount);
	}

}

package louie.dong.airbnb.accommodation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/accommodations")
@RequiredArgsConstructor
@RestController
public class AccommodationController {

	private final MockAccommodationService mockAccommodationService;
	private final AccommodationService accommodationService;

	@GetMapping("/prices")
	public AccommodationPriceResponse getAccommodationPrices(String country) {
		return accommodationService.findPrices(country);
	}

	@GetMapping
	public AccommodationSearchResponse getAccommodationSearch(
		AccommodationSearchRequest accommodationSearchRequest) {
		return mockAccommodationService.findAccommodations(accommodationSearchRequest);
	}

	@GetMapping("/{id}")
	public AccommodationDetailResponse getAccommodationDetail(@PathVariable Long id) {
		return mockAccommodationService.findById(id);
	}

	@GetMapping("/{id}/detail-price")
	public AccommodationDetailPriceResponse getDetailPrice(@PathVariable Long id,
		AccommodationDetailPriceRequest accommodationDetailPriceRequest) {
		return mockAccommodationService.findDetailPrice(id, accommodationDetailPriceRequest);
	}

}

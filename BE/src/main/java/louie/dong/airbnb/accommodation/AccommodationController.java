package louie.dong.airbnb.accommodation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RequestMapping("/accommodations")
@RequiredArgsConstructor
@RestController
public class AccommodationController {

	private final AccommodationService accommodationService;

	@GetMapping("/prices")
	public AccommodationPriceResponse getAccommodationPrices(String country) {
		return accommodationService.findPrices(country);
	}

	@GetMapping
	public AccommodationSearchResponse getAccommodationSearch(
		@Valid AccommodationSearchRequest accommodationSearchRequest) {
		return accommodationService.findAccommodations(accommodationSearchRequest);
	}

	@GetMapping("/{id}")
	public AccommodationDetailResponse getAccommodationDetail(@PathVariable Long id) {
		return accommodationService.findById(id);
	}

	@GetMapping("/{id}/detail-price")
	public AccommodationDetailPriceResponse getDetailPrice(@PathVariable Long id,
		AccommodationDetailPriceRequest accommodationDetailPriceRequest) {
		return accommodationService.findDetailPrice(id, accommodationDetailPriceRequest);
	}

}

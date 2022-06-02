package louie.dong.airbnb.accommodation;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AccommodationService {

	private final AccommodationRepository accommodationRepository;

	public AccommodationPriceResponse findPrices(String country) {
		List<Integer> prices = accommodationRepository.findByAccommodationPrices(country);
		return new AccommodationPriceResponse(calculateAverage(prices), prices);
	}

	public AccommodationDetailResponse findById(Long id) {
		Accommodation accommodation = accommodationRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));
		return new AccommodationDetailResponse(accommodation);
	}

	private int calculateAverage(List<Integer> prices) {
		int sum = 0;
		for (Integer price : prices) {
			sum += price;
		}
		return sum / prices.size();
	}

}

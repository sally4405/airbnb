package louie.dong.airbnb.accommodation;

import static louie.dong.airbnb.accommodation.DiscountPolicy.MONTHLY;
import static louie.dong.airbnb.accommodation.DiscountPolicy.NONE;
import static louie.dong.airbnb.accommodation.DiscountPolicy.WEEKLY;
import static louie.dong.airbnb.accommodation.DiscountPolicy.YEARLY;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import louie.dong.airbnb.wishlist.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AccommodationService {

	private final AccommodationRepository accommodationRepository;
	private final WishRepository wishRepository;

	public AccommodationPriceResponse findPrices(String country) {
		List<Integer> prices = accommodationRepository.findByAccommodationPrices(country);
		return new AccommodationPriceResponse(calculateAverage(prices), prices);
	}

	public AccommodationDetailResponse findById(Long id) {
		Accommodation accommodation = getAccommodation(id);
		boolean wish = wishRepository.existsByAccommodationId(id);
		return new AccommodationDetailResponse(accommodation, wish);
	}

	public AccommodationDetailPriceResponse findDetailPrice(Long id,
		AccommodationDetailPriceRequest accommodationDetailPriceRequest) {
		Accommodation accommodation = getAccommodation(id);
		LocalDate checkIn = accommodationDetailPriceRequest.getCheckIn();
		LocalDate checkOut = accommodationDetailPriceRequest.getCheckOut();

		int date = (int) checkIn.until(checkOut, ChronoUnit.DAYS);
		int totalPrice = accommodation.getPrice() * date;
		double discountRate = getDiscountRate(date) * 0.01;
		int discountPrice = (int) (totalPrice * discountRate);
		int finalPrice = totalPrice - discountPrice + accommodation.getCleaningFee()
			+ accommodation.getServiceFee() + accommodation.getAccommodationFee();

		return new AccommodationDetailPriceResponse(accommodation.getPrice(), date, totalPrice,
			WEEKLY.getDiscountRate(), discountPrice, accommodation.getCleaningFee(),
			accommodation.getServiceFee(), accommodation.getAccommodationFee(), finalPrice);
	}

	private Accommodation getAccommodation(Long id) {
		return accommodationRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));
	}

	private int getDiscountRate(int date) {
		if (date < 7) {
			return NONE.getDiscountRate();
		}

		if (date < 30) {
			return WEEKLY.getDiscountRate();
		}

		if (date < 365) {
			return MONTHLY.getDiscountRate();
		}
		return YEARLY.getDiscountRate();
	}

	private int calculateAverage(List<Integer> prices) {
		int sum = 0;
		for (Integer price : prices) {
			sum += price;
		}
		return sum / prices.size();
	}
}

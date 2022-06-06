package louie.dong.airbnb.accommodation;

import static louie.dong.airbnb.accommodation.DiscountPolicy.MONTHLY;
import static louie.dong.airbnb.accommodation.DiscountPolicy.NONE;
import static louie.dong.airbnb.accommodation.DiscountPolicy.WEEKLY;
import static louie.dong.airbnb.accommodation.DiscountPolicy.YEARLY;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchResponse;
import louie.dong.airbnb.wishlist.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

		int date = getDate(checkIn, checkOut);
		int totalPrice = accommodation.getPrice() * date;
		double discountRate = getDiscountRate(date) * 0.01;
		int discountPrice = (int) (totalPrice * discountRate);
		int finalPrice = totalPrice - discountPrice + accommodation.getCleaningFee()
			+ accommodation.getServiceFee() + accommodation.getAccommodationFee();

		return new AccommodationDetailPriceResponse(accommodation.getPrice(), date, totalPrice,
			WEEKLY.getDiscountRate(), discountPrice, accommodation.getCleaningFee(),
			accommodation.getServiceFee(), accommodation.getAccommodationFee(), finalPrice);
	}

	private int getDate(LocalDate checkIn, LocalDate checkOut) {
		int date = (int) checkIn.until(checkOut, ChronoUnit.DAYS);
		return date;
	}

	@Transactional
	public AccommodationSearchResponse findAccommodations(
		AccommodationSearchRequest accommodationSearchRequest) {
		List<Accommodation> accommodations = accommodationRepository.findByAccommodations(accommodationSearchRequest.getCountry());
		List<AccommodationResponse> accommodationResponses = new ArrayList<>();

		for (Accommodation accommodation : accommodations) {

			if (accommodation.getRoomInformation().getMaxGuestCount()
				< accommodationSearchRequest.getGuestCount()) {
				continue;
			}

			if (accommodationSearchRequest.getMinPrice() > accommodation.getPrice()
				&& accommodationSearchRequest.getMaxPrice() < accommodation.getPrice()) {
				continue;
			}

			int date = getDate(accommodationSearchRequest.getCheckIn(),
				accommodationSearchRequest.getCheckOut());
			int totalPrice = date * accommodation.getPrice();

			AccommodationResponse accommodationResponse = new AccommodationResponse(
				accommodation.getId(), accommodation.getName(),
				accommodation.getAccommodationImages().get(0).getImageUrl(),
				accommodation.getRating(), accommodation.getReviewCount(), accommodation.getPrice(),
				totalPrice, wishRepository.existsByAccommodationId(accommodation.getId()),
				accommodation.getPoint());

			accommodationResponses.add(accommodationResponse);
		}
		return new AccommodationSearchResponse(accommodations.size(), accommodationResponses);
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

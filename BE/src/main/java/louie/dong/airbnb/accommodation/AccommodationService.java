package louie.dong.airbnb.accommodation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final WishRepository wishRepository;

    public AccommodationPriceResponse findPrices(String country) {
        List<Integer> prices = accommodationRepository.findPricesByAccommodation(country);
        return new AccommodationPriceResponse(calculateAverage(prices), prices);
    }

    public AccommodationDetailResponse findById(Long id) {
        Accommodation accommodation = getAccommodationOrThrow(id);
        boolean wish = wishRepository.existsByAccommodationId(id);
        return new AccommodationDetailResponse(accommodation, wish);
    }

    public AccommodationDetailPriceResponse findDetailPrice(Long id,
        AccommodationDetailPriceRequest accommodationDetailPriceRequest) {
        Accommodation accommodation = getAccommodationOrThrow(id);
        LocalDate checkIn = accommodationDetailPriceRequest.getCheckIn();
        LocalDate checkOut = accommodationDetailPriceRequest.getCheckOut();
        return new AccommodationDetailPriceResponse(checkIn, checkOut, accommodation);
    }

    public AccommodationSearchResponse findAccommodations(
        AccommodationSearchRequest accommodationSearchRequest) {
        List<Accommodation> accommodations = accommodationRepository.searchAccommodations(
            accommodationSearchRequest.getCountry(),
            accommodationSearchRequest.getCheckInWithTime(),
            accommodationSearchRequest.getCheckOutWithTime(),
            accommodationSearchRequest.getMinPrice(), accommodationSearchRequest.getMaxPrice(),
            accommodationSearchRequest.getGuestCount());

        List<AccommodationResponse> accommodationResponses = createAccommodationResponses(
            accommodations, accommodationSearchRequest.getCheckIn(),
            accommodationSearchRequest.getCheckOut());
        return new AccommodationSearchResponse(accommodations.size(), accommodationResponses);
    }

    private List<AccommodationResponse> createAccommodationResponses(
        List<Accommodation> accommodations, LocalDate checkIn, LocalDate checkOut) {
        int stayNight = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
        return accommodations.stream()
            .map(accommodation -> new AccommodationResponse(accommodation,
                stayNight * accommodation.getPrice(), accommodation.existsWish()))
            .collect(Collectors.toList());
    }

    private Accommodation getAccommodationOrThrow(Long id) {
        return accommodationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));
    }

    private int calculateAverage(List<Integer> prices) {
        return (int) prices.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElseThrow();
    }
}

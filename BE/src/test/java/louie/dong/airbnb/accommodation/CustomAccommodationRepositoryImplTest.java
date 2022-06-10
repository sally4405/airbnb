package louie.dong.airbnb.accommodation;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchRequest;
import louie.dong.airbnb.config.JpaQueryTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(JpaQueryTestConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class CustomAccommodationRepositoryImplTest {

    @Autowired AccommodationRepository accommodationRepository;

    @Test
    void 서울에_있는_모든_숙소를_반환한다() {
        AccommodationSearchRequest request = new AccommodationSearchRequest();
        request.setCountry("서울");

        List<Accommodation> accommodations = accommodationRepository.searchAccommodations(request);

        assertThat(accommodations.size()).isEqualTo(5);
    }

    @Test
    void 양재동에_있는_모든_숙소를_반환한다() {
        AccommodationSearchRequest request = new AccommodationSearchRequest();
        request.setCountry("양재동");

        List<Accommodation> accommodations = accommodationRepository.searchAccommodations(request);

        assertThat(accommodations.size()).isEqualTo(3);
    }

    @Test
    void 가격이_5만원에서_10만원_사이인_서울에_있는_모든_숙소를_반환한다() {
        AccommodationSearchRequest request = new AccommodationSearchRequest();
        request.setCountry("서울");
        request.setMinPrice(50000);
        request.setMaxPrice(100000);

        List<Accommodation> accommodations = accommodationRepository.searchAccommodations(request);

        assertThat(accommodations.size()).isEqualTo(3);
    }

    @Test
    void 가격이_3만원에서_5만원_사이인_양재동에_있는_모든_숙소를_반환한다() {
        AccommodationSearchRequest request = new AccommodationSearchRequest();
        request.setCountry("양재동");
        request.setMinPrice(30000);
        request.setMaxPrice(50000);

        List<Accommodation> accommodations = accommodationRepository.searchAccommodations(request);

        assertThat(accommodations.size()).isEqualTo(2);
    }

    @Test
    void 서울에_있고_6월_11일부터_6월_14일_사이에_예약이_존재하지_않는_모든_숙소를_반환한다() {
        AccommodationSearchRequest request = new AccommodationSearchRequest();
        request.setCountry("서울");
        request.setCheckIn(LocalDate.of(2022, 6, 11));
        request.setCheckOut(LocalDate.of(2022, 6, 14));

        List<Accommodation> accommodations = accommodationRepository.searchAccommodations(request);

        assertThat(accommodations.size()).isEqualTo(2);
    }

}

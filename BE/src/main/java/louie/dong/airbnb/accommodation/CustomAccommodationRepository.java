package louie.dong.airbnb.accommodation;

import java.time.LocalDateTime;
import java.util.List;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchResponse;
import org.springframework.data.repository.query.Param;

public interface CustomAccommodationRepository {

    List<Accommodation> searchAccommodations(AccommodationSearchRequest accommodationSearchRequest);
}

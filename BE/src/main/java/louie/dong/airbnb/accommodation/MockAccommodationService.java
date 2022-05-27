package louie.dong.airbnb.accommodation;

import java.util.List;
import louie.dong.airbnb.dto.SearchCountryResponse;
import org.springframework.stereotype.Service;

@Service
public class MockAccommodationService {

    public List<SearchCountryResponse> findByCountryLike(String country) {
        return List.of(
            new SearchCountryResponse("양재동, 서초구, 서울특별시", ""),
            new SearchCountryResponse("양재역 사거리, 양재1동", ""),
            new SearchCountryResponse("서울특별시 양재2동 시민의숲앞", ""),
            new SearchCountryResponse("서울특별시 양재2동 양재IC", "")
        );
    }
}

package louie.dong.airbnb.travel;

import java.util.List;
import louie.dong.airbnb.travel.dto.PopularTravelResponse;
import org.springframework.stereotype.Service;

@Service
public class MockTravelService {

    public List<PopularTravelResponse> findAll() {
        return List.of(
            new PopularTravelResponse("서울", "", "차로 30분 거리"),
            new PopularTravelResponse("광주", "", "차로 4시간 거리"),
            new PopularTravelResponse("의정부시", "", "차로 30분 거리"),
            new PopularTravelResponse("수원시", "", "차로 45분 거리"),
            new PopularTravelResponse("대구", "", "차로 3.5시간 거리"),
            new PopularTravelResponse("울산", "", "차로 4.5시간 거리"),
            new PopularTravelResponse("대전", "", "차로 2시간 거리"),
            new PopularTravelResponse("부천시", "", "차로 30분 거리")
        );
    }
}

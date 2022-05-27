package louie.dong.airbnb.travel;

import java.util.List;
import louie.dong.airbnb.dto.PopularDestinationResponse;
import org.springframework.stereotype.Service;

@Service
public class MockTravelService {

    public List<PopularDestinationResponse> findAll() {
        return List.of(
            new PopularDestinationResponse("서울", "", "차로 30분 거리"),
            new PopularDestinationResponse("광주", "", "차로 4시간 거리"),
            new PopularDestinationResponse("의정부시", "", "차로 30분 거리"),
            new PopularDestinationResponse("수원시", "", "차로 45분 거리"),
            new PopularDestinationResponse("대구", "", "차로 3.5시간 거리"),
            new PopularDestinationResponse("울산", "", "차로 4.5시간 거리"),
            new PopularDestinationResponse("대전", "", "차로 2시간 거리"),
            new PopularDestinationResponse("부천시", "", "차로 30분 거리")
        );
    }
}

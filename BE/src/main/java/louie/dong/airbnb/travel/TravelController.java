package louie.dong.airbnb.travel;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.travel.dto.PopularTravelResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TravelController {

    private final MockTravelService mockTravelService;

    @GetMapping("/travel/popular")
    public List<PopularTravelResponse> getPopularDestinations() {
        return mockTravelService.findAll();
    }
}

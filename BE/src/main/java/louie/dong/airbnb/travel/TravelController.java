package louie.dong.airbnb.travel;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.dto.PopularDestinationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TravelController {

    private final MockTravelService mockPopularDestinationService;

    @GetMapping("/travel/popular")
    public List<PopularDestinationResponse> getPopularDestinations() {
        return mockPopularDestinationService.findAll();
    }
}

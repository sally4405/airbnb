package louie.dong.airbnb.region;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.region.dto.SearchRegionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegionController {

	private final MockRegionService mockRegionService;

	@GetMapping("/regions/search")
	public List<SearchRegionResponse> getSearchRegions(String country) {
		return mockRegionService.findByRegion(country);
	}

}

package louie.dong.airbnb.region;

import java.util.List;
import louie.dong.airbnb.region.dto.SearchRegionResponse;
import org.springframework.stereotype.Service;

@Service
public class MockRegionService {

	public List<SearchRegionResponse> findByRegion(String country) {
		return List.of(
			new SearchRegionResponse("양재동, 서초구, 서울특별시", ""),
			new SearchRegionResponse("양재역 사거리, 양재1동", ""),
			new SearchRegionResponse("서울특별시 양재2동 시민의숲앞", ""),
			new SearchRegionResponse("서울특별시 양재2동 양재IC", "")
		);
	}
}

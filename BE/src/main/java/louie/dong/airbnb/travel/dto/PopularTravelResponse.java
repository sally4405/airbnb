package louie.dong.airbnb.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PopularTravelResponse {

    private String name;
    private String imageUrl;
    private String distance;
}

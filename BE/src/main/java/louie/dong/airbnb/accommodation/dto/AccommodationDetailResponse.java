package louie.dong.airbnb.accommodation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
정import louie.dong.airbnb.accommodation.RoomInformation;

@Getter
@AllArgsConstructor
public class AccommodationDetailResponse {

	private Long id;
	private String name;
	private String imageUrl;
	private double rating;
	private int reviewCount;
	private String country;
	private String hostName;
	private String hostImageUrl;
	private RoomInformation roomInformation;
	private String description;
	private int price;
	private boolean wishlist;
}

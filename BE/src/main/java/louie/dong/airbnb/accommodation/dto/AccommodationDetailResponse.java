package louie.dong.airbnb.accommodation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
	private int maxGuestCount;
	private String roomCount;
	private int bedroomCount;
	private int bathroomCount;
	private String description;
	private int price;
	private boolean wishlist;
}

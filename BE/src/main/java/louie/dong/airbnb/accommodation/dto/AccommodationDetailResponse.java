package louie.dong.airbnb.accommodation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import louie.dong.airbnb.accommodation.Accommodation;
import louie.dong.airbnb.accommodation.RoomInformation;

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

	public AccommodationDetailResponse(Accommodation accommodation, boolean wish) {
		this.id = accommodation.getId();
		this.name = accommodation.getName();
		this.imageUrl = accommodation.getImageUrl();
		this.rating = accommodation.getRating();
		this.reviewCount = accommodation.getReviewCount();
		this.country = accommodation.getCountry();
		this.hostName = accommodation.getHostName();
		this.hostImageUrl = accommodation.getHostImageUrl();
		this.roomInformation = accommodation.getRoomInformation();
		this.description = accommodation.getDescription();
		this.price = accommodation.getPrice();
		this.wishlist = wish;
	}
}

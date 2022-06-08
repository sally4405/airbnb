package louie.dong.airbnb.accommodation.dto;

import lombok.Getter;
import louie.dong.airbnb.accommodation.Accommodation;
import org.springframework.data.geo.Point;

@Getter
public class AccommodationResponse {

	private Long id;
	private String name;
	private String imageUrl;
	private double rating;
	private int reviewCount;
	private int price;
	private int totalPrice;
	private boolean wishlist;
	private double latitude;
	private double longitude;

	public AccommodationResponse(Accommodation accommodation, int totalPrice, boolean wishlist) {
		this.id = accommodation.getId();
		this.name = accommodation.getName();
		this.imageUrl = accommodation.getAccommodationImages().get(0).getImageUrl();
		this.rating = accommodation.getRating();
		this.reviewCount = accommodation.getReviewCount();
		this.price = accommodation.getPrice();
		this.totalPrice = totalPrice;
		this.wishlist = wishlist;
		this.latitude = accommodation.getPoint().getY();
		this.longitude = accommodation.getPoint().getX();
	}

	public AccommodationResponse(Long id, String name, String imageUrl, double rating,
		int reviewCount, int price, int totalPrice, boolean wishlist, Point point) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.rating = rating;
		this.reviewCount = reviewCount;
		this.price = price;
		this.totalPrice = totalPrice;
		this.wishlist = wishlist;
		this.latitude = point.getY();
		this.longitude = point.getX();
	}
}

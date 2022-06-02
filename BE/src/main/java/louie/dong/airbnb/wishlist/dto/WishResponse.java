package louie.dong.airbnb.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import louie.dong.airbnb.wishlist.Wish;

@Getter
@AllArgsConstructor
public class WishResponse {

	private Long id;
	private String name;
	private String imageUrl;
	private double rating;
	private int reviewCount;
	private int price;
	private boolean wishlist;

	public WishResponse(Wish wish) {
		this.id = wish.getId();
		this.name = wish.getAccommodation().getName();
		this.imageUrl = wish.getAccommodation().getAccommodationImages().get(0).getImageUrl();
		this.rating = wish.getAccommodation().getRating();
		this.reviewCount = wish.getAccommodation().getReviewCount();
		this.price = wish.getAccommodation().getPrice();
		this.wishlist = true;
	}
}

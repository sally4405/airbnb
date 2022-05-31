package louie.dong.airbnb.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishListResponse {

	private Long id;
	private String name;
	private String imageUrl;
	private double rating;
	private int reviewCount;
	private int price;
	private boolean wishlist;
}

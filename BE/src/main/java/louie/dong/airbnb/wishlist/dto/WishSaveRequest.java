package louie.dong.airbnb.wishlist.dto;

import lombok.Getter;

@Getter
public class WishSaveRequest {

    private Long memberId;
    private Long accommodationId;
}

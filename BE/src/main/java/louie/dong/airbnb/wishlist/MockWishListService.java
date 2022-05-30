package louie.dong.airbnb.wishlist;

import java.util.List;
import louie.dong.airbnb.wishlist.dto.WishListResponse;
import louie.dong.airbnb.wishlist.dto.WishListSaveRequest;
import org.springframework.stereotype.Service;

@Service
public class MockWishListService {

    public List<WishListResponse> findAll() {
        return List.of(
            new WishListResponse(1L, "숙소 이름", "https://~~~", 4.80, 127, 82953, true),
            new WishListResponse(2L, "숙소 이름 2", "https://~~~~~~~", 4.35, 105, 333, true));
    }

    public void save(WishListSaveRequest wishListSaveRequest) {

    }

    public void delete(Long id) {

    }
}

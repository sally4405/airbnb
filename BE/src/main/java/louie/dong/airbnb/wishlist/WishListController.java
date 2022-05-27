package louie.dong.airbnb.wishlist;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/wishlists")
@RestController
public class WishListController {

    private final MockWishListService mockWishListService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mockWishListService.delete(id);
    }
}

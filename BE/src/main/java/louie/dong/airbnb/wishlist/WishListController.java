package louie.dong.airbnb.wishlist;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.wishlist.dto.WishListResponse;
import louie.dong.airbnb.wishlist.dto.WishListSaveRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/wishlists")
@RestController
public class WishListController {

    private final MockWishListService mockWishListService;

    @GetMapping
    public List<WishListResponse> getWishlists() {
        return mockWishListService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody WishListSaveRequest wishListSaveRequest) {
        mockWishListService.save(wishListSaveRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mockWishListService.delete(id);
    }
}

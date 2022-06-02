package louie.dong.airbnb.wishlist;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.wishlist.dto.WishResponse;
import louie.dong.airbnb.wishlist.dto.WishSaveRequest;
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
public class WishController {

    private final WishService wishService;

    @GetMapping
    public List<WishResponse> getWishlist() {
        return wishService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody WishSaveRequest wishSaveRequest) {
        wishService.save(wishSaveRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        wishService.delete(id);
    }
}

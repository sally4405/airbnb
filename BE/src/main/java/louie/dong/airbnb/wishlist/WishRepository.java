package louie.dong.airbnb.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

	boolean existsByAccommodationId(Long accommodationId);

}

package louie.dong.airbnb.accommodation;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, CustomAccommodationRepository {

	@Query("SELECT ac.price FROM Accommodation AS ac WHERE ac.country LIKE %:country%")
	List<Integer> findPricesByAccommodation(@Param("country") String country);

//	@Query("SELECT a FROM Accommodation a "
//		+ "WHERE a.id NOT IN "
//		+ "(SELECT a1.id FROM Accommodation a1 "
//		+ "JOIN a1.books b ON "
//		+ "b.checkIn BETWEEN :checkIn AND :checkOut AND "
//		+ "b.checkOut BETWEEN :checkIn AND :checkOut "
//		+ "WHERE a1.country LIKE %:country% AND "
//		+ "a1.price BETWEEN :minPrice AND :maxPrice AND "
//		+ "a1.roomInformation.maxGuestCount >= :guestCount) ")
//	List<Accommodation> searchAccommodations(@Param("country") String country,
//		@Param("checkIn") LocalDateTime checkIn, @Param("checkOut") LocalDateTime checkOut,
//		@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice,
//		@Param("guestCount") int guestCount);

}

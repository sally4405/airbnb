package louie.dong.airbnb.accommodation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT ac.price FROM Accommodation AS ac WHERE ac.country LIKE %:country%")
    List<Integer> findByAccommodationPrices(@Param("country") String country);

    /**
     * 파라미터로 넘어온 country를 포함하는 지역 데이터를
     */
    @Query("SELECT ac FROM Accommodation AS ac WHERE ac.country LIKE %:country%")
    List<Accommodation> findByAccommodations(@Param("country") String country);
}

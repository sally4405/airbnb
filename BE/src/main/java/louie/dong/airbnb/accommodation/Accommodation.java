package louie.dong.airbnb.accommodation;

import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

@Getter
@Entity
public class Accommodation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	// mysql에 autoincrement 가능하게 하는 옵션
	private Long id;

	@Column(name = "accommodation_name")
	private String name;

	@Column(name = "accommodation_description")
	private String description;

	@Embedded
	private RoomInformation roomInformation;

	@OneToMany(mappedBy = "accommodation")
	private List<AccommodationImage> accommodationImages;

	@Column(columnDefinition = "point")
	private Point point;

	private String country;
	private int price;
	private double rating;
	private int reviewCount;
	private String hostName;
	private String hostImageUrl;
	private LocalTime checkInTime;
	private LocalTime checkOutTime;
	private int cleaningFee;
	private int serviceFee;
	private int accommodationFee;
}

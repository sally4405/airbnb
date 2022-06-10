package louie.dong.airbnb.book;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import louie.dong.airbnb.accommodation.Accommodation;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Accommodation accommodation;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int guestCount;
    private int finalPrice;
    private boolean isCanceled;

    public Book(Accommodation accommodation, LocalDateTime checkIn, LocalDateTime checkOut,
        int guestCount, int finalPrice) {
        this.accommodation = accommodation;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestCount = guestCount;
        this.finalPrice = finalPrice;
        this.isCanceled = false;
    }

    public void cancel() {
        this.isCanceled = true;
    }
}

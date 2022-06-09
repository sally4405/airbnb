package louie.dong.airbnb.accommodation;

import static louie.dong.airbnb.accommodation.QAccommodation.*;
import static louie.dong.airbnb.accommodation.QAccommodationImage.*;
import static louie.dong.airbnb.book.QBook.*;
import static louie.dong.airbnb.wishlist.QWish.*;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchRequest;
import louie.dong.airbnb.wishlist.QWish;

@RequiredArgsConstructor
public class CustomAccommodationRepositoryImpl implements CustomAccommodationRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Accommodation> searchAccommodations(
        AccommodationSearchRequest accommodationSearchRequest) {
        return queryFactory
            .selectFrom(accommodation)
            .leftJoin(accommodation.accommodationImages, accommodationImage)
                .on(accommodationImage.imageType.eq(ImageType.MAIN))
            .leftJoin(accommodation.wishlist).fetchJoin()
            .leftJoin(accommodation.books, book).on(book.isCanceled.eq(false))
            .where(
                accommodation.country.contains(accommodationSearchRequest.getCountry()),
                checkPrice(accommodationSearchRequest.getMinPrice(),
                    accommodationSearchRequest.getMaxPrice()),
                checkInAndOut(accommodationSearchRequest.getCheckIn(),
                    accommodationSearchRequest.getCheckOut()),
                checkGuestCount(accommodationSearchRequest.getGuestCount())
            ).fetch();
    }

    private BooleanExpression checkGuestCount(Integer guestCount) {
        return guestCount != null ? accommodation.roomInformation.maxGuestCount.goe(guestCount) : null;
    }

    private BooleanExpression checkInAndOut(LocalDate checkIn, LocalDate checkOut) {
        return ((checkIn != null) && (checkOut != null)) ?
            accommodation.books.isEmpty().or(book.checkOut.loe(LocalDateTime.of(checkIn, LocalTime.of(0, 0)))
                .or(book.checkIn.goe(LocalDateTime.of(checkOut, LocalTime.of(0, 0))))) : null;
    }

    private BooleanExpression checkPrice(Integer minPrice, Integer maxPrice) {
        return ((minPrice != null) && (maxPrice != null)) ?
            accommodation.price.goe(minPrice).and(accommodation.price.loe(maxPrice)) : null;
    }

}

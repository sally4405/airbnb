package louie.dong.airbnb.accommodation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DiscountPolicy {
    WEEKLY(4), MONTHLY(7), YEARLY(10), NONE(0);

    private final int discountRate;
}

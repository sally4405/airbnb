package louie.dong.airbnb.accommodation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DiscountPolicy {

	WEEKLY(4), MONTHLY(7), YEARLY(10), NONE(0);

	private static final int WEEK = 7;
	private static final int MONTH = 30;
	private static final int YEAR = 365;

	private final int discountRate;

	public static int getDiscountRate(int date) {
		if (date < WEEK) {
			return NONE.getDiscountRate();
		}

		if (date < MONTH) {
			return WEEKLY.getDiscountRate();
		}

		if (date < YEAR) {
			return MONTHLY.getDiscountRate();
		}
		return YEARLY.getDiscountRate();
	}
}

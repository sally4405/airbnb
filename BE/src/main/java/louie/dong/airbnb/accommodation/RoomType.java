package louie.dong.airbnb.accommodation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoomType {
	APARTMENT("아파트"),
	HOUSE("단독주택"),
	PENSION("펜션");

	private final String value;
}

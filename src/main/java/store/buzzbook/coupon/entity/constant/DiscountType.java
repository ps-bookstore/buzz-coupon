package store.buzzbook.coupon.entity.constant;

public enum DiscountType {
	RATE, AMOUNT;

	public static DiscountType fromString(String value) {
		if (value == null) {
			throw new IllegalArgumentException("enum 값을 찾을 수 없습니다.");
		}

		return switch (value.toLowerCase()) {
			case "rate" -> RATE;
			case "amount" -> AMOUNT;
			default -> throw new IllegalArgumentException("enum 값을 찾을 수 없습니다.");
		};
	}
}

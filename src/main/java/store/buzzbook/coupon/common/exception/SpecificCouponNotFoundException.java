package store.buzzbook.coupon.common.exception;

public class SpecificCouponNotFoundException extends RuntimeException {

	public SpecificCouponNotFoundException() {
		super("특정 도서 쿠폰을 찾을 수 없습니다.");
	}

	public SpecificCouponNotFoundException(String message) {
		super(message);
	}
}

package store.buzzbook.coupon.common.exception;

public class CouponTypeNotFoundException extends RuntimeException {

	public CouponTypeNotFoundException() {
		super("쿠폰 타입을 찾을 수 없습니다.");
	}

	public CouponTypeNotFoundException(String message) {
		super(message);
	}
}

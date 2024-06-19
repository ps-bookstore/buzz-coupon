package store.buzzbook.coupon.common.exception;

public class CouponLogNotFoundException extends RuntimeException {

	public CouponLogNotFoundException() {
		super("쿠폰 로그를 찾을 수 없습니다.");
	}

	public CouponLogNotFoundException(String message) {
		super(message);
	}
}

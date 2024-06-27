package store.buzzbook.coupon.common.exception;

public class CouponNotFoundException extends RuntimeException {

	public CouponNotFoundException() {
		super("쿠폰 로그를 찾을 수 없습니다.");
	}

	public CouponNotFoundException(String message) {
		super(message);
	}
}

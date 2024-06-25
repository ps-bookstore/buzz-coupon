package store.buzzbook.coupon.common.exception;

public class CouponAlreadyExistsException extends RuntimeException {

	public CouponAlreadyExistsException() {
		super("이미 다운로드한 쿠폰입니다.");
	}

	public CouponAlreadyExistsException(String message) {
		super(message);
	}
}

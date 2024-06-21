package store.buzzbook.coupon.common.exception;

public class CouponLogAlreadyExistsException extends RuntimeException {

	public CouponLogAlreadyExistsException() {
		super("이미 다운로드한 쿠폰입니다.");
	}

	public CouponLogAlreadyExistsException(String message) {
		super(message);
	}
}

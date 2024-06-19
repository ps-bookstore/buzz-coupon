package store.buzzbook.coupon.common.exception;

public class CouponPolicyNotFoundException extends RuntimeException {

	public CouponPolicyNotFoundException() {
		super("쿠폰 정책을 찾을 수 없습니다.");
	}

	public CouponPolicyNotFoundException(String message) {
		super(message);
	}
}

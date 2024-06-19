package store.buzzbook.coupon.common.exception;

public class CategoryCouponNotFoundException extends RuntimeException {

	public CategoryCouponNotFoundException() {
		super("카테고리 쿠폰을 찾을 수 없습니다.");
	}

	public CategoryCouponNotFoundException(String message) {
		super(message);
	}
}

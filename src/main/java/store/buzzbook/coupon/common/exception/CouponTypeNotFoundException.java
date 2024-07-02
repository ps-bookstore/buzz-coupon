package store.buzzbook.coupon.common.exception;

/**
 * 쿠폰 타입을 찾을 수 없을 때 발생하는 예외 클래스입니다.
 * <p>
 * 이 예외는 런타임 시 쿠폰 타입이 존재하지 않음을 나타내기 위해 사용됩니다.
 * 기본 메시지와 사용자 정의 메시지를 모두 지원합니다.
 * </p>
 */
public class CouponTypeNotFoundException extends RuntimeException {

	/**
	 * 기본 메시지 "쿠폰 타입을 찾을 수 없습니다."를 사용하여 예외를 생성합니다.
	 */
	public CouponTypeNotFoundException() {
		super("쿠폰 타입을 찾을 수 없습니다.");
	}

	/**
	 * 지정된 메시지를 사용하여 예외를 생성합니다.
	 *
	 * @param message 예외의 세부 메시지
	 */
	public CouponTypeNotFoundException(String message) {
		super(message);
	}
}

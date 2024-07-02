package store.buzzbook.coupon.common.exception;

/**
 * 이미 존재하는 쿠폰을 다운로드하려고 할 때 발생하는 예외 클래스입니다.
 * <p>
 * 이 예외는 런타임 시 쿠폰이 이미 존재함을 나타내기 위해 사용됩니다.
 * 기본 메시지와 사용자 정의 메시지를 모두 지원합니다.
 * </p>
 */
public class CouponAlreadyExistsException extends RuntimeException {

	/**
	 * 기본 메시지 "이미 다운로드한 쿠폰입니다."를 사용하여 예외를 생성합니다.
	 */
	public CouponAlreadyExistsException() {
		super("이미 다운로드한 쿠폰입니다.");
	}

	/**
	 * 지정된 메시지를 사용하여 예외를 생성합니다.
	 *
	 * @param message 예외의 세부 메시지
	 */
	public CouponAlreadyExistsException(String message) {
		super(message);
	}
}

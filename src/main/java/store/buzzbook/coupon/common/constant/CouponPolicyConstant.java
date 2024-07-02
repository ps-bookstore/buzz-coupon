package store.buzzbook.coupon.common.constant;

/**
 * 쿠폰 정책과 관련된 상수를 포함하는 클래스입니다.
 * <p>
 * 웰컴 쿠폰, 생일 쿠폰과 같은 다양한 쿠폰 정책의 상수를 포함하고 있습니다.
 * 이 클래스는 인스턴스화할 수 없습니다.
 * </p>
 * <p>
 * <b>사용 예:</b>
 * <pre>
 * {@code
 * String welcomePolicy = CouponPolicyConstant.WELCOME_COUPON_POLICY_NAME;
 * String birthdayPolicy = CouponPolicyConstant.BIRTHDAY_COUPON_POLICY_NAME;
 * }
 * </pre>
 * </p>
 */
public final class CouponPolicyConstant {

	/**
	 * 웰컴 쿠폰 정책의 이름을 나타내는 상수입니다.
	 */
	public static final String WELCOME_COUPON_POLICY_NAME = "웰컴 쿠폰";

	/**
	 * 생일 쿠폰 정책의 이름을 나타내는 상수입니다.
	 */
	public static final String BIRTHDAY_COUPON_POLICY_NAME = "생일 쿠폰";

	/**
	 * 인스턴스화를 방지하기 위한 생성자입니다.
	 */
	private CouponPolicyConstant() {
	}
}

package store.buzzbook.coupon.dto.couponpolicy;

import store.buzzbook.coupon.entity.CouponPolicy;

/**
 * CouponPolicy 생성 후 응답을 위한 데이터 전송 객체 (DTO)입니다.
 * 이 레코드는 CouponPolicy 의 기본 정보를 캡슐화합니다.
 *
 * @param id CouponPolicy 의 고유 식별자
 * @param name CouponPolicy 의 이름
 */
public record CreateCouponPolicyResponse(
	int id,
	String name
) {
	/**
	 * 주어진 CouponPolicy 엔티티로부터 CreateCouponPolicyResponse 의 새로운 인스턴스를 생성합니다.
	 *
	 * @param request 응답을 생성할 CouponPolicy 엔티티
	 * @return 주어진 CouponPolicy 의 id와 name 을 포함하는 새로운 CreateCouponPolicyResponse
	 */
	public static CreateCouponPolicyResponse from(CouponPolicy request) {
		return new CreateCouponPolicyResponse(
			request.getId(),
			request.getName()
		);
	}
}

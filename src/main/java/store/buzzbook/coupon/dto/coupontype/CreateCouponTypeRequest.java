package store.buzzbook.coupon.dto.coupontype;

import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.common.constant.CouponRange;

public record CreateCouponTypeRequest(

	@NotNull(message = "쿠폰 타입의 이름이 null 일 수 없습니다.")
	CouponRange name
) {
	public CouponType toEntity() {
		return CouponType.builder()
			.name(name())
			.build();
	}
}

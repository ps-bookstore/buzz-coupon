package store.buzzbook.coupon.dto.coupontype;

import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.entity.CouponType;

public record CreateCouponTypeRequest(

	@NotNull
	String name
) {
	public CouponType toEntity() {
		return CouponType.builder()
			.name(name())
			.build();
	}
}

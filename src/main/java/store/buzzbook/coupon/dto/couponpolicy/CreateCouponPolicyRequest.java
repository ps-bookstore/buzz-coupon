package store.buzzbook.coupon.dto.couponpolicy;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 쿠폰 정책 생성 요청 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 쿠폰 정책의 이름, 할인 타입, 할인율, 할인 금액, 기준 금액, 최대 할인 금액, 기간, 시작일, 종료일, 쿠폰 타입, 대상 ID를 포함합니다.
 * </p>
 *
 * @param name 쿠폰 정책의 이름
 * @param discountType 쿠폰 정책의 할인 타입
 * @param discountRate 쿠폰 정책의 할인율 (0에서 1 사이)
 * @param discountAmount 쿠폰 정책의 할인 금액 (0에서 20000 사이)
 * @param standardPrice 쿠폰 정책의 기준 금액 (0에서 500000 사이)
 * @param maxDiscountAmount 쿠폰 정책의 최대 할인 금액 (0 이상)
 * @param period 쿠폰 정책의 지속 기간 (0 이상)
 * @param startDate 쿠폰 정책 다운로드 시작일
 * @param endDate 쿠폰 정책 다운로드 종료일
 * @param couponType 쿠폰 정책 타입
 * @param targetId 대상 ID (0 이상)
 */
public record CreateCouponPolicyRequest(

	@NotBlank(message = "쿠폰 정책의 이름은 공백이 불가합니다.")
	String name,

	@NotBlank(message = "쿠폰 정책의 할인 타입은 공백이 불가합니다.")
	String discountType,

	@Range(message = "할인율은 0에서 1 사이여야 합니다.")
	Double discountRate,

	@Max(value = 20000, message = "할인 금액은 20000 이하 입니다.")
	@Min(value = 0, message = "할인 금액은 0 이상 입니다.")
	Integer discountAmount,

	@Max(value = 500000, message = "기준 금액은 500000 이하 입니다.")
	@Min(value = 0, message = "기준 금액은 0 이상 입니다.")
	Integer standardPrice,

	@Min(value = 0, message = "최대 금액은 0 이상 입니다.")
	Integer maxDiscountAmount,

	@Min(value = 0, message = "쿠폰 지속 날짜는 0 이상 입니다.")
	Integer period,

	@NotNull(message = "쿠폰 정책 다운로드 날짜는 null 이 될 수 없습니다.")
	String startDate,

	@NotNull(message = "쿠폰 정책 다운로드 날짜는 null 이 될 수 없습니다.")
	String endDate,

	@NotBlank(message = "쿠폰 정책 타입은 공백이 불가 합니다.")
	String couponType,

	@Min(value = 0, message = "id 는 0 이상 입니다.")
	Integer targetId
) {
}

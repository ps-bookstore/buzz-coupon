package store.buzzbook.coupon.repository.couponpolicy.impl;

import static store.buzzbook.coupon.entity.QCouponPolicy.*;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.constant.CouponScope;
import store.buzzbook.coupon.common.constant.DiscountType;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyConditionRequest;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.QCouponPolicy;
import store.buzzbook.coupon.entity.QSpecificCoupon;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyQuerydslRepository;

@Slf4j
@Repository
public class CouponPolicyQuerydslRepositoryImpl extends QuerydslRepositorySupport
	implements CouponPolicyQuerydslRepository {

	public CouponPolicyQuerydslRepositoryImpl() {
		super(CouponPolicy.class);
	}

	@Override
	public List<CouponPolicy> findAllByBookId(int bookId) {
		QCouponPolicy couponPolicy = QCouponPolicy.couponPolicy;
		QSpecificCoupon specificCoupon = QSpecificCoupon.specificCoupon;

		return from(specificCoupon)
			.join(specificCoupon.couponPolicy, couponPolicy)
			.where(specificCoupon.bookId.eq(bookId), couponPolicy.deleted.eq(false))
			.select(couponPolicy)
			.fetch();
	}

	@Override
	public Page<CouponPolicy> findAllByCondition(CouponPolicyConditionRequest condition) {
		QCouponPolicy couponPolicy = QCouponPolicy.couponPolicy;

		List<CouponPolicy> couponPolicies = from(couponPolicy)
			.where(
				discountTypeEq(condition.discountTypeName()),
				isDeletedEq(condition.isDeleted()),
				couponTypeEq(condition.couponTypeName()))
			.offset(condition.pageable().getOffset())
			.limit(condition.pageable().getPageSize())
			.select(couponPolicy)
			.fetch();

		long total = from(couponPolicy)
			.where(
				discountTypeEq(condition.discountTypeName()),
				isDeletedEq(condition.isDeleted()),
				couponTypeEq(condition.couponTypeName()))
			.fetch().size();

		return new PageImpl<>(couponPolicies, condition.pageable(), total);
	}

	private BooleanExpression discountTypeEq(String discountType) {
		return !Objects.equals(discountType, "ALL") ? couponPolicy.discountType.eq(DiscountType.valueOf(discountType)) :
			null;
	}

	private BooleanExpression isDeletedEq(String isDeleted) {
		return !Objects.equals(isDeleted, "ALL") ? couponPolicy.deleted.eq(Boolean.valueOf(isDeleted)) :
			null;
	}

	private BooleanExpression couponTypeEq(String couponType) {
		return !Objects.equals(couponType, "ALL") ?
			couponPolicy.couponType.name.eq(CouponScope.fromString(couponType)) :
			null;
	}
}

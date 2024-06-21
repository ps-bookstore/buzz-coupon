package store.buzzbook.coupon.repository.couponpolicy.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.QCouponPolicy;
import store.buzzbook.coupon.entity.QSpecificCoupon;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyQuerydslRepository;

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
			.where(specificCoupon.bookId.eq(bookId))
			.select(couponPolicy)
			.fetch();
	}
}

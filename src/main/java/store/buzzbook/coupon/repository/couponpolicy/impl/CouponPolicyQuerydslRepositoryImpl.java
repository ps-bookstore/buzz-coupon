package store.buzzbook.coupon.repository.couponpolicy.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.QCategoryCoupon;
import store.buzzbook.coupon.entity.QCouponPolicy;
import store.buzzbook.coupon.entity.QSpecificCoupon;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyQuerydslRepository;

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

		return from(couponPolicy)
			.leftJoin(couponPolicy, specificCoupon.couponPolicy)
			.where(specificCoupon.bookId.eq(bookId))
			.fetch();
	}

	@Override
	public List<CouponPolicy> findAllByCategoryId(int categoryId) {

		QCouponPolicy couponPolicy = QCouponPolicy.couponPolicy;
		QCategoryCoupon categoryCoupon = QCategoryCoupon.categoryCoupon;

		return from(couponPolicy)
			.leftJoin(couponPolicy, categoryCoupon.couponPolicy)
			.where(categoryCoupon.categoryId.eq(categoryId))
			.fetch();
	}
}

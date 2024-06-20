package store.buzzbook.coupon.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.exception.CategoryCouponNotFoundException;
import store.buzzbook.coupon.common.exception.CouponLogNotFoundException;
import store.buzzbook.coupon.common.exception.CouponPolicyNotFoundException;
import store.buzzbook.coupon.common.exception.CouponTypeNotFoundException;
import store.buzzbook.coupon.common.exception.SpecificCouponNotFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
	public ResponseEntity<String> handleBadRequest(Exception exception) {
		log.warn("handleBadRequest : {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@ExceptionHandler({CategoryCouponNotFoundException.class, CouponLogNotFoundException.class,
		CouponPolicyNotFoundException.class, CouponTypeNotFoundException.class, SpecificCouponNotFoundException.class})
	public ResponseEntity<String> handleNotFound(Exception exception) {
		log.warn("handleNotFound : {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
}

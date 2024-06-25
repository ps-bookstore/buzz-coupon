package store.buzzbook.coupon.common.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.exception.CategoryCouponNotFoundException;
import store.buzzbook.coupon.common.exception.CouponAlreadyExistsException;
import store.buzzbook.coupon.common.exception.CouponNotFoundException;
import store.buzzbook.coupon.common.exception.CouponPolicyNotFoundException;
import store.buzzbook.coupon.common.exception.CouponTypeNotFoundException;
import store.buzzbook.coupon.common.exception.SpecificCouponNotFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<String> handleBadRequest(Exception exception) {
		log.warn("handleBadRequest : {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@ExceptionHandler({CategoryCouponNotFoundException.class, CouponNotFoundException.class,
		CouponPolicyNotFoundException.class, CouponTypeNotFoundException.class, SpecificCouponNotFoundException.class})
	public ResponseEntity<String> handleNotFound(Exception exception) {
		log.warn("handleNotFound : {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler({CouponAlreadyExistsException.class})
	public ResponseEntity<String> handleAlreadyExists(Exception exception) {
		log.warn("handleAlreadyExists : {}", exception.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((org.springframework.validation.FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		log.warn("handleMethodArgumentNotValid : {}", errors);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}

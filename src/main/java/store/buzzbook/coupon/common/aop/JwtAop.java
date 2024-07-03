package store.buzzbook.coupon.common.aop;

import java.util.Objects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.exception.AuthorizeFailException;
import store.buzzbook.coupon.common.service.AuthService;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class JwtAop {

	private final AuthService authService;
	private final HttpServletRequest request;

	@Before("@annotation(store.buzzbook.coupon.common.annotation.JwtValidate)")
	public void authenticate() {
		String authorizationHeader = request.getHeader("Authorization");

		if (Objects.isNull(authorizationHeader)) {
			throw new AuthorizeFailException();
		}

		Long userId = authService.getUserIdFromJwt(request);
		request.setAttribute(AuthService.USER_ID, userId);
	}
}

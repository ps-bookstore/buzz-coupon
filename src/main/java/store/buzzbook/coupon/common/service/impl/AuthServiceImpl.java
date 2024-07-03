package store.buzzbook.coupon.common.service.impl;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.adapter.AuthAdapter;
import store.buzzbook.coupon.common.exception.AuthorizeFailException;
import store.buzzbook.coupon.common.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
	private final AuthAdapter authAdapter;

	@Override
	public Map<String, Object> getInfoMapFromJwt(HttpServletRequest request) {
		String accessToken = request.getHeader(TOKEN_HEADER);
		String refreshToken = request.getHeader(REFRESH_HEADER);

		ResponseEntity<Map<String, Object>> responseEntity = authAdapter.getUserInfo(accessToken, refreshToken);

		if (Objects.isNull(responseEntity.getBody())) {
			log.debug("토큰 인증에 실패 했습니다. : null point exception");
			throw new AuthorizeFailException("Invalid access token");
		}

		if (responseEntity.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
			throw new AuthorizeFailException((String)responseEntity.getBody().get(ERROR),
				(String)responseEntity.getBody().get(MESSAGE));
		}

		return responseEntity.getBody();
	}

	@Override
	public Long getUserIdFromJwt(HttpServletRequest request) {
		Map<String, Object> claims = getInfoMapFromJwt(request);
		return (Long)claims.get(USER_ID);
	}

}

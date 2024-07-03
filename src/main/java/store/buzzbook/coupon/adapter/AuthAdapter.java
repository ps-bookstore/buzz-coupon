package store.buzzbook.coupon.adapter;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import store.buzzbook.coupon.common.service.AuthService;

@FeignClient(name = "authClient", url = "http://${api.gateway.host}:" + "${api.gateway.port}/api/auth")
public interface AuthAdapter {
	@GetMapping("/info")
	ResponseEntity<Map<String, Object>> getUserInfo(
		@RequestHeader(value = AuthService.TOKEN_HEADER, required = false) String accessToken,
		@RequestHeader(value = AuthService.REFRESH_HEADER, required = false) String refreshToken);
}

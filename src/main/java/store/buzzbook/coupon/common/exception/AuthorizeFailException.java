package store.buzzbook.coupon.common.exception;

public class AuthorizeFailException extends RuntimeException {
	public AuthorizeFailException() {
		super("회원 인증에 실패 했습니다.");
	}

	public AuthorizeFailException(String message) {
		super(message);
	}

	public AuthorizeFailException(String error, String message) {
		super(String.format("%s: %s", error, message));
	}

}

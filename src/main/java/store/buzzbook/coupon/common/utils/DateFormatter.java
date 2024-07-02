package store.buzzbook.coupon.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 날짜 문자열을 LocalDate 객체로 변환하는 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 "yyyy-MM-dd" 형식의 날짜 문자열을 LocalDate 로 변환하는 메서드를 제공합니다.
 * </p>
 */
public class DateFormatter {

	/**
	 * 이 클래스는 인스턴스화할 수 없습니다.
	 * <p>
	 * 유틸리티 클래스이므로, 인스턴스화하려고 하면 IllegalStateException 이 발생합니다.
	 * </p>
	 */
	private DateFormatter() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * "yyyy-MM-dd" 형식의 날짜 문자열을 LocalDate 객체로 변환합니다.
	 * <p>
	 * 만약 주어진 문자열이 올바른 형식이 아닐 경우 IllegalArgumentException 을 발생시킵니다.
	 * </p>
	 *
	 * @param date 변환할 날짜 문자열
	 * @return 변환된 LocalDate 객체
	 * @throws IllegalArgumentException 날짜 문자열이 올바른 형식이 아닐 경우
	 */
	public static LocalDate toLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			return LocalDate.parse(date, formatter);

		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("올바른 날짜 형식이 아닙니다.");
		}
	}
}

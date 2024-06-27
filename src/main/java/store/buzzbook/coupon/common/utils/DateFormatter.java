package store.buzzbook.coupon.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {

	private DateFormatter() {
		throw new IllegalStateException("Utility class");
	}

	public static LocalDate toLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			return LocalDate.parse(date, formatter);

		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException();
		}
	}
}

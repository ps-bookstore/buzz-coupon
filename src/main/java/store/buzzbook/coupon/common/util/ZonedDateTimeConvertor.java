package store.buzzbook.coupon.common.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeConvertor {

	public static ZonedDateTime toZonedDateTime(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
		return ZonedDateTime.parse(date, formatter);
	}
}

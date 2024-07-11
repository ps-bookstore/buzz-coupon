package store.buzzbook.coupon.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogNCrash {
	private static final Logger USER_LOG = LoggerFactory.getLogger("user-logger");

	public void logging() {
		USER_LOG.debug("LogNCrash Debug.");

		// Log & Crash 에서 예약된 항목 이외, 사용자 정의 항목 사용 시 MDC 활용

		try {
			String logncrash = null;
			if (true) {
				System.out.print(logncrash.toString());
			}
		} catch (Exception e) {
			USER_LOG.error("LogNCrash Exception.", e);
		}
	}
}

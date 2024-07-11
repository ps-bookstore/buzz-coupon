package store.buzzbook.coupon.common.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import store.buzzbook.coupon.adapter.LogNCrashAdapter;
import store.buzzbook.coupon.dto.log.LogNCrashRequest;

public class LogNCrashAppender extends AppenderBase<ILoggingEvent> {

	private String appKey;
	private LogNCrashAdapter logNCrashAdapter;

	public LogNCrashAppender(String appKey, LogNCrashAdapter logNCrashAdapter) {
		this.appKey = appKey;
		this.logNCrashAdapter = logNCrashAdapter;
	}

	@Override
	protected void append(ILoggingEvent iLoggingEvent) {
		LogNCrashRequest request = createLogNCrashRequest(iLoggingEvent);
		logNCrashAdapter.sendLog(request);
	}

	private LogNCrashRequest createLogNCrashRequest(ILoggingEvent iLoggingEvent) {
		return LogNCrashRequest.builder()
			.projectName(appKey)
			.projectVersion("1.0.0")
			.logVersion("v2")
			.body(iLoggingEvent.getFormattedMessage())
			.logSource("aa")
			.logType("aa")
			.host("aa-coupon-api")
			.build();
	}
}

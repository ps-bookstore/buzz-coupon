package store.buzzbook.coupon.common.appender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import store.buzzbook.coupon.adapter.LogNCrashAdapter;
import store.buzzbook.coupon.dto.log.LogNCrashRequest;

@Component
public class LogNCrashAppender extends AppenderBase<ILoggingEvent> {

	@Value("${logncrash.appkey}")
	private String appKey;

	private LogNCrashAdapter logNCrashAdapter;

	public LogNCrashAppender() {
	}

	@Autowired
	public LogNCrashAppender(LogNCrashAdapter logNCrashAdapter) {
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
			.body(iLoggingEvent.getMessage())
			.logSource("aa")
			.logType("aa")
			.build();
	}
}

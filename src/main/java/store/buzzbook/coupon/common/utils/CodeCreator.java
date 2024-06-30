package store.buzzbook.coupon.common.utils;

import java.security.SecureRandom;

public class CodeCreator {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int GROUP_LENGTH = 4;
	private static final int GROUP_COUNT = 3;
	private static final SecureRandom random = new SecureRandom();

	private CodeCreator() {
		throw new IllegalStateException("Utility class");
	}

	public static String createCode() {
		StringBuilder serial = new StringBuilder(GROUP_LENGTH * GROUP_COUNT + (GROUP_COUNT - 1));
		for (int i = 0; i < GROUP_COUNT; i++) {
			if (i > 0) {
				serial.append('-');
			}
			for (int j = 0; j < GROUP_LENGTH; j++) {
				int index = random.nextInt(CHARACTERS.length());
				serial.append(CHARACTERS.charAt(index));
			}
		}
		return serial.toString();
	}
}

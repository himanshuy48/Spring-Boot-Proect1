package com.iot.app.util;

import java.util.Random;

public class RandomCodeGenerator {

	public static String verificationCodeGenerator() throws Exception {

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();

//		String generatedString = random.ints(leftLimit, rightLimit + 1)
//				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//				.limit(targetStringLength)
//				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//				.toString();
//
//		return generatedString;

		random.setSeed(System.currentTimeMillis());

		Integer num = random.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) {
			num = random.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999) {
				throw new Exception("Unable to generate PIN at this time..");
			}
		}
		return num.toString();
	}
}

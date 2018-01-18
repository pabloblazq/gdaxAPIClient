package com.blame.gdaxAPIClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Test {

	public static void main(String[] args) {
		SimpleDateFormat ISO_8601_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS000'Z'");
		ISO_8601_DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date dateToSign = new Date();
		String timestamp = ISO_8601_DATEFORMAT.format(dateToSign);
		System.out.println(timestamp);
	}

}

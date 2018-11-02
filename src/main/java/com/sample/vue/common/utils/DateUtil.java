package com.sample.vue.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Timestamp convertToTimestamp(String format, String stringDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	    Date parsedDate = dateFormat.parse( stringDate);
	    Timestamp timestamp = new Timestamp(parsedDate.getTime());
		return timestamp;
	}

}

package com.elink.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String now() {
		return sdf.format(new Date());
	}

	public static String format(Date date) {
		return sdf.format(date);
	}
	
	public static Date parse(String d) {
		try {
			return sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(now());
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());

		System.out.println(format(new Date(1483273323181L)));
		System.out.println(format(new Date(TimeUnit.NANOSECONDS.toMillis(373133139332773L))));

		long startTime = System.nanoTime();
		// ... the code being measured ...
		long sum1 = 0;
		for (int i = 0; i < 1000000000; i++) {
			sum1 += i;
		}
		long estimatedTime = System.nanoTime() - startTime;
		System.out.printf("sum: %d, cost: %dms%n", sum1, TimeUnit.NANOSECONDS.toMillis(estimatedTime));
		
		long start = System.currentTimeMillis();
		// ... the code being measured ...
		long sum2 = 0;
		for (int i = 0; i < 1000000000; i++) {
			sum2 += i;
		}
		long duration = System.currentTimeMillis() - start;
		System.out.printf("sum: %d, cost: %dms%n", sum2, duration);
		
		String d1 = "2017-01-06 15:21:56";
		String d2 = "2017-01-07 18:25:22";
		long total = parse(d2).getTime() - parse(d1).getTime();
		System.out.println(total);
		System.out.println(total / 1588);
		System.out.println(TimeUnit.MILLISECONDS.toDays(total));
		System.out.println(TimeUnit.MILLISECONDS.toHours(total) % 24);
		System.out.println(TimeUnit.MILLISECONDS.toMinutes(total) % 60);
		System.out.println(TimeUnit.MILLISECONDS.toSeconds(total) % 60);
	}
}

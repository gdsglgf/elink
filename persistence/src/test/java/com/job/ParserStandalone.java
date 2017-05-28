package com.job;

import java.io.BufferedReader;
import java.util.Map;

import com.elink.util.IOUtils;
import com.elink.util.PageParser;

public class ParserStandalone {
	public static void benchmark() {
		long startTime = System.currentTimeMillis();

		String line = null;
		StringBuilder sb = new StringBuilder();
		Map<String, Object> result;
		try (BufferedReader reader = IOUtils.getBufferedReaderForCompressedFile(Configs.FILE_INPUT)) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				if (line.contains("</doc>")) {
					result = PageParser.parseForMap(sb.toString());
					sb = null;
					result = null;
					sb = new StringBuilder();
				}
			}
		} catch (Exception e) {
			
		}

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.printf("start: %d, end: %d, cost time: %d%n", startTime, endTime, duration);
	}

	// start: 1489543521174, end: 1489543825908, cost time: 304734
	// start: 1489546626644, end: 1489546901121, cost time: 274477
	public static void main(String[] args) {
		int n = 50;
		for (int i = 0; i < n; i++) {
			System.out.printf("batch:%2d, ", i);
			benchmark();
		}
	}

}

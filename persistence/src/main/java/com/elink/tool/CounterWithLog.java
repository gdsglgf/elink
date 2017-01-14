package com.elink.tool;

import java.io.BufferedReader;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.elink.util.Config;
import com.elink.util.IOUtils;

public class CounterWithLog {
	private static Logger log = Logger.getLogger(CounterWithLog.class);

	public static void main(String[] args) {
		log.debug("start counter...");
		for (int i = 1106; i < 4096; i++) {
			String filename = String.format(Config.getDataFileFormat(), i);
			log.debug(String.format("start %s", filename));
			long start = System.nanoTime();
			int total = 0;
			String line = null;
			try (BufferedReader reader = IOUtils.getBufferedReaderForCompressedFile(filename)) {
				while ((line = reader.readLine()) != null) {
					if (line.contains("<doc>")) {
						total++;
					}
				}
			} catch (Exception e) {
				continue;
			}
			long end = System.nanoTime();

			String data = String.format("%s,%d,%d,%d", filename, total, start, end);
			log.info(data);
			
			long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
			String message = String.format("%s, total: %d files, cost %d ms, %.2f files/second, %.2f ms/file",
					filename, total, duration, total / (duration / 1000.0), duration * 1.0 / total);
			log.debug(message);
		}
		log.debug("end counter...");
	}

}

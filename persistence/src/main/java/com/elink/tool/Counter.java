package com.elink.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import com.elink.util.Config;
import com.elink.util.DateUtils;
import com.elink.util.IOUtils;

public class Counter {

	public static void main(String[] args) throws Exception {
		String file = Config.getDataCounterOutputPath();
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));

		int startIndex = Config.getDataFileStart();
		int endIndex = Config.getDataFileEnd();
		String fileFormat = Config.getDataFileFormat();
		for (int i = startIndex; i < endIndex; i++) {
			String filename = String.format(fileFormat, i);
			System.out.printf("%s, %s%n", DateUtils.now(), filename);
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

			String data = String.format("%s,%d,%d,%d%n", filename, total, start, end);
			out.write(data);
			out.flush();
			
			long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
			String message = String.format("%s: %s, total: %d files, cost %d ms, %.2f files/second, %.2f ms/file",
					DateUtils.now(), filename, total, duration, total / (duration / 1000.0), 
					duration * 1.0 / total);
			System.out.println(message);
		}
		out.close();
	}
}

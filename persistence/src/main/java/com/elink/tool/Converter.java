package com.elink.tool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.elink.util.Config;

public class Converter {

	public static void main(String[] args) {
		String fileName = Config.getDataCounterOutputPath();

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(line -> {
				String[] items = line.split(",");
				long duration = Long.parseLong(items[3]) - Long.parseLong(items[2]);
				long cost = TimeUnit.NANOSECONDS.toMillis(duration);
				System.out.printf("%s,%s,%d%n", items[0], items[1], cost);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

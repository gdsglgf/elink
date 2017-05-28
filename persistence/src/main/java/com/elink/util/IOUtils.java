package com.elink.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public class IOUtils {
	/**
	 * 获取压缩文件的BufferedReader
	 * @param fileIn  文件路径名
	 * @return
	 */
	public static BufferedReader getBufferedReaderForCompressedFile(String fileIn) {
		BufferedReader br2 = null;
		try {
			FileInputStream fin = new FileInputStream(fileIn);
			BufferedInputStream bis = new BufferedInputStream(fin);
			CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(bis);
			br2 = new BufferedReader(new InputStreamReader(input));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br2;
	}
	
	/**
	 * 
	 * @param filename  文件路径名
	 * @param consumer    文件内容消费者
	 * @return   文件的个数
	 */
	public static int split(String filename, IConsumer consumer) {
		int cnt = 0;
		String line = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = getBufferedReaderForCompressedFile(filename)) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				if (line.contains("</doc>")) {
					cnt++;
					consumer.consume(sb.toString(), cnt);
					sb = new StringBuilder();
				}
			}
		} catch (IOException e) {
//			e.printStackTrace();  // when EOF java.io.IOException: bad block header
		}
		return cnt;
	}
	
	/**
	 * 
	 * @param filename  文件路径名
	 * @return   文件的个数
	 */
	public static int split(String filename) {
		int cnt = 0;
		String line = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = getBufferedReaderForCompressedFile(filename)) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				if (line.contains("</doc>")) {
					cnt++;
					sb.toString();
					sb = new StringBuilder();
				}
			}
		} catch (IOException e) {
//			e.printStackTrace();  // when EOF java.io.IOException: bad block header
		}
		return cnt;
	}
	
	public static void benchmark(String filename) {
		long start = System.nanoTime();
		int cnt = split(filename);
		long end = System.nanoTime();
		long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		String message = String.format("%s: %s, total: %d files, cost %d ms, %.1f files/second, %.1f ms/file", 
				DateUtils.now(), filename, cnt, duration,  cnt / (duration / 1000.0), duration * 1.0 / cnt);
		System.out.println(message);
	}
	
	public static void benchmark(String filename, IConsumer consumer) {
		long start = System.nanoTime();
		int cnt = split(filename, consumer);
		long end = System.nanoTime();
		long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		String message = String.format("%s: %s, total: %d files, cost %d ms, %.1f files/second, %.1f ms/file", 
				DateUtils.now(), filename, cnt, duration,  cnt / (duration / 1000.0), duration * 1.0 / cnt);
		System.out.println(message);
	}
	
	public static void countFileBenchmark(String filename) {
		long start = System.nanoTime();
		
		int cnt = 0;
		String line = null;
		try (BufferedReader reader = getBufferedReaderForCompressedFile(filename)) {
			while ((line = reader.readLine()) != null) {
				if (line.contains("<doc>")) {
					cnt++;
				}
			}
		} catch (IOException e) {

		}
		
		long end = System.nanoTime();
		long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		String message = String.format("%s: %s, total: %d files, cost %d ms, %.1f files/second, %.1f ms/file", 
				DateUtils.now(), filename, cnt, duration,  cnt / (duration / 1000.0), duration * 1.0 / cnt);
		System.out.println(message);
	}
	
	public static void main(String[] args) {
		String filename = Config.getTestDataFile();
		benchmark(filename);
//		benchmark(filename, new IConsumer() {});
//		countFileBenchmark(filename);
	}
}

package com.job;

import java.io.BufferedReader;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.elink.util.IOUtils;
import com.elink.util.PageParser;

public class ParserUsingQueue {
	public static void benchmark() throws InterruptedException {
		long startTime = System.currentTimeMillis();

		final BlockingQueue<String> docQueue = new LinkedBlockingQueue<String>();

		Thread spliter = new Thread(new Runnable() {
			@Override
			public void run() {
				String line = null;
				StringBuilder sb = new StringBuilder();
				try (BufferedReader reader = IOUtils.getBufferedReaderForCompressedFile(Configs.FILE_INPUT)) {
					while ((line = reader.readLine()) != null) {
						sb.append(line);
						if (line.contains("</doc>")) {
							if (!docQueue.offer(sb.toString(), 2, TimeUnit.SECONDS)) {
								System.out.println("放入数据失败：\n" + sb);
							}
							sb = null;
							sb = new StringBuilder();
						}
					}
				} catch (Exception e) {
					
				}
			}
		});

		spliter.start();

		Thread parser = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String data;
					Map<String, Object> result;
					while (true) {
						data = (String) docQueue.poll(2, TimeUnit.SECONDS);
						if (null != data) {
							result = PageParser.parseForMap(data);
							result = null;
						} else {
							// 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
							break;
						}
					}
				} catch (InterruptedException e) {
					
				}
			}
		});

		parser.start();

		spliter.join();
		parser.join();

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.printf("start: %d, end: %d, cost time: %d%n", startTime, endTime, duration);
	}

	// java.lang.OutOfMemoryError: GC overhead limit exceeded
	// start: 1489545024438, end: 1489545292536, cost time: 268098
	// start: 1489545358328, end: 1489545609349, cost time: 251021
	// start: 1489546303940, end: 1489546506567, cost time: 202627
	// start: 1489552426043, end: 1489552577237, cost time: 151194
	public static void main(String[] args) throws InterruptedException {
		int n = 10;
		for (int i = 0; i < n; i++) {
			benchmark();
		}
	}

}

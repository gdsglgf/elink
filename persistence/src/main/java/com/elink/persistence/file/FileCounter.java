package com.elink.persistence.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.elink.util.Config;
import com.elink.util.DataFileUtils;
import com.elink.util.DateUtils;
import com.elink.util.IOUtils;

class Worker implements Runnable {
	public static int FIRST_INDEX = Config.getDataFileStart();
	private int bin;
	private boolean end;

	public Worker(int bin) {
		this.bin = bin;
		this.end = false;
	}

	public int getBin() {
		return bin;
	}

	public void setBin(int bin) {
		this.bin = bin;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	@Override
	public void run() {
		System.out.printf("start %d thread...%n", bin);
		for (int i = FIRST_INDEX + bin; i < FileCounter.length; i += FileCounter.nthread) {
			String filename = FileCounter.files.get(i);
			System.out.printf("%s, thread:%d, %s%n", bin, DateUtils.now(), filename);
			long start = System.nanoTime();
			int cnt = 0;
			String line = null;
			try (BufferedReader reader = IOUtils.getBufferedReaderForCompressedFile(filename)) {
				while ((line = reader.readLine()) != null) {
					if (line.contains("<doc>")) {
						cnt++;
					}
				}
			} catch (IOException e) {

			}
			long end = System.nanoTime();

			// System.out.printf("set %d bins%n", i);
			FileCounter.counter[i] = cnt;
			FileCounter.startTime[i] = start;
			FileCounter.endTime[i] = end;
			FileCounter.showMessage(i);
		}
		this.end = true;
	}
}

public class FileCounter {
	public static List<String> files;
	public static int length;
	public static int nthread;
	public static int[] counter;
	public static long[] startTime;
	public static long[] endTime;

	public static void showMessage(int i) {
		String filename = files.get(i);
		int cnt = counter[i];
		long start = startTime[i];
		long end = endTime[i];
		long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		String message = String.format("%s: %s, total: %d files, cost %d ms, %.1f files/second, %.1f ms/file",
				DateUtils.now(), filename, cnt, duration, cnt / (duration / 1000.0), duration * 1.0 / cnt);
		System.out.println(message);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("loading data...");
		files = DataFileUtils.loadAllFiles();
		length = files.size();
		System.out.printf("load %d files%n", length);
		nthread = 3;
		counter = new int[length];
		startTime = new long[length];
		endTime = new long[length];

		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < nthread; i++) {
			service.execute(new Worker(i));
		}

		String file = Config.getDataCounterOutputPath();
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));

		int i = Worker.FIRST_INDEX;
		while (i < length) {
			if (counter[i] != 0) {
				while (counter[i] != 0) {
					// save and show
					String data = String.format("%s,%d,%d,%d%n", files.get(i), counter[i], startTime[i], endTime[i]);
					out.write(data);
					i++;
				}
				out.flush();
				System.out.printf("save data to %d...%n", i);
			}

			// 执行3min
			Thread.sleep(3 * 60 * 1000);
		}

		out.close();
		service.shutdown();
	}

}

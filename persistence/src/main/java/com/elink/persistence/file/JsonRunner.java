package com.elink.persistence.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;

import com.elink.util.Config;
import com.elink.util.IOUtils;

public class JsonRunner {
	private static Writer out;
	
	public static void run(String batch) {
		System.out.println(String.format("parsing %s...", batch));
		String output = batch.replace(Config.getDataHome(), Config.getDataOutputPath()).replace(Config.getDataFileSuffix(), "json");
		PageComsumer pageComsumer = new PageComsumer(output);
		long start = System.currentTimeMillis();
		int cnt = IOUtils.split(batch, pageComsumer);
		long duration = System.currentTimeMillis() - start;
		pageComsumer.close();
		String message = String.format("%s -> %s, total: %d files, cost %d ms, %.1f files/second, %.1f ms/file", 
				batch, output, cnt, duration,  cnt / (duration / 1000.0), duration * 1.0 / cnt);
		System.out.println(message);
		try {
			out.write(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("report.log"), "utf-8"));;
		
		String root = Config.getDataHome();
		String outputPath = Config.getDataOutputPath();
		File home = new File(root);
		Arrays.asList(home.list()).stream()
		.filter(name -> name.contains(Config.getDataDirKey()))
		.map(f -> {
				String dirPath = outputPath + "/" + f;
				File dir = new File(dirPath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				return root + f;
			}
		)
		.flatMap(f -> Arrays.asList(new File(f).list())
					.stream().map(p -> f + "/" + p)
					.filter(a -> a.endsWith(Config.getDataFileSuffix())))
		.forEach(JsonRunner::run);
		
		out.flush();
		out.close();
	}
}

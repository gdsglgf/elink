package com.elink.persistence.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.elink.persistence.Constants;
import com.elink.util.Config;
import com.elink.util.IConsumer;
import com.elink.util.IOUtils;
import com.elink.util.JacksonUtils;
import com.elink.util.PageParser;

public class PageComsumer implements IConsumer {
	public PageComsumer(String filename) {
		try {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void consume(String html, int rank) {
		Map<String, Object> pageMap = PageParser.parseToMap(html, rank);
		try {
			out.write(JacksonUtils.toJSon(pageMap));
			out.write(Constants.LINE_SEPARATOR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Writer out;
	
	public static void main(String[] args) {
		String path = Config.getTestDataFile();
		IOUtils.split(path, new PageComsumer(path));
	}
}

package com.miner.parser;

import com.miner.model.WebPage;
import com.miner.util.IOUtils;
import com.miner.util.Runner;
import com.miner.util.WebPageParser;

public class JSONParser implements IParser {
	private String filePath;
	
	public JSONParser(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void parse(String html, int index) {
		WebPage wp = WebPageParser.parse(html);
		IOUtils.appendToFile(wp.toJSONString() + "\n", filePath);
		System.out.printf("-------------file:%d\n", index);
		System.out.println(wp);
	}

	public static void main(String[] args) {
		Runner.benchmark(new JSONParser("log.json"));
	}
}

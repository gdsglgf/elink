package com.miner.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.miner.util.Runner;

public class MetaParser implements IParser {
	public static final Pattern META_PATTERN = Pattern.compile("(<meta[\\w\\W]+?>)");

	@Override
	public void parse(String html, int index) {
		System.out.printf("-------------file:%d\n", index);
		Matcher m = META_PATTERN.matcher(html);
		while (m.find()) {
			System.out.println(m.group(1));
		}
	}

	public static void main(String[] args) {
		Runner.benchmark(new MetaParser());
	}
}

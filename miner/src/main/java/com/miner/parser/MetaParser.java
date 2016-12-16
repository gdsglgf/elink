package com.miner.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.miner.util.Runner;

/**
 * Parse all meta tag
 * @author Luo Guofu
 *
 */
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
		//Runner.benchmark(new MetaParser());
		int index=1;
		String html="<doc><docno>123456</docno><url>http://www.test.com/</url><!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <meta name=\"keywords\" content=\"一千零一夜 天方夜谭 阿拉伯之夜\" />";
		new MetaParser().parse(html, index);
	}
}

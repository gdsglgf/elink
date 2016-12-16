package com.miner.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.miner.util.Runner;

/**
 * Parse html charset
 * @author Luo Guofu
 *
 */
public class CharsetParser implements IParser {
	public static final Pattern CHARSET_PATTERN = Pattern.compile("(?:charset|Charset|CHARSET)\\s*=\\s*\"?\\s*([-\\w]*?)[^-\\w]");
	public static final String DEFAULT_CHARSET = "UTF-8";

	@Override
	public void parse(String html, int index) {
		String encode = "";
		Matcher m = CHARSET_PATTERN.matcher(html);
		if (m.find()) {
			encode = m.group(1);
		}
		System.out.printf("file: %d, charset:[%s]\n", index, encode);
	}
	
	public static void main(String[] args) {
		//Runner.benchmark(new CharsetParser());
		//String html="<doc><docno>123456</docno><url>http://www.test.com/</url><!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <meta name=\"keywords\" content=\"一千零一夜 天方夜谭 阿拉伯之夜\" />";
		//int index = 1 ;
		//new CharsetParser().parse(html, index);
	}

}

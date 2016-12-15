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
		Runner.benchmark(new CharsetParser());
	}

}

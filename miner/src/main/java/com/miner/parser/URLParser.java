package com.miner.parser;

import com.miner.util.ParserUtils;
import com.miner.util.Runner;

/**
 * Parse html url tag
 * @author Luo Guofu
 *
 */
public class URLParser implements IParser {
	@Override
	public void parse(String html, int index) {
		System.out.printf("-------------file:%d\n", index);
		System.out.println(ParserUtils.parseUrl(html));
	}

	public static void main(String[] args) {
		Runner.benchmark(new URLParser());
	}
}

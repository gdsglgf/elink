package com.miner.parser;

import com.miner.util.ParserUtils;
import com.miner.util.Runner;

/**
 * Parse html charset
 * @author Luo Guofu
 *
 */
public class CharsetParser implements IParser {
	@Override
	public void parse(String html, int index) {
		String encode = ParserUtils.parseCharset(html);
		System.out.printf("file: %d, charset:[%s]\n", index, encode);
	}
	
	public static void main(String[] args) {
		Runner.benchmark(new CharsetParser());
	}

}

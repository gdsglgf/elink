package com.miner.util;

import com.miner.parser.IParser;

/**
 * parser运行时间测试类
 * @author Luo Guofu
 *
 */
public class Runner {
	public static String TEST_FILE = "src/test/data/test.bz2";
	
	public static void benchmark(IParser parser) {
		long start = System.currentTimeMillis();
		int cnt = BZ2Splitter.split(TEST_FILE, parser);
		long duration = System.currentTimeMillis() - start;
		System.out.println(String.format("total: %d files, cost %d ms", cnt, duration));
	}
}

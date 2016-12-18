package com.miner;

import org.junit.Test;

import com.miner.parser.MetaParser;
import com.miner.util.IOUtils;

public class MetaParserTest {
	public static MetaParser parser = new MetaParser();
	
	@Test
	public void testFromFile() {
		String html = IOUtils.fileToString("src/test/data/test.html");
		parser.parse(html, 0);
	}
}

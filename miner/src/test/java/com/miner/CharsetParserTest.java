package com.miner;

import org.junit.Test;

import com.miner.parser.CharsetParser;
import com.miner.util.IOUtils;

public class CharsetParserTest {
	public static CharsetParser parser = new CharsetParser();
	
	@Test
	public void testFromString() {
		String html = "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>";
		parser.parse(html, 0);
	}
	
	@Test
	public void testFromFile() {
		String html = IOUtils.fileToString("src/test/data/test.html");
		parser.parse(html, 0);
	}
}

package com.miner;

import static org.junit.Assert.*;

import java.io.BufferedReader;

import org.junit.Before;
import org.junit.Test;

import com.miner.parser.JsoupParser;
import com.miner.util.IOUtils;

public class JsoupParserTest {
    private static JsoupParser jp=new JsoupParser();
	@Before
	

	@Test
	public void testfromString() {
		String html="<doc>"
				+ "<docno>0015e77674b7604f-07dd0ee2a1c1ef92-469b9cc3ce78f64d392776be46976e8f</docno>"
				+ "<url>http://www.test.com/</url>"
				+ "<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">"
				+ "<head>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> "
				+ "<title>tales from the thousand and one nights</title>"
				+ "<meta name=\"keywords\" content=\"一千零一夜 天方夜谭 阿拉伯之夜\" />"
				+ "<meta name=\"description\" content=\"Once upon a time..\" />"
				+ " </head>"
				+ "</html>"
				+ "</doc>";
		jp.parse(html, 0);
		
	}
	@Test
	public void testFromFile() {
		String html = IOUtils.fileToString("src/test/data/test.html");
		jp.parse(html, 1);
		//html=IOUtils.toString(new BufferedReader());
		jp.parse(html, 2);
	}

}

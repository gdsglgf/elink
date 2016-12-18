package com.miner;

import org.junit.Test;

import com.miner.parser.JsoupApiParser;
import com.miner.util.IOUtils;

public class JsoupApiParserTest {
    private static JsoupApiParser jap = new JsoupApiParser();
    private static int mockIndex = 0;

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
		jap.parse(html, mockIndex);
	}

    @Test
	public void testFromFile() {
		String html = IOUtils.fileToString("src/test/data/test.html");
		jap.parse(html, mockIndex);
	}
    
    @Test
    public void testMeta() {
    	String html = "<meta name=\"Keywords\" content=\"one\" /><meta content=\"two\" name=\"Description\" />";
    	jap.parse(html, mockIndex);
    }
    
    @Test
    public void testEmpty() {
    	String html = "<doc></doc>";
    	jap.parse(html, mockIndex);
    }
}

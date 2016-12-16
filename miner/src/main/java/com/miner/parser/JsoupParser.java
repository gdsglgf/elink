package com.miner.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.miner.util.Runner;

public class JsoupParser implements IParser {
    
	public static final Pattern DOCNO_PATTERN=Pattern.compile("(<docno>[0-9a-z\\-]+?</docno>)");
	public static final Pattern URL_PATTERN=Pattern.compile("(<url>(http://|https://){1}(.+?)</url>)");
	public static final Pattern TITLE_PATTERN=Pattern.compile("(<title>(.+?)</title>)");
	public static final Pattern KEYWORDS_PATTERN=Pattern.compile("(<meta((\\s)*?)name=\"keywords\"((\\s)*?)content=\"(.*?)\"((\\s)*?) />)");
	public static final Pattern DESCRIPTION_PATTERN=Pattern.compile("(<meta((\\s)*?)name=\"description\"((\\s)*?)content=\"(.*?)\"((\\s)*?) />)");
	
	@Override
	public void parse(String html, int index) {
		System.out.printf("-------------file:%d\n", index);
	//	Matcher m= DESCRIPTION_PATTERN.matcher(html);
		//while (m.find()) {
			//System.out.println(m.group(1));
		//}
		Matcher[] m=new Matcher[5];
		m[0]=DOCNO_PATTERN.matcher(html);
		while (m[0].find()) {
		System.out.println(m[0].group(1));
	    }
		m[1]=URL_PATTERN.matcher(html);
		while (m[1].find()) {
		System.out.println(m[1].group(1));
	    }
		m[2]=TITLE_PATTERN.matcher(html);
		while (m[2].find()) {
		System.out.println(m[2].group(1));
	    }
		m[3]=KEYWORDS_PATTERN.matcher(html);
		while (m[3].find()) {
		System.out.println(m[3].group(1));
	    }
		m[4]=DESCRIPTION_PATTERN.matcher(html);
		while (m[4].find()) {
		System.out.println(m[4].group(1));
	    }
		
	}

	public static void main(String[] args) {
		Runner.benchmark(new JsoupParser());
		//String html="<doc><docno>0015e77674b7604f-07dd0ee2a1c1ef92-469b9cc3ce78f64d392776be46976e8f</docno><url>http://www.test.com/</url><!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <title>tales from the thousand and one nights</title><meta name=\"keywords\" content=\"一千零一夜 天方夜谭 阿拉伯之夜\" /><meta name=\"description\" content=\"Once upon a time..\" /> ";
		//int index = 1 ;
		//new JsoupParser().parse(html, index);
		}
}

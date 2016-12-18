package com.miner.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.miner.util.ParserUtils;
import com.miner.util.Runner;

public class JsoupApiParser implements IParser {
	@Override
	public void parse(String html, int index) {
		System.out.printf("-------------file:%d\n", index);
		String docno = "",
				url = "",
				title = "",
				keywords = "",
				description = "";
		try {
			Document doc = Jsoup.parse(html);
			docno = doc.getElementsByTag("docno").text();
			url = doc.getElementsByTag("url").text();
			title = doc.getElementsByTag("title").text();
			keywords = doc.getElementsByAttributeValue("name", "keywords").attr("content");
			description = doc.getElementsByAttributeValue("name", "description").attr("content");
		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//			System.out.printf("-------------file:%d\n", index);
//			System.out.println(html);
//			System.out.printf("docno:%s\nurl:%s\ntitle:%s\nkeywords:%s\ndescription:%s\n", 
//					docno, url, title, keywords, description);
			
			docno = ParserUtils.parseDocno(html);
			url = ParserUtils.parseUrl(html);
		}

		System.out.printf("docno:%s\nurl:%s\ntitle:%s\nkeywords:%s\ndescription:%s\n", 
				docno, url, title, keywords, description);
	}

	public static void main(String[] args) {
		Runner.benchmark(new JsoupApiParser());
	}

}

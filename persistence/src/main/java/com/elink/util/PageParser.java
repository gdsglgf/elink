package com.elink.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.elink.persistence.model.Page;

public class PageParser {
	public static Page parse(String html) {
		String docno = "",
			url = "",
			title = "",
			keywords = "",
			description = "";
		try {
			Document doc = Jsoup.parse(html);	// maybe IllegalArgumentException
			docno = doc.getElementsByTag("docno").text();
			url = doc.getElementsByTag("url").text();
			title = doc.getElementsByTag("title").text();
			keywords = doc.getElementsByAttributeValue("name", "keywords").attr("content");
			description = doc.getElementsByAttributeValue("name", "description").attr("content");
		} catch (IllegalArgumentException e) {
			docno = ParserUtils.parseDocno(html);
			url = ParserUtils.parseUrl(html);
		}
		
		return new Page(docno, url, title, keywords, description);
	}
}

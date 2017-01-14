package com.elink.util;

import java.util.HashMap;
import java.util.Map;

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
	
	public static Map<String, Object> parseToMap(String html, int rank) {
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
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("docno", docno);
		pageMap.put("url", url);
		pageMap.put("title", title.trim());
		pageMap.put("keywords", keywords.trim());
		pageMap.put("description", description.trim());
		pageMap.put("rank", rank);
		
		return pageMap;
	}
	
	public static Object[] parseToArray(String html, int rank) {
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

		return new Object[] {docno, url, title.trim(), keywords.trim(), description.trim(), rank};
	}
}

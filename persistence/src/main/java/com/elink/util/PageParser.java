package com.elink.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.elink.persistence.model.Link;
import com.elink.persistence.model.Page;

public class PageParser {
	public static final Pattern bodyPattern = Pattern.compile("<[\\s]*?body[^>]*?>([\\s\\S]*?)<[\\s]*?\\/[\\s]*?body[\\s]*?>", Pattern.CASE_INSENSITIVE);
	public static final Pattern scriptPattern = Pattern.compile("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", Pattern.CASE_INSENSITIVE);
	public static final Pattern stylePattern = Pattern.compile("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", Pattern.CASE_INSENSITIVE);

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
	
	public static String removeBlackSpace(String text) {
		if (text == null) {
			return "";
		}
		return text.trim().replaceAll("\\s{2,}", " ");
	}
	
	private static List<Link> parseLink(Document doc) {
		List<Link> links = new ArrayList<Link>();
		for (Element e : doc.select("a[href]")) {
			String href = e.attr("abs:href");
			String text = removeBlackSpace(e.text());
			links.add(new Link(href, text));
		}
		return links;
	}
	
	// 去除docno, url, title
	public static String parseBodyContent(String html) {
		Matcher bodyMatcher = bodyPattern.matcher(html);
		String content = null;
		if (bodyMatcher.find()) {
			content = bodyMatcher.group(1);
		} else {
			return "";
		}
		
		Matcher scriptMatcher = scriptPattern.matcher(content);
		content = scriptMatcher.replaceAll("");
		
		Matcher styleMatcher = stylePattern.matcher(content);
		content = styleMatcher.replaceAll("");
		
		content = content.replaceAll("<[^>]+>", "");
		content = content.replaceAll("\\s{2,}", " ");
		return content;
	}
	
	public static Map<String, Object> parseForMap(String html) {
		String docno = "",
			url = "",
			title = "",
			text = "";
		List<Link> links = new ArrayList<Link>();
		try {
			Document doc = Jsoup.parse(html);	// maybe IllegalArgumentException
			docno = doc.getElementsByTag("docno").text();
			url = doc.getElementsByTag("url").text();
			doc.setBaseUri(url);
			links = parseLink(doc);
			title = doc.getElementsByTag("title").text();
			text = doc.body().text();//parseBodyContent(html);
		} catch (IllegalArgumentException e) {
			docno = ParserUtils.parseDocno(html);
			url = ParserUtils.parseUrl(html);
		}
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("docno", docno);
		pageMap.put("url", url);
		pageMap.put("title", removeBlackSpace(title));
		pageMap.put("text", removeBlackSpace(text));
		pageMap.put("links", links);
		
		return pageMap;
	}
	
	public static void main(String[] args) {
		String html = "<html><head><title>Jsoup Example</title></head>"
				+ "<body><h1>Welcome to JournalDev!!</h1>"
							+ "<div id=\"id1\">Hello</div>"
							+ "<div class=\"class1\">Pankaj</div>"
							+ "<a href=\"http://journaldev.com\">Home</a>"
							+ "<a href=\"http://wikipedia.org\">Wikipedia</a>"
							+ "</body></html>";
		
		System.out.println(parseBodyContent(html));
	}
}

package com.miner.model;

public class WebPage {
	private String docno = "";
	private String url = "";
	private String title = "";
	private String keywords = "";
	private String description = "";
	
	public WebPage() {}

	public WebPage(String docno, String url, String title, String keywords, String description) {
		this.docno = docno;
		this.url = url;
		this.title = title;
		this.keywords = keywords;
		this.description = description;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return String.format("WebPage{\n  docno:%s, \n  url:%s, \n  title:%s, \n  keywords:%s, \n  description:%s\n}",
				docno, url, title, keywords, description);
	}
	
	private String format(String data) {
		data = data.trim();
		data = data.replace("\r", "");
		data = data.replace("\n", "");
		data = data.replace("\"", "\\\"");
		return data;
	}
	
	private void formatAll() {
		title = format(title);
		keywords = format(keywords);
		description = format(description);
	}
	
	public String toJSONString() {
		formatAll();
		String format = "{\"docno\":\"%s\", \"url\":\"%s\", \"title\":\"%s\", \"keywords\":\"%s\", \"description\":\"%s\"}";
		Object[] args = new Object[] {docno, url, title, keywords, description};
		return String.format(format, args);
	}
	
	public static void main(String[] args) {
		String data = "Just do \"IT\"";
		System.out.println(data);
		System.out.println(data.replace("\"", "\\\""));
		
		WebPage wp = new WebPage("123", "http://www.123.com", "hello world", "say \"it\" hello", "nice to meet you");
		System.out.println(wp);
		System.out.println(wp.toJSONString());
	}
}

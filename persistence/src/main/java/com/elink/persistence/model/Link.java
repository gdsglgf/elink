package com.elink.persistence.model;
public class Link {
	private String href;
	private String text;

	public Link() {
		// TODO Auto-generated constructor stub
	}

	public Link(String href, String text) {
		this.href = href;
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

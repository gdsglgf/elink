package com.elink.persistence.model;

public class Page {
	private Long id;
	private String docno;
	private String url;
	private String title;
	private String keywords;
	private String description;
	private int rank;
	private Location location;

	public Page() {}
	
	public Page(String docno, String url, String title, String keywords, String description) {
		this.docno = docno;
		this.url = url;
		this.title = title;
		this.keywords = keywords;
		this.description = description;
	}

	public Page(String docno, String url, String title, String keywords, String description, int rank) {
		this(docno, url, title, keywords, description);
		this.rank = rank;
	}

	public Page(String docno, String url, String title, String keywords, String description, int rank,
			Location location) {
		this(docno, url, title, keywords, description, rank);
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String toString() {
		return String.format("Page[id:%d, docno:%s, url:%s, title:%s, keywords:%s, description:%s, loc:%s]",
				id, docno, url, title, keywords, description, location);
	}
}

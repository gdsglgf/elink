package com.elink.persistence.model;

public class ParserLog {
	private int id;
	private int amount;
	private int costTime;
	private Location location;

	public ParserLog() {
	}

	public ParserLog(Location location, int amount, int costTime) {
		this.location = location;
		this.amount = amount;
		this.costTime = costTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCostTime() {
		return costTime;
	}

	public void setCostTime(int costTime) {
		this.costTime = costTime;
	}
	
	public String toString() {
		return String.format("ParserLog[id:%d, location:%s, amount:%d, costTime:%d]", 
				id, location, amount, costTime);
	}
}

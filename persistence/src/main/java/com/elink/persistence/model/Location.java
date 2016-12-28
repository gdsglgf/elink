package com.elink.persistence.model;

public class Location {
	private Long locId;
	private int diskId;
	private Directory directory;
	private Batch batch;

	public Location() {
	}

	public Location(int diskId, Directory directory, Batch batch) {
		this.diskId = diskId;
		this.directory = directory;
		this.batch = batch;
	}

	public Location(Long locId, int diskId, Directory directory, Batch batch) {
		this.locId = locId;
		this.diskId = diskId;
		this.directory = directory;
		this.batch = batch;
	}

	public Long getLocId() {
		return locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public int getDiskId() {
		return diskId;
	}

	public void setDiskId(int diskId) {
		this.diskId = diskId;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public String toString() {
		return String.format("Location[locId:%d, diskId:%d, dir:%s, batch:%s]", 
				locId, diskId, directory, batch);
	}
}

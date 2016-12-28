package com.elink.util;

public class DataUtils {
	public static int parseDirId(String dir) {
		try {
			return Integer.parseInt(dir.split("\\.")[1]);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public static int parseBatchId(String batch) {
		try {
			return Integer.parseInt(batch.split("\\.")[0].split("-")[2]);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(parseDirId("sogout_data.1.comp"));
		System.out.println(parseBatchId("part-m-00000.bz2"));
	}
}

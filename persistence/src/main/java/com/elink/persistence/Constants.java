package com.elink.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
	public static int DISK_ID;
	public static String DATA_HOME;
	public static String DATA_DIR_KEY;
	public static String DATA_FILE_SUFFIX;
	
	@Value("${data.disk.id}")
	public void setDickId(int diskId) {
		Constants.DISK_ID = diskId;
		System.out.println(String.format("set DISK_ID:%d", DISK_ID));
	}
	
	@Value("${data.home}")
	public void setDataHome(String dataHome) {
		Constants.DATA_HOME = dataHome;
		System.out.println(String.format("set DATA_HOME:%s", DATA_HOME));
	}
	
	@Value("${data.dir.key}")
	public void setDataDirKey(String key) {
		Constants.DATA_DIR_KEY = key;
		System.out.println(String.format("set DATA_DIR_KEY:%s", DATA_DIR_KEY));
	}
	
	@Value("${data.file.suffix}")
	public void setDataFileSuffix(String suffix) {
		Constants.DATA_FILE_SUFFIX = suffix;
		System.out.println(String.format("set DATA_FILE_SUFFIX:%s", DATA_FILE_SUFFIX));
	}
}

package com.elink.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	public static int DISK_ID;
	public static String DATA_HOME;
	public static String DATA_DIR_KEY;
	public static String DATA_FILE_SUFFIX;
	public static List<String> DATA_BATCH_IGNORE;
	public static String DATA_OUTPUT_PATH;
	
	public static Logger log = Logger.getLogger(Constants.class);
	
	@Value("${data.disk.id}")
	public void setDickId(int diskId) {
		Constants.DISK_ID = diskId;
		log.debug(String.format("set DISK_ID:%d", DISK_ID));
	}
	
	@Value("${data.home}")
	public void setDataHome(String dataHome) {
		Constants.DATA_HOME = dataHome;
		log.debug(String.format("set DATA_HOME:%s", DATA_HOME));
	}
	
	@Value("${data.dir.key}")
	public void setDataDirKey(String key) {
		Constants.DATA_DIR_KEY = key;
		log.debug(String.format("set DATA_DIR_KEY:%s", DATA_DIR_KEY));
	}
	
	@Value("${data.file.suffix}")
	public void setDataFileSuffix(String suffix) {
		Constants.DATA_FILE_SUFFIX = suffix;
		log.debug(String.format("set DATA_FILE_SUFFIX:%s", DATA_FILE_SUFFIX));
	}
	
	@Value("#{'${data.batch.ignore}'.split(',')}")
	public void setDataBatchIgnore(List<String> dataBatchIgnore) {
		Constants.DATA_BATCH_IGNORE = dataBatchIgnore;
		log.debug(String.format("set DATA_BATCH_IGNORE:%s", DATA_BATCH_IGNORE));
	}
	
	@Value("${data.output.path}")
	public void setDataOutputPath(String path) {
		Constants.DATA_OUTPUT_PATH = path;
		log.debug(String.format("set DATA_OUTPUT_PATH:%s", DATA_BATCH_IGNORE));
	}
	
	public static boolean isIgnore(String batchPath) {
		return Constants.DATA_BATCH_IGNORE.contains(batchPath);
	}
}

package com.elink.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static String defaultPath = "src/main/resources/config.properties";
	private static Properties properties;

	static {
		properties = new Properties();
		try(InputStream is = new FileInputStream(defaultPath)) {
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到key的值
	 * @param key 取得其值的键
	 * @return key的值
	 */
	public static String getValue(String key) {
		String value = properties.getProperty(key);
		return value;
	}
	
	public static void showProperty() {
		properties.forEach((a, b) -> {System.out.println(a + "--->" + b);});
	}
	
	public static String getDataHome() {
		return getValue("data.home");
	}
	
	public static String getDataDirKey() {
		return getValue("data.dir.key");
	}
	
	public static String getDataFileSuffix() {
		return getValue("data.file.suffix");
	}
	
	public static String getDataOutputPath() {
		return getValue("data.output.path");
	}
	
	public static String getDataFileFormat() {
		return getValue("data.file.format");
	}
	
	public static int getDataFileStart() {
		return Integer.parseInt(getValue("data.file.start"));
	}
	
	public static int getDataFileEnd() {
		return Integer.parseInt(getValue("data.file.end"));
	}
	
	public static String getTestDataFile() {
		return getValue("test.data.path");
	}
	
	public static String getRandomFilename() {
		return String.format("data%d.txt", System.currentTimeMillis());
	}
	
	public static String getDataCounterOutputPath() {
		return getValue("data.counter.output.path");
	}
	
	public static void main(String[] args) {
		Config.showProperty();
		System.out.println("jdbc.url--->" + Config.getValue("jdbc.url"));
		System.out.println("host--->" + Config.getValue("host"));
	}
}

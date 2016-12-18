package com.miner.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件IO工具类
 * @author Luo Guofu
 *
 */
public class IOUtils {
	/**
	 * 读文件到字符串
	 * @param path 文件路径名
	 * @return 文件内容字符串
	 */
	public static String fileToString(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 读文件到字符串
	 * @param file 要读取的文件
	 * @return  文件内容字符串
	 */
	public static String toString(File file) {
		return fileToString(file.getAbsolutePath());
	}
	
	private static void writeToFile(String text, String path, OpenOption... options) {
		Path targetPath = Paths.get(path);
		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
		try {
			Files.write(targetPath, bytes, options);
			System.out.println("Done writing to " + path); // For testing
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写字符串到文件
	 * @param text  要保存的字符串
	 * @param path  文件路径名
	 */
	public static void writeToFile(String text, String path) {
		// StandardOpenOption.CREATE ---  Create a new file if it does not exist. If the file already exists, it will modify the file.
		// StandardOpenOption.CREATE_NEW  --- Create a new file, failing if the file already exists.
		writeToFile(text, path, StandardOpenOption.CREATE_NEW);
	}
	
	/**
	 * 写字符串到文件
	 * @param text  要保存的字符串
	 * @param path  文件路径名
	 */
	public static void appendToFile(String text, String path) {
		// append to an existing file, create file if it doesn't initially exist
		OpenOption[] ops = new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.APPEND};
		writeToFile(text, path, ops);
	}
	
	public static void main(String[] args) {
		String text = "hello world.123";
		String path = "log.txt";
		writeToFile(text, path);
		appendToFile(text, path);
	}
}

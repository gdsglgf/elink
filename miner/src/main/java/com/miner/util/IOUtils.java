package com.miner.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
	
	/**
	 * 写字符串到文件
	 * @param text  要保存的字符串
	 * @param targetFilePath  文件路径名
	 */
	public static void writeToFile(String text, String targetFilePath) {
		Path targetPath = Paths.get(targetFilePath);
		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
		try {
			Files.write(targetPath, bytes, StandardOpenOption.CREATE);
			System.out.println("Done writing to " + targetFilePath); // For testing
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.elink.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {
	public static final Pattern CHARSET_PATTERN = Pattern.compile("(?:charset|Charset|CHARSET)\\s*=\\s*\"?\\s*([-\\w]*?)[^-\\w]");
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final Pattern DOCNO_PATTERN = Pattern.compile("<docno>(.*)</docno>");
	public static final Pattern URL_PATTERN = Pattern.compile("<url>(.*)</url>");
	
	/**
	 * 获取所有匹配的项
	 * @param input   输入字符串
	 * @param pattern  正则表达式
	 * @return
	 */
	public static List<String> parseAll(String input, Pattern pattern) {
		List<String> result = new ArrayList<String>();
		Matcher m = pattern.matcher(input);
		while (m.find()) {
			result.add(m.group(1));
		}
		return result;
	}
	
	/**
	 * 获取第一个匹配的项, 没有匹配到返回空字符串
	 * @param input   输入字符串
	 * @param pattern  正则表达式
	 * @return
	 */
	public static String parseOne(String input, Pattern pattern) {
		String result = "";
		Matcher m = pattern.matcher(input);
		if (m.find()) {
			result = m.group(1);
		}
		return result;
	}
	
	/**
	 * 获取匹配字符集, 没有匹配到返回空字符串
	 * <meta charset="UTF-8">
	 * <meta http-equiv="content-type" content="text/html;charset=utf-8">
	 * @param input   输入字符串
	 * @return
	 */
	public static String parseCharset(String input) {
		return parseOne(input, CHARSET_PATTERN);
	}
	
	/**
	 * 获取docno标签的内容, 没有匹配到返回空字符串
	 * <docno>123456</docno>
	 * @param input   输入字符串
	 * @return
	 */
	public static String parseDocno(String input) {
		return parseOne(input, DOCNO_PATTERN);
	}
	
	/**
	 * 获取url标签的内容, 没有匹配到返回空字符串
	 * <url>http://www.test.com/</url>
	 * @param input   输入字符串
	 * @return
	 */
	public static String parseUrl(String input) {
		return parseOne(input, URL_PATTERN);
	}
}

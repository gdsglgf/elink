package com.elink.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
	public static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 把JavaBean转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSon(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

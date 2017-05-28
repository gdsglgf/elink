package com.elink.util;

import com.fasterxml.jackson.core.type.TypeReference;
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
	
	/**
	 * json字符串转java对象
	 * @param jsonStr
	 * @param valueTypeRef
	 * Map<String, Object> map = JacksonUtil.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

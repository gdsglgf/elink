package com.elink.util;

import java.util.Arrays;

public class JacksonUtilsTest {

	public static void main(String[] args) {
		String text = "<^\"U\"^>";
		System.out.println(Arrays.toString(text.split("\"")));
		System.out.println(text);
		System.out.println(text.replace("\"", "\\\""));
		System.out.println(JacksonUtils.toJSon(text));
		System.out.println(JacksonUtils.toJSon(new Object[] {"string", null, 100, true, 0.05, 'X', "{\"key\":\"value\"}"}));
	}

}

//[<^, U, ^>]
//<^"U"^>
//<^\"U\"^>
//"<^\"U\"^>"
//["string",null,100,true,0.05,"X"]

// 文件读取<^"U"^>与<^\"U\"^>和"<^\"U\"^>"的差异
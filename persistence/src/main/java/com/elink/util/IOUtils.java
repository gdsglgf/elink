package com.elink.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public class IOUtils {
	/**
	 * 获取压缩文件的BufferedReader
	 * @param fileIn  文件路径名
	 * @return
	 */
	public static BufferedReader getBufferedReaderForCompressedFile(String fileIn) {
		BufferedReader br2 = null;
		try {
			FileInputStream fin = new FileInputStream(fileIn);
			BufferedInputStream bis = new BufferedInputStream(fin);
			CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(bis);
			br2 = new BufferedReader(new InputStreamReader(input));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br2;
	}
	
	
}

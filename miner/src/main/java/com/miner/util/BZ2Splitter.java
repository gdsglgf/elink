package com.miner.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import com.miner.parser.IParser;

/**
 * BZ2文件切分器
 * @author Luo Guofu
 *
 */
public class BZ2Splitter {
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

	/**
	 * 
	 * @param filename  文件路径名
	 * @param parser    文件内容解析器
	 * @return   文件的个数
	 */
	public static int split(String filename, IParser parser) {
		int cnt = 0;
		String line = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = getBufferedReaderForCompressedFile(filename)) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				if (line.contains("</doc>")) {
					cnt++;
					parser.parse(sb.toString(), cnt);
					sb = new StringBuilder();
				}
			}
		} catch (IOException e) {
//			e.printStackTrace();  // when EOF java.io.IOException: bad block header
		}
		return cnt;
	}
}

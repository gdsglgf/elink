package com.miner.parser;

/**
 * HTML文件内容解析器接口
 * @author Luo Guofu
 *
 */
public interface IParser {
	/**
	 * 
	 * @param html  文件内容
	 * @param index  文件在bz2中的位置(从1开始)
	 */
	public void parse(String html, int index);
}             

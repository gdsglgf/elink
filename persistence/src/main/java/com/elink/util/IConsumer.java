package com.elink.util;

/**
 * 文件内容消费者接口
 */
public interface IConsumer {
	/**
	 * 处理文件
	 * @param html  文件内容
	 * @param rank  文件内容在压缩文件中的位置(从1开始)
	 */
	public default void consume(String html, int rank) {
		// empty method body
	}

}
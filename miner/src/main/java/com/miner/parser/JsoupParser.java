package com.miner.parser;

import com.miner.util.Runner;

public class JsoupParser implements IParser {

	@Override
	public void parse(String html, int index) {
		
	}

	public static void main(String[] args) {
		Runner.benchmark(new JsoupParser());
	}
}

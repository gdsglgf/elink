package com.elink.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataFileUtils {
	
	/**
	 * file path format: root/%dir_key%/*.%suffix%
	 * @return
	 */
	public static List<String> loadAllFiles() {
		String root = Config.getDataHome();
		File home = new File(root);
		List<String> files = Arrays.asList(home.list()).stream()
				.filter(name -> name.contains(Config.getDataDirKey()))
				.map(f -> root + f)
				.flatMap(f -> Arrays.asList(new File(f).list()).stream()
						.map(p -> f + "/" + p)
						.filter(a -> a.endsWith(Config.getDataFileSuffix())))
				.collect(Collectors.toList());
		return files;
	}
}

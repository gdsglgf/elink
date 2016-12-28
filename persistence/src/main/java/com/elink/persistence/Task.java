package com.elink.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elink.persistence.mapper.LocationMapper;
import com.elink.persistence.mapper.PageMapper;
import com.elink.persistence.mapper.ParserLogMapper;
import com.elink.persistence.model.Batch;
import com.elink.persistence.model.Directory;
import com.elink.persistence.model.Location;
import com.elink.persistence.model.Page;
import com.elink.persistence.model.ParserLog;
import com.elink.persistence.service.BatchService;
import com.elink.persistence.service.DirectoryService;
import com.elink.util.DataUtils;
import com.elink.util.IOUtils;
import com.elink.util.PageParser;

@Component
public class Task {
	public void parse(String html, Location loc, int rank) {
		Page page = PageParser.parse(html);
		page.setLocation(loc);
		page.setRank(rank);
		try {
			pageMapper.create(page);
		} catch (Exception e) {
			System.out.printf("Save page occurs error:%s\n", e.getMessage());
			System.out.println(page);
		}
	}
	
	public int splitBatch(String filename, Location loc) {
		int cnt = 0;
		String line = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = IOUtils.getBufferedReaderForCompressedFile(filename)) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				if (line.contains("</doc>")) {
					cnt++;
					parse(sb.toString(), loc, cnt);
					sb = new StringBuilder();
				}
			}
		} catch (IOException e) {
		}
		return cnt;
	}
	
	public void doTask() {
		File home = new File(Constants.DATA_HOME);
		String[] dirs = home.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(Constants.DATA_DIR_KEY);
			}
		});
		
		Directory directory = new Directory();
		Batch batch = new Batch();
		Location location = new Location();
		ParserLog parserLog = new ParserLog();
		
		for (String dir : dirs) {
			// set dir id, name and save
			directory.setId(DataUtils.parseDirId(dir));
			directory.setName(dir);
			directoryService.create(directory);
			
			String dirPath = Constants.DATA_HOME + dir;
			String[] batches = new File(dirPath).list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(Constants.DATA_FILE_SUFFIX);
				}
			});
			
			for (String b : batches) {
				// set batch id, name, and save
				batch.setId(DataUtils.parseBatchId(b));
				batch.setName(b);
				batchService.create(batch);
				
				// set loc disk_id, dir_id, batch_id and save
				location.setDiskId(Constants.DISK_ID);
				location.setDirectory(directory);
				location.setBatch(batch);
				locationMapper.create(location);
				
				// parse bz2 file 
				String batchPath = dirPath + "/" + b;
				long start = System.currentTimeMillis();
				int cnt = splitBatch(batchPath, location);
				long duration = System.currentTimeMillis() - start;
				
				// set log and save
				parserLog.setLocation(location);
				parserLog.setAmount(cnt);
				parserLog.setCostTime((int) duration);
				parserLogMapper.create(parserLog);
				
				System.out.println(location);
				System.out.println(String.format("total: %d files, cost %d ms", cnt, duration));
				System.out.printf("%.1f files/second, %.1f ms/file\n", cnt / (duration / 1000.0), duration * 1.0 / cnt);
			}
		}

//		Arrays.asList(home.list()).stream()
//			.filter(name -> name.contains(Constants.DATA_DIR_KEY))
//			.map(f -> Constants.DATA_HOME + f)
//			.flatMap(f -> Arrays.asList(new File(f).list())
//						.stream().map(p -> f + "/" + p)
//						.filter(a -> a.endsWith(Constants.DATA_FILE_SUFFIX)))
//			.forEach(System.out::println);
	}

	@Autowired
	private DirectoryService directoryService;
	@Autowired
	private BatchService batchService;
	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private PageMapper pageMapper;
	@Autowired
	private ParserLogMapper parserLogMapper;
}

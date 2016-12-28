package com.elink.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elink.persistence.mapper.DirectoryMapper;
import com.elink.persistence.model.Directory;

@Service
public class DirectoryService {
	public void create(Directory directory) {
		try {
			directoryMapper.create(directory);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Autowired
	private DirectoryMapper directoryMapper;
}

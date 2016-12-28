package com.elink.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elink.persistence.mapper.BatchMapper;
import com.elink.persistence.model.Batch;

@Service
public class BatchService {
	public void create(Batch batch) {
		try {
			batchMapper.create(batch);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Autowired
	private BatchMapper batchMapper;
}

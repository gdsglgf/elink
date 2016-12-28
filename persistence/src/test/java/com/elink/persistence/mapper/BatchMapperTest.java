package com.elink.persistence.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.elink.persistence.model.Batch;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
//@ContextConfiguration({"file:src/main/resources/spring-servlet.xml"})
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
public class BatchMapperTest {
	@Test
	public void testGetById() {
		Batch batch = mapper.getById(0);
		Assert.assertNotNull(batch);
		System.out.println(batch);
		Assert.assertNull(mapper.getById(1000));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void testCreateUsingSameName() {
		Batch batch = mapper.getById(0);
		batch.setId(1);
		System.out.println(batch);
		mapper.create(batch);
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void testCreateUsingSameId() {
		Batch batch = mapper.getById(0);
		System.out.println(batch);
		mapper.create(batch);
	}
	
	@Test
	public void testCreate() {
		Batch batch = new Batch(100, "batch100");
		mapper.create(batch);
		Assert.assertNotNull(mapper.getById(100));
	}

	@Autowired
	private BatchMapper mapper;
}

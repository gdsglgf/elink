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

import com.elink.persistence.model.ParserLog;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
//@ContextConfiguration({"file:src/main/resources/spring-servlet.xml"})
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
public class ParserLogMapperTest {
	@Test
	public void testGetById() {
		ParserLog log = mapper.getById(1);
		Assert.assertNotNull(log);
		System.out.println(log);
		Assert.assertNull(mapper.getById(1000));
	}
	
	@Test
	public void testCreate() {
		ParserLog log = mapper.getById(1);
		log.getLocation().setLocId(3L);
		mapper.create(log);
		Assert.assertNotEquals(1, log.getId());
		System.out.println(log);
		System.out.println(mapper.getById(log.getId()));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void testCreateUsingSameDocno() {
		ParserLog log = mapper.getById(1);
		mapper.create(log);
		Assert.assertNotEquals(1, log.getId());
		System.out.println(mapper.getById(log.getId()));
	}

	@Autowired
	private ParserLogMapper mapper;
}

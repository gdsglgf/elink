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

import com.elink.persistence.model.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
//@ContextConfiguration({"file:src/main/resources/spring-servlet.xml"})
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
public class PageMapperTest {
	@Test
	public void testGetById() {
		Page page = mapper.getById(1);
		Assert.assertNotNull(page);
		System.out.println(page);
		Assert.assertNull(mapper.getById(1000));
	}
	
	@Test
	public void testCreate() {
		Page page = mapper.getById(1);
		page.setDocno("1234567890");
		mapper.create(page);
		Assert.assertNotEquals(1, page.getId().longValue());
		System.out.println(mapper.getById(page.getId()));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void testCreateUsingSameDocno() {
		Page page = mapper.getById(1);
		mapper.create(page);
		Assert.assertNotEquals(1, page.getId().longValue());
		System.out.println(mapper.getById(page.getId()));
	}

	@Autowired
	private PageMapper mapper;
}

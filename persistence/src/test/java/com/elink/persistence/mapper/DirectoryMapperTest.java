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

import com.elink.persistence.model.Directory;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
//@ContextConfiguration({"file:src/main/resources/spring-servlet.xml"})
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
public class DirectoryMapperTest {
	@Test
	public void testGetById() {
		Directory dir = mapper.getById(1);
		Assert.assertNotNull(dir);
		System.out.println(dir);
		Assert.assertNull(mapper.getById(1000));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void testCreateUsingSameName() {
		Directory dir = mapper.getById(1);
		dir.setId(2);
		System.out.println(dir);
		mapper.create(dir);
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void testCreateUsingSameId() {
		Directory dir = mapper.getById(1);
		System.out.println(dir);
		mapper.create(dir);
	}
	
	@Test
	public void testCreate() {
		mapper.create(new Directory(100, "dir100"));
		Directory dir = mapper.getById(100);
		Assert.assertNotNull(dir);
		System.out.println(dir);
	}

	@Autowired
	private DirectoryMapper mapper;
}

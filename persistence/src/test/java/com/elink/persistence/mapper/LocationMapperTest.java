package com.elink.persistence.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.elink.persistence.model.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
//@ContextConfiguration({"file:src/main/resources/spring-servlet.xml"})
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
public class LocationMapperTest {
	@Test
	public void testGetById() {
		Location loc = mapper.getById(1);
		Assert.assertNotNull(loc);
		System.out.println(loc);
		Assert.assertNull(mapper.getById(1000));
	}
	
	@Test
	public void testCreate() {
		Location loc = mapper.getById(1);
		mapper.create(loc);
		Assert.assertNotEquals(1, loc.getLocId().longValue());
		System.out.println(mapper.getById(loc.getLocId()));
	}

	@Autowired
	private LocationMapper mapper;
}

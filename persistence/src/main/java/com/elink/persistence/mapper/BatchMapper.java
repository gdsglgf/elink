package com.elink.persistence.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.elink.persistence.model.Batch;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface BatchMapper {
	public Batch getById(@Param("id") int id);
	public void create(Batch batch);
}

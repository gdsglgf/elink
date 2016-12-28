package com.elink.persistence.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.elink.persistence.model.Page;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface PageMapper {
	public Page getById(@Param("id") long id);
	public void create(Page page);
}

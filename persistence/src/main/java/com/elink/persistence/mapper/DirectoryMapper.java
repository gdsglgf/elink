package com.elink.persistence.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.elink.persistence.model.Directory;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface DirectoryMapper {
	public Directory getById(@Param("id") int id);
	public void create(Directory directory);
}

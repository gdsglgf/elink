package com.elink.persistence.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.elink.persistence.model.Page;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface PageMapper {
	public Page getById(@Param("id") long id);
	public void create(Page page);
	public long countAll();
	public List<Map<String, String>> findAll(@Param("params") Map<String, String> params);
	public List<Map<String, String>> findAll2(@Param("offset") int offset, @Param("limit") int limit);
}

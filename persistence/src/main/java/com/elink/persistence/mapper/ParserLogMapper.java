package com.elink.persistence.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.elink.persistence.model.ParserLog;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface ParserLogMapper {
	public ParserLog getById(@Param("id") int id);
	public void create(ParserLog parserLog);
}

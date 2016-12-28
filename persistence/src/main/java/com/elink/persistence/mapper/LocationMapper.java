package com.elink.persistence.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.elink.persistence.model.Location;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface LocationMapper {
	public Location getById(@Param("id") long id);
	public void create(Location location);
}

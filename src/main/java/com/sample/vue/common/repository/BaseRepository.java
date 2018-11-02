package com.sample.vue.common.repository;

import org.apache.ibatis.annotations.Param;

public interface BaseRepository<T> {
	void insert(@Param("entity") T entity);
	int delete(	@Param("entity") T entity);
	int update( @Param("entity") T entity);
	T selectOne(@Param("entity") T entity);
}

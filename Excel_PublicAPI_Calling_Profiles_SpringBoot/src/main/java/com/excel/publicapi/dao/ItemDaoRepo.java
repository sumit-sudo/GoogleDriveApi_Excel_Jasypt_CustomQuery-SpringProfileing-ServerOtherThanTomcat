package com.excel.publicapi.dao;

import java.util.List;

import org.hibernate.type.TrueFalseType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.excel.publicapi.entities.Item;







@Repository
public interface ItemDaoRepo extends CrudRepository<Item, Integer> {

	@Query(value="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='item';", nativeQuery = true)
	public List<String> getColumnName();
	@Query(value="select count(*) from item;", nativeQuery = true)
	public int getTotalNumRow();
	@Query(value="select id from item;", nativeQuery = true)
	public List<Integer> getId();
	@Query(value="select * from item;", nativeQuery = true)
	public List<Object[]> FindAllItem();
	@Modifying
	@Query(value="insert into Employee values:data", nativeQuery = true)
	public void insertEmployee(@Param("data") StringBuilder data);

//
}

package org.apache.ibatis.submitted.blocking_cache;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

@CacheNamespace(blocking = true)
public interface PersonMapper {

  @Select("select id, firstname, lastname from person")
  List<Person> findAll();

  @Delete("delete from person where id = #{id}")
  int delete(int id);
}

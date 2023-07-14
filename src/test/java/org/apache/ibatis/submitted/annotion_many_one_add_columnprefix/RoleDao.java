package org.apache.ibatis.submitted.annotion_many_one_add_columnprefix;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author lvyang
 */
public interface RoleDao {
  @Select("select * from role")
  @Results(id = "roleMap1", value = {
    @Result(id = true, column = "id", property = "id"),
    @Result(column = "name", property = "name")
  })
  public List<Role> findAll();
}

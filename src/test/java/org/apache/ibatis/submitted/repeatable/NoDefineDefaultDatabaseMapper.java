package org.apache.ibatis.submitted.repeatable;

import org.apache.ibatis.annotations.Select;

public interface NoDefineDefaultDatabaseMapper {

  @Select(value = "SELECT id, name, 'HSQL' as databaseName FROM users WHERE id = #{id}", databaseId = "hsql")
  @Select(value = "SELECT id, name, 'HSQL' as databaseName FROM users WHERE id = #{id}", databaseId = "h2")
  User getUser(Integer id);

}

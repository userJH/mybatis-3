package org.apache.ibatis.submitted.repeatable;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface BothSelectContainerAndSelectProviderContainerMapper {

  @Select(value = "SELECT * FROM users WHERE id = #{id}", databaseId = "hsql")
  @Select("SELECT * FROM users WHERE id = #{id}")
  @SelectProvider(type = SqlProvider.class, method = "getUser", databaseId = "hsql")
  @SelectProvider(type = SqlProvider.class, method = "getUser")
  User getUser(Integer id);

  class SqlProvider {
    public static String getUser() {
      return "SELECT * FROM users WHERE id = #{id}";
    }
  }

}

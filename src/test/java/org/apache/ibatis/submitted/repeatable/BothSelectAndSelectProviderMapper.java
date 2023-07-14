package org.apache.ibatis.submitted.repeatable;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface BothSelectAndSelectProviderMapper {

  @Select("SELECT * FROM users WHERE id = #{id}")
  @SelectProvider(type = SqlProvider.class, method = "getUser")
  User getUser(Integer id);

  class SqlProvider {
    public static String getUser() {
      return "SELECT * FROM users WHERE id = #{id}";
    }
  }

}

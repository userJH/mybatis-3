package org.apache.ibatis.submitted.arg_name_based_constructor_automapping;

import org.apache.ibatis.annotations.Param;

public class User2 {

  private Integer userId;
  private String name;

  public User2(Integer userId, @Param("userName") String name) {
    super();
    this.userId = userId;
    this.name = name;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }
}

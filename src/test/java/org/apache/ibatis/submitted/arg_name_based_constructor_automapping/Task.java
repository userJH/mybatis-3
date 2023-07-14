package org.apache.ibatis.submitted.arg_name_based_constructor_automapping;

import org.apache.ibatis.annotations.Param;

public class Task {
  private final Integer id;
  private final String name;
  private User assignee;

  public Task(@Param("id") Integer id, @Param("name") String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public User getAssignee() {
    return assignee;
  }

  public void setAssignee(User assignee) {
    this.assignee = assignee;
  }
}

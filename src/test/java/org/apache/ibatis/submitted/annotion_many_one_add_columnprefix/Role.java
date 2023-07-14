package org.apache.ibatis.submitted.annotion_many_one_add_columnprefix;

public class Role {
  private Integer id;

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", roleName='" + name + '\'' +
        '}';
  }

  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

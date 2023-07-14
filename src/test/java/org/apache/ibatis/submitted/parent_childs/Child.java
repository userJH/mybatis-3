package org.apache.ibatis.submitted.parent_childs;

public class Child {

  private Integer id;
  private String name;
  private String surName;
  private Integer age;

  public Child() {

  }

  public Child(Integer id, String name, String surName, Integer age) {
    this.id = id;
    this.name = name;
    this.surName = surName;
    this.age = age;
  }

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

  public String getSurName() {
    return surName;
  }

  public void setSurName(String surName) {
    this.surName = surName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

}

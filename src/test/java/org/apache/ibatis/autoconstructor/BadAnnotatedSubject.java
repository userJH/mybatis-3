package org.apache.ibatis.autoconstructor;

import org.apache.ibatis.annotations.AutomapConstructor;

public class BadAnnotatedSubject {
  private final int id;
  private final String name;
  private final int age;
  private final int height;
  private final int weight;

  @AutomapConstructor
  public BadAnnotatedSubject(final int id, final String name, final int age, final int height, final int weight) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.height = height;
    this.weight = weight;
  }

  @AutomapConstructor
  public BadAnnotatedSubject(final int id, final String name, final int age) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.height = 0;
    this.weight = 0;
  }
}

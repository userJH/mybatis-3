package org.apache.ibatis.submitted.orphan_result_maps;

import org.apache.ibatis.annotations.Param;

public class Post {

  private final int id;
  private final String body;

  public Post(@Param("id") int id, @Param("body") String body) {
    this.id = id;
    this.body = body;
  }

  public int getId() {
    return id;
  }

  public String getBody() {
    return body;
  }
}

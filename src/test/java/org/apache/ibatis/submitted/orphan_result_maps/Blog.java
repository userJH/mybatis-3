package org.apache.ibatis.submitted.orphan_result_maps;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.submitted.parent_reference_3level.Post;

public class Blog {

  private int id;
  private String title;
  private List<Post> posts;

  public Blog(@Param("id") int id, @Param("title") String title) {
    this.id = id;
    this.title = title;
    this.posts = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public List<Post> getPosts() {
    return posts;
  }
}

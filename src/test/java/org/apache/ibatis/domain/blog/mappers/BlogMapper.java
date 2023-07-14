package org.apache.ibatis.domain.blog.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BlogMapper {

  List<Map> selectAllPosts();

  List<Map> selectAllPosts(RowBounds rowBounds);

  List<Map> selectAllPosts(RowBounds rowBounds, Object param);

}

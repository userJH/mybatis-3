package org.apache.ibatis.submitted.dirty_select;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.cursor.Cursor;

public interface Mapper {

  @Select("select * from users where id = #{id}")
  User selectById(Integer id);

  @Select("select * from users where id = #{id}")
  Cursor<User> selectCursorById(Integer id);

  @Select(value = "insert into users (name) values (#{name}) returning id, name", affectData = true)
  User insertReturn(String name);

  @Select(value = "insert into users (name) values (#{name}) returning id, name", affectData = true)
  Cursor<User> insertReturnCursor(String name);

  User insertReturnXml(String name);

  @SelectProvider(type = MyProvider.class, method = "getSql", affectData = true)
  User insertReturnProvider(String name);

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("insert into users (name) values (#{name}) returning id, name")
  int insert(User user);

  static class MyProvider {
    public static String getSql() {
      return "insert into users (name) values (#{name}) returning id, name";
    }
  }
}

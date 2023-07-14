package org.apache.ibatis.submitted.arg_name_based_constructor_automapping;

import org.apache.ibatis.annotations.Select;

public interface Mapper {

  @Select("select name, id from users where id = #{id}")
  User selectNameAndId(Integer id);

  @Select("select name, id bar from users where id = #{id}")
  User selectNameAndIdWithBogusLabel(Integer id);

  @Select("select name, team, id from users where id = #{id}")
  User selectNameTeamAndId(Integer id);

  @Select("select name userName, id userId from users where id = #{id}")
  User2 selectUserIdAndUserName(Integer id);

  @Select("select name user_name, id user_id from users where id = #{id}")
  User2 selectUserIdAndUserNameUnderscore(Integer id);

  Task selectTask(Integer id);
}

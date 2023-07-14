package org.apache.ibatis.submitted.record_type;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface RecordTypeMapper {

  @Select("select id, val, url from prop where id = #{id}")
  Property selectPropertyAutomapping(int id);

  @Results(id = "propertyRM")
  @Arg(column = "id", javaType = int.class)
  @Arg(column = "val", javaType = String.class)
  @Arg(column = "url", javaType = String.class)
  @Select("select val, id, url from prop where id = #{id}")
  Property selectProperty(int id);

  @Insert("insert into prop (id, val, url) values (#{id}, #{value}, #{URL})")
  int insertProperty(Property property);

  @Arg(id = true, column = "id", javaType = Integer.class)
  @Arg(javaType = Property.class, resultMap = "propertyRM", columnPrefix = "p_")
  @Select({
    "select i.id, p.id p_id, p.val p_val, p.url p_url",
    "from item i left join prop p on p.id = i.prop_id",
    "where i.id = #{id}" })
  Item selectItem(Integer id);

}

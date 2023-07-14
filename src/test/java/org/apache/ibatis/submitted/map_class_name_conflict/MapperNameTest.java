package org.apache.ibatis.submitted.map_class_name_conflict;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

class MapperNameTest {

  @Test
  void initDatabase() throws IOException {
    String resource = "org/apache/ibatis/submitted/map_class_name_conflict/ibatisConfig.xml";
    Reader reader = Resources.getResourceAsReader(resource);
    new SqlSessionFactoryBuilder().build(reader);
  }
}

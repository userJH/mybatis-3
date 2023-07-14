package org.apache.ibatis.submitted.ognlstatic;

import java.io.Reader;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OgnlStaticTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeAll
  static void setUp() throws Exception {
    // create a SqlSessionFactory
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/ognlstatic/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    // populate in-memory database
    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/ognlstatic/CreateDB.sql");
  }

  /**
   * This is the log output.
   * <p>
   * DEBUG [main] - ooo Using Connection [org.hsqldb.jdbc.JDBCConnection@5ae1a5c7]
   * <p>
   * DEBUG [main] - ==> Preparing: SELECT * FROM users WHERE name IN (?) AND id = ?
   * <p>
   * DEBUG [main] - ==> Parameters: 1(Integer), 1(Integer)
   * <p>
   * There are two parameter mappings but DefaultParameterHandler maps them both to input parameter (integer)
   */
  @Test // see issue #448
  void shouldGetAUserStatic() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User user = mapper.getUserStatic(1);
      Assertions.assertNotNull(user);
      Assertions.assertEquals("User1", user.getName());
    }
  }

  @Tag("RequireIllegalAccess")
  @Test // see issue #61 (gh)
  void shouldGetAUserWithIfNode() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User user = mapper.getUserIfNode("User1");
      Assertions.assertEquals("User1", user.getName());
    }
  }

}

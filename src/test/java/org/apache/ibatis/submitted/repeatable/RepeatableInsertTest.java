package org.apache.ibatis.submitted.repeatable;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RepeatableInsertTest {

  @Test
  void hsql() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-hsql");
    }

    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      int count = mapper.count();
      User newUser = new User();
      newUser.setName("Test");
      mapper.insertUser(newUser);

      User user = mapper.getUser(newUser.getId());
      Assertions.assertEquals(Integer.valueOf(count + 1), user.getId());
      Assertions.assertEquals("Test HSQL", user.getName());
    }
  }

  @Test
  void hsqlUsingProvider() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-hsql");
    }

    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      int count = mapper.count();
      User newUser = new User();
      newUser.setName("Test");
      mapper.insertUserUsingProvider(newUser);

      User user = mapper.getUser(newUser.getId());
      Assertions.assertEquals(Integer.valueOf(count + 1), user.getId());
      Assertions.assertEquals("Test HSQL", user.getName());
    }
  }

  @Test
  void derby() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-derby");
    }

    // populate in-memory database
    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      User newUser = new User();
      newUser.setName("Test");
      mapper.insertUser(newUser);

      User user = mapper.getUser(newUser.getId());
      Assertions.assertEquals(Integer.valueOf(count + 1001), user.getId());
      Assertions.assertEquals("Test DERBY", user.getName());
    }
  }

  @Test
  void derbyUsingProvider() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-derby");
    }

    // populate in-memory database
    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      User newUser = new User();
      newUser.setName("Test");
      mapper.insertUserUsingProvider(newUser);

      User user = mapper.getUser(newUser.getId());
      Assertions.assertEquals(Integer.valueOf(count + 1001), user.getId());
      Assertions.assertEquals("Test DERBY", user.getName());
    }
  }

  @Test
  void h2() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-h2");
    }

    // populate in-memory database
    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      User newUser = new User();
      newUser.setName("Test");
      mapper.insertUser(newUser);

      User user = mapper.getUser(newUser.getId());
      Assertions.assertEquals(Integer.valueOf(count + 10001), user.getId());
      Assertions.assertEquals("Test DEFAULT", user.getName());
    }
  }

  @Test
  void h2UsingProvider() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-h2");
    }

    // populate in-memory database
    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      User newUser = new User();
      newUser.setName("Test");
      mapper.insertUserUsingProvider(newUser);

      User user = mapper.getUser(newUser.getId());
      Assertions.assertEquals(Integer.valueOf(count + 10001), user.getId());
      Assertions.assertEquals("Test DEFAULT", user.getName());
    }
  }

}

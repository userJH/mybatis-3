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

class RepeatableDeleteTest {

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
      int targetCount = mapper.countByCurrentDatabase("HSQL");
      mapper.delete();

      Assertions.assertEquals(count - targetCount , mapper.count());
      Assertions.assertEquals(0 , mapper.countByCurrentDatabase("HSQL"));
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
      int targetCount = mapper.countByCurrentDatabase("HSQL");
      mapper.deleteUsingProvider();

      Assertions.assertEquals(count - targetCount , mapper.count());
      Assertions.assertEquals(0 , mapper.countByCurrentDatabase("HSQL"));
    }
  }

  @Test
  void derby() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-derby");
    }

    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      int targetCount = mapper.countByCurrentDatabase("DERBY");
      mapper.delete();

      Assertions.assertEquals(count - targetCount , mapper.count());
      Assertions.assertEquals(0 , mapper.countByCurrentDatabase("DERBY"));
    }
  }

  @Test
  void derbyUsingProvider() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-derby");
    }

    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      int targetCount = mapper.countByCurrentDatabase("DERBY");
      mapper.deleteUsingProvider();

      Assertions.assertEquals(count - targetCount , mapper.count());
      Assertions.assertEquals(0 , mapper.countByCurrentDatabase("DERBY"));
    }
  }

  @Test
  void h2() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-h2");
    }

    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      int targetCount = mapper.countByCurrentDatabase("DEFAULT");
      mapper.delete();

      Assertions.assertEquals(count - targetCount , mapper.count());
      Assertions.assertEquals(0 , mapper.countByCurrentDatabase("DEFAULT"));
    }
  }

  @Test
  void h2UsingProvider() throws IOException, SQLException {
    SqlSessionFactory sqlSessionFactory;
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-h2");
    }

    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/repeatable/CreateDB.sql");

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);

      int count = mapper.count();
      int targetCount = mapper.countByCurrentDatabase("DEFAULT");
      mapper.deleteUsingProvider();

      Assertions.assertEquals(count - targetCount , mapper.count());
      Assertions.assertEquals(0 , mapper.countByCurrentDatabase("DEFAULT"));
    }
  }

}

package org.apache.ibatis.submitted.repeatable;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RepeatableErrorTest {

  @Test
  void noSuchStatementByCurrentDatabase() throws IOException {
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-derby");
      BuilderException exception = Assertions.assertThrows(BuilderException.class, () ->
          sqlSessionFactory.getConfiguration().addMapper(NoDefineDefaultDatabaseMapper.class));
      Assertions.assertEquals("Could not find a statement annotation that correspond a current database or default statement on method 'org.apache.ibatis.submitted.repeatable.NoDefineDefaultDatabaseMapper.getUser'. Current database id is [derby].", exception.getMessage());
    }
  }

  @Test
  void bothSpecifySelectAndSelectProvider() throws IOException {
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-derby");
      BuilderException exception = Assertions.assertThrows(BuilderException.class, () ->
          sqlSessionFactory.getConfiguration().addMapper(BothSelectAndSelectProviderMapper.class));
      String message = exception.getMessage();
      Assertions.assertTrue(message.startsWith("Detected conflicting annotations "));
      Assertions.assertTrue(message.contains("'@org.apache.ibatis.annotations.Select("));
      Assertions.assertTrue(message.contains("'@org.apache.ibatis.annotations.SelectProvider("));
      Assertions.assertTrue(message.matches(".*databaseId=[\"]*,.*"));
      Assertions.assertTrue(message.endsWith(
          "'org.apache.ibatis.submitted.repeatable.BothSelectAndSelectProviderMapper.getUser'."));
    }
  }

  @Test
  void bothSpecifySelectContainerAndSelectProviderContainer() throws IOException {
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/repeatable/mybatis-config.xml")) {
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development-derby");
      BuilderException exception = Assertions.assertThrows(BuilderException.class, () ->
          sqlSessionFactory.getConfiguration().addMapper(BothSelectContainerAndSelectProviderContainerMapper.class));
      String message = exception.getMessage();
      Assertions.assertTrue(message.startsWith("Detected conflicting annotations "));
      Assertions.assertTrue(message.contains("'@org.apache.ibatis.annotations.Select("));
      Assertions.assertTrue(message.contains("'@org.apache.ibatis.annotations.SelectProvider("));
      Assertions.assertTrue(message.matches(".*databaseId=\"?hsql\"?,.*"));
      Assertions.assertTrue(message.endsWith(
          " on 'org.apache.ibatis.submitted.repeatable.BothSelectContainerAndSelectProviderContainerMapper.getUser'."));
    }
  }

}

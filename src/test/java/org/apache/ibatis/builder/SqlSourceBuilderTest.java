package org.apache.ibatis.builder;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SqlSourceBuilderTest {

  private static Configuration configuration;
  private static SqlSourceBuilder sqlSourceBuilder;
  private final String sqlFromXml = "\t\n\n  SELECT * \n        FROM user\n \t        WHERE user_id = 1\n\t  ";

  @BeforeEach
  void setUp() {
    configuration = new Configuration();

    sqlSourceBuilder = new SqlSourceBuilder(configuration);
  }

  @Test
  void testShrinkWhitespacesInSqlIsFalse() {
    SqlSource sqlSource = sqlSourceBuilder.parse(sqlFromXml, null, null);
    BoundSql boundSql = sqlSource.getBoundSql(null);
    String actual = boundSql.getSql();
    Assertions.assertEquals(sqlFromXml, actual);
  }

  @Test
  void testShrinkWhitespacesInSqlIsTrue() {
    configuration.setShrinkWhitespacesInSql(true);
    SqlSource sqlSource = sqlSourceBuilder.parse(sqlFromXml, null, null);
    BoundSql boundSql = sqlSource.getBoundSql(null);
    String actual = boundSql.getSql();

    String shrankWhitespacesInSql = "SELECT * FROM user WHERE user_id = 1";
    Assertions.assertEquals(shrankWhitespacesInSql, actual);
  }
}

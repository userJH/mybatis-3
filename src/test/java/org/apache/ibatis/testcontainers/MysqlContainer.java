package org.apache.ibatis.testcontainers;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.testcontainers.containers.MySQLContainer;

public class MysqlContainer {

  private static final String DB_NAME = "mybatis_test";
  private static final String USERNAME = "u";
  private static final String PASSWORD = "p";
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

  private static final MySQLContainer<?> INSTANCE = initContainer();

  private static MySQLContainer<?> initContainer() {
    @SuppressWarnings("resource")
    MySQLContainer<?> container = new MySQLContainer<>().withDatabaseName(DB_NAME).withUsername(USERNAME)
        .withPassword(PASSWORD).withUrlParam("useSSL", "false");
    container.start();
    return container;
  }

  public static DataSource getUnpooledDataSource() {
    return new UnpooledDataSource(MysqlContainer.DRIVER, INSTANCE.getJdbcUrl(), MysqlContainer.USERNAME,
        MysqlContainer.PASSWORD);
  }

  public static PooledDataSource getPooledDataSource() {
    return new PooledDataSource(MysqlContainer.DRIVER, INSTANCE.getJdbcUrl(), MysqlContainer.USERNAME,
        MysqlContainer.PASSWORD);
  }

  private MysqlContainer() {
    super();
  }
}

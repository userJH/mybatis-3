package org.apache.ibatis.transaction.jdbc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.junit.jupiter.api.Test;

class JdbcTransactionFactoryTest {

  @Test
  void testNullProperties() throws Exception {
    TestConnection connection = new TestConnection(false);
    JdbcTransactionFactory factory = new JdbcTransactionFactory();
    factory.setProperties(null);
    Transaction transaction = factory.newTransaction(connection);
    transaction.getConnection();
    transaction.close();
    assertTrue(connection.getAutoCommit());
  }

  @Test
  void testSkipSetAutoCommitOnClose() throws Exception {
    TestConnection connection = new TestConnection(false);
    DataSource ds = mock(DataSource.class);
    when(ds.getConnection()).thenReturn(connection);

    JdbcTransactionFactory factory = new JdbcTransactionFactory();
    Properties properties = new Properties();
    properties.setProperty("skipSetAutoCommitOnClose", "true");
    factory.setProperties(properties);
    Transaction transaction = factory.newTransaction(ds, TransactionIsolationLevel.NONE, false);
    transaction.getConnection();
    transaction.close();
    assertFalse(connection.getAutoCommit());
  }

}

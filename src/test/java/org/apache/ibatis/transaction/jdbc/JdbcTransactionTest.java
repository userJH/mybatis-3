package org.apache.ibatis.transaction.jdbc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.junit.jupiter.api.Test;

class JdbcTransactionTest {
  @Test
  void testSetAutoCommitOnClose() throws Exception {
    testAutoCommit(true, false, true, false);
    testAutoCommit(false, false, true, false);
    testAutoCommit(true, true, true, false);
    testAutoCommit(false, true, true, false);
    testAutoCommit(true, false, false, true);
    testAutoCommit(false, false, false, true);
    testAutoCommit(true, true, true, true);
    testAutoCommit(false, true, true, true);
  }

  private void testAutoCommit(boolean initialAutoCommit, boolean desiredAutoCommit, boolean resultAutoCommit,
      boolean skipSetAutoCommitOnClose) throws Exception {
    TestConnection con = new TestConnection(initialAutoCommit);
    DataSource ds = mock(DataSource.class);
    when(ds.getConnection()).thenReturn(con);

    JdbcTransaction transaction = new JdbcTransaction(ds, TransactionIsolationLevel.NONE, desiredAutoCommit, skipSetAutoCommitOnClose);
    transaction.getConnection();
    transaction.commit();
    transaction.close();

    assertEquals(resultAutoCommit, con.getAutoCommit());
  }
}

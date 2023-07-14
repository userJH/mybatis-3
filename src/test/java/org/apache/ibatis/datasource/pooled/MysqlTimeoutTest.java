package org.apache.ibatis.datasource.pooled;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.testcontainers.MysqlContainer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("TestcontainersTests")
public class MysqlTimeoutTest {

  @Test
  void shouldReconnectWhenServerKilledLeakedConnection() throws Exception {
    // See #748
    PooledDataSource ds = MysqlContainer.getPooledDataSource();
    ds.setPoolMaximumActiveConnections(1);
    ds.setPoolMaximumIdleConnections(1);
    ds.setPoolTimeToWait(1000);
    ds.setPoolMaximumCheckoutTime(2000);
    ds.setPoolPingEnabled(true);
    ds.setPoolPingQuery("select 1");
    ds.setDefaultAutoCommit(true);
    // MySQL wait_timeout * 1000 or less. (unit:ms)
    ds.setPoolPingConnectionsNotUsedFor(1000);

    Connection con1 = ds.getConnection();

    Statement stmt = con1.createStatement();
    stmt.execute("set session wait_timeout = 3");

    executeQuery(con1);
    // Simulate connection leak by not closing.
    // con1.close();

    // Wait for disconnected from mysql...
    Thread.sleep(TimeUnit.SECONDS.toMillis(3));

    // Should return usable connection.
    Connection con2 = ds.getConnection();
    executeQuery(con2);

    con1.close();
    con2.close();
  }

  private void executeQuery(Connection con) throws SQLException {
    try (PreparedStatement st = con.prepareStatement("select 1");
        ResultSet rs = st.executeQuery()) {
      while (rs.next()) {
        assertEquals(1, rs.getInt(1));
      }
    }
  }

}

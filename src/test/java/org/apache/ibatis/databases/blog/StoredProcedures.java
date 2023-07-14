package org.apache.ibatis.databases.blog;

import java.sql.*;

public class StoredProcedures {
  public static void selectTwoSetsOfTwoAuthors(int p1, int p2, ResultSet[] rs1, ResultSet[] rs2) throws SQLException {
    try (Connection conn = DriverManager.getConnection("jdbc:default:connection")) {
      PreparedStatement ps1 = conn.prepareStatement("select * from author where id in (?,?)");
      ps1.setInt(1, p1);
      ps1.setInt(2, p2);
      rs1[0] = ps1.executeQuery();
      PreparedStatement ps2 = conn.prepareStatement("select * from author where id in (?,?)");
      ps2.setInt(1, p2);
      ps2.setInt(2, p1);
      rs2[0] = ps2.executeQuery();
    }
  }

  public static void insertAuthor(int id, String username, String password, String email) throws SQLException {
    try (Connection conn = DriverManager.getConnection("jdbc:default:connection")) {
      PreparedStatement ps = conn.prepareStatement("INSERT INTO author (id, username, password, email) VALUES (?,?,?,?)");
      ps.setInt(1, id);
      ps.setString(2, username);
      ps.setString(3, password);
      ps.setString(4, email);
      ps.executeUpdate();
    }
  }

  public static void selectAuthorViaOutParams(int id, String[] username, String[] password, String[] email, String[] bio) throws SQLException {
    try (Connection conn = DriverManager.getConnection("jdbc:default:connection")) {
      PreparedStatement ps = conn.prepareStatement("select * from author where id = ?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      rs.next();
      username[0] = rs.getString("username");
      password[0] = rs.getString("password");
      email[0] = rs.getString("email");
      bio[0] = rs.getString("bio");
    }
  }
}

package org.apache.ibatis.jdbc;

import static org.apache.ibatis.jdbc.SqlBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SqlBuilderTest {

  @Test
  void shouldProduceExpectedSimpleSelectStatement() {
    String expected =
        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n" +
            "FROM PERSON P\n" +
            "WHERE (P.ID like #id# AND P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName#)\n" +
            "ORDER BY P.LAST_NAME";
    assertEquals(expected, example2("a", "b", "c"));
  }

  @Test
  void shouldProduceExpectedSimpleSelectStatementMissingFirstParam() {
    String expected =
        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n" +
            "FROM PERSON P\n" +
            "WHERE (P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName#)\n" +
            "ORDER BY P.LAST_NAME";
    assertEquals(expected, example2(null, "b", "c"));
  }

  @Test
  void shouldProduceExpectedSimpleSelectStatementMissingFirstTwoParams() {
    String expected =
        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n" +
            "FROM PERSON P\n" +
            "WHERE (P.LAST_NAME like #lastName#)\n" +
            "ORDER BY P.LAST_NAME";
    assertEquals(expected, example2(null, null, "c"));
  }

  @Test
  void shouldProduceExpectedSimpleSelectStatementMissingAllParams() {
    String expected =
        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n" +
            "FROM PERSON P\n" +
            "ORDER BY P.LAST_NAME";
    assertEquals(expected, example2(null, null, null));
  }

  @Test
  void shouldProduceExpectedComplexSelectStatement() {
    String expected =
        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON\n" +
            "FROM PERSON P, ACCOUNT A\n" +
            "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID\n" +
            "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID\n" +
            "WHERE (P.ID = A.ID AND P.FIRST_NAME like ?) \n" +
            "OR (P.LAST_NAME like ?)\n" +
            "GROUP BY P.ID\n" +
            "HAVING (P.LAST_NAME like ?) \n" +
            "OR (P.FIRST_NAME like ?)\n" +
            "ORDER BY P.ID, P.FULL_NAME";
    assertEquals(expected, example1());
  }

  private static String example1() {
    SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
    SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
    FROM("PERSON P");
    FROM("ACCOUNT A");
    INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
    INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
    WHERE("P.ID = A.ID");
    WHERE("P.FIRST_NAME like ?");
    OR();
    WHERE("P.LAST_NAME like ?");
    GROUP_BY("P.ID");
    HAVING("P.LAST_NAME like ?");
    OR();
    HAVING("P.FIRST_NAME like ?");
    ORDER_BY("P.ID");
    ORDER_BY("P.FULL_NAME");
    return SQL();
  }

  private static String example2(String id, String firstName, String lastName) {
    SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME");
    FROM("PERSON P");
    if (id != null) {
      WHERE("P.ID like #id#");
    }
    if (firstName != null) {
      WHERE("P.FIRST_NAME like #firstName#");
    }
    if (lastName != null) {
      WHERE("P.LAST_NAME like #lastName#");
    }
    ORDER_BY("P.LAST_NAME");
    return SQL();
  }

}

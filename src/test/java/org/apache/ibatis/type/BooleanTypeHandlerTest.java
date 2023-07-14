package org.apache.ibatis.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class BooleanTypeHandlerTest extends BaseTypeHandlerTest {

  private static final TypeHandler<Boolean> TYPE_HANDLER = new BooleanTypeHandler();

  @Override
  @Test
  public void shouldSetParameter() throws Exception {
    TYPE_HANDLER.setParameter(ps, 1, true, null);
    verify(ps).setBoolean(1, true);
  }

  @Override
  @Test
  public void shouldGetResultFromResultSetByName() throws Exception {
    when(rs.getBoolean("column")).thenReturn(true, false);
    assertEquals(true, TYPE_HANDLER.getResult(rs, "column"));
    assertEquals(false, TYPE_HANDLER.getResult(rs, "column"));
  }

  @Override
  @Test
  public void shouldGetResultNullFromResultSetByName() throws Exception {
    when(rs.getBoolean("column")).thenReturn(false);
    when(rs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(rs, "column"));
  }

  @Override
  @Test
  public void shouldGetResultFromResultSetByPosition() throws Exception {
    when(rs.getBoolean(1)).thenReturn(true, false);
    assertEquals(true, TYPE_HANDLER.getResult(rs, 1));
    assertEquals(false, TYPE_HANDLER.getResult(rs, 1));
  }

  @Override
  @Test
  public void shouldGetResultNullFromResultSetByPosition() throws Exception {
    when(rs.getBoolean(1)).thenReturn(false);
    when(rs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(rs, 1));
  }

  @Override
  @Test
  public void shouldGetResultFromCallableStatement() throws Exception {
    when(cs.getBoolean(1)).thenReturn(true, false);
    assertEquals(true, TYPE_HANDLER.getResult(cs, 1));
    assertEquals(false, TYPE_HANDLER.getResult(cs, 1));
  }

  @Override
  @Test
  public void shouldGetResultNullFromCallableStatement() throws Exception {
    when(cs.getBoolean(1)).thenReturn(false);
    when(cs.wasNull()).thenReturn(true);
    assertNull(TYPE_HANDLER.getResult(cs, 1));
  }

}

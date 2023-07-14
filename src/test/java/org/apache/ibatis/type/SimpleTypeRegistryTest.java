package org.apache.ibatis.type;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.apache.ibatis.domain.misc.RichType;
import org.junit.jupiter.api.Test;

class SimpleTypeRegistryTest {

  @Test
  void shouldTestIfClassIsSimpleTypeAndReturnTrue() {
    assertTrue(SimpleTypeRegistry.isSimpleType(String.class));
  }

  @Test
  void shouldTestIfClassIsSimpleTypeAndReturnFalse() {
    assertFalse(SimpleTypeRegistry.isSimpleType(RichType.class));
  }

  @Test
  void shouldTestIfMapIsSimpleTypeAndReturnFalse() {
    assertFalse(SimpleTypeRegistry.isSimpleType(HashMap.class)); // see issue #165, a Map is not a simple type
  }

}

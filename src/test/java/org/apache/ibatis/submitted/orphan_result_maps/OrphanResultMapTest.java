package org.apache.ibatis.submitted.orphan_result_maps;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;

class OrphanResultMapTest {

  private static String RESULT_MAP_BLOG = "BlogResultMap";
  private static String RESULT_MAP_POST = "PostResultMap";
  private static String RESULT_MAP_INNER = "mapper_resultMap[BlogResultMap]_collection[posts]";

  @Test
  void testSeparateResultMaps() {
    // given
    Configuration configuration = new Configuration();
    configuration.getTypeAliasRegistry().registerAlias(Blog.class);
    configuration.getTypeAliasRegistry().registerAlias(Post.class);
    configuration.addMapper(SeparateCollectionMapper.class);

    // there should be two result maps declared, with two name variants each
    assertEquals(4, configuration.getResultMaps().size());

    // test short names
    assertNotNull(configuration.getResultMap(RESULT_MAP_BLOG));
    assertNotNull(configuration.getResultMap(RESULT_MAP_POST));
    assertThrows(IllegalArgumentException.class, () -> configuration.getResultMap(RESULT_MAP_INNER));

    // test long names
    String prefix = SeparateCollectionMapper.class.getName() + ".";
    assertNotNull(configuration.getResultMap(prefix + RESULT_MAP_BLOG));
    assertNotNull(configuration.getResultMap(prefix + RESULT_MAP_POST));
    assertThrows(IllegalArgumentException.class, () -> configuration.getResultMap(prefix + RESULT_MAP_INNER));
  }

  @Test
  void testNestedResultMap() {
    // given
    Configuration configuration = new Configuration();
    configuration.getTypeAliasRegistry().registerAlias(Blog.class);
    configuration.getTypeAliasRegistry().registerAlias(Post.class);
    configuration.addMapper(NestedCollectionMapper.class);

    // there should be two result maps declared, with two name variants each
    assertEquals(4, configuration.getResultMaps().size());

    // test short names
    assertNotNull(configuration.getResultMap(RESULT_MAP_BLOG));
    assertNotNull(configuration.getResultMap(RESULT_MAP_INNER));

    // test long names
    String prefix = NestedCollectionMapper.class.getName() + ".";
    assertNotNull(configuration.getResultMap(prefix + RESULT_MAP_BLOG));
    assertNotNull(configuration.getResultMap(prefix + RESULT_MAP_INNER));
  }

}

package org.apache.ibatis.submitted.refid_resolution;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

/**
 * @see <a href="https://github.com/mybatis/old-google-code-issues/issues/291>Issue 291</a>
 *
 */
class ExternalRefidResolutionTest {
  @Test
  void testExternalRefAfterSelectKey() throws Exception {
    String resource = "org/apache/ibatis/submitted/refid_resolution/ExternalMapperConfig.xml";
    try (Reader reader = Resources.getResourceAsReader(resource)) {
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      SqlSessionFactory sqlSessionFactory = builder.build(reader);
      sqlSessionFactory.getConfiguration().getMappedStatementNames();
    }
  }
}

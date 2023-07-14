package org.apache.ibatis.builder;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;

class AnnotationMapperBuilderTest {

  @Test
  void withOptions() {
    Configuration configuration = new Configuration();
    MapperAnnotationBuilder builder = new MapperAnnotationBuilder(configuration, Mapper.class);
    builder.parse();

    MappedStatement mappedStatement = configuration.getMappedStatement("selectWithOptions");
    assertThat(mappedStatement.getFetchSize()).isEqualTo(200);
    assertThat(mappedStatement.getTimeout()).isEqualTo(10);
    assertThat(mappedStatement.getStatementType()).isEqualTo(StatementType.STATEMENT);
    assertThat(mappedStatement.getResultSetType()).isEqualTo(ResultSetType.SCROLL_INSENSITIVE);
    assertThat(mappedStatement.isFlushCacheRequired()).isTrue();
    assertThat(mappedStatement.isUseCache()).isFalse();
    assertThat(mappedStatement.getResultSets()).containsExactly("resultSets");

    mappedStatement = configuration.getMappedStatement("insertWithOptions");
    assertThat(mappedStatement.getKeyGenerator()).isInstanceOf(Jdbc3KeyGenerator.class);
    assertThat(mappedStatement.getKeyColumns()).containsExactly("key_column");
    assertThat(mappedStatement.getKeyProperties()).containsExactly("keyProperty");
  }

  @Test
  void withOptionsAndWithoutOptionsAttributesWhenSpecifyDefaultValue() {
    Configuration configuration = new Configuration();
    configuration.setDefaultResultSetType(ResultSetType.SCROLL_INSENSITIVE);
    MapperAnnotationBuilder builder = new MapperAnnotationBuilder(configuration, Mapper.class);
    builder.parse();

    MappedStatement mappedStatement = configuration.getMappedStatement("selectWithOptionsAndWithoutOptionsAttributes");
    assertThat(mappedStatement.getResultSetType()).isEqualTo(ResultSetType.SCROLL_INSENSITIVE);
  }


  @Test
  void withOptionsAndWithoutOptionsAttributesWhenNotSpecifyDefaultValue() {
    Configuration configuration = new Configuration();
    MapperAnnotationBuilder builder = new MapperAnnotationBuilder(configuration, Mapper.class);
    builder.parse();

    MappedStatement mappedStatement = configuration.getMappedStatement("selectWithOptionsAndWithoutOptionsAttributes");
    assertThat(mappedStatement.getResultSetType()).isEqualTo(ResultSetType.DEFAULT);
  }

  @Test
  void withoutOptionsWhenSpecifyDefaultValue() {
    Configuration configuration = new Configuration();
    configuration.setDefaultResultSetType(ResultSetType.SCROLL_INSENSITIVE);
    MapperAnnotationBuilder builder = new MapperAnnotationBuilder(configuration, Mapper.class);
    builder.parse();

    MappedStatement mappedStatement = configuration.getMappedStatement("selectWithoutOptions");
    assertThat(mappedStatement.getResultSetType()).isEqualTo(ResultSetType.SCROLL_INSENSITIVE);
  }

  @Test
  void withoutOptionsWhenNotSpecifyDefaultValue() {
    Configuration configuration = new Configuration();
    MapperAnnotationBuilder builder = new MapperAnnotationBuilder(configuration, Mapper.class);
    builder.parse();

    MappedStatement mappedStatement = configuration.getMappedStatement("selectWithoutOptions");
    assertThat(mappedStatement.getResultSetType()).isEqualTo(ResultSetType.DEFAULT);
  }

  interface Mapper {

    @Insert("insert into test (name) values(#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "key_column", keyProperty = "keyProperty")
    void insertWithOptions(String name);

    @Select("select * from test")
    @Options(fetchSize = 200, timeout = 10, statementType = StatementType.STATEMENT, resultSetType = ResultSetType.SCROLL_INSENSITIVE, flushCache = Options.FlushCachePolicy.TRUE, useCache = false, resultSets = "resultSets")
    String selectWithOptions(Integer id);

    @Select("select * from test")
    @Options
    String selectWithOptionsAndWithoutOptionsAttributes(Integer id);

    @Select("select * from test")
    String selectWithoutOptions(Integer id);

  }

}

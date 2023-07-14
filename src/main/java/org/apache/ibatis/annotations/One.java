package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.mapping.FetchType;

/**
 * The annotation that specify the nested statement for retrieving single object.
 *
 * @see Result
 * @see Results
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface One {
  /**
   * Returns the columnPrefix.
   *
   * @return the columnPrefix.
   * @since 3.5.5
   */
  String columnPrefix() default "";

  /**
   * Returns the result map id used to map single object.
   *
   * @return the result map id
   * @since 3.5.5
   */
  String resultMap() default "";

  /**
   * Returns the statement id that retrieves single object.
   *
   * @return the statement id
   */
  String select() default "";

  /**
   * Returns the fetch strategy for nested statement.
   *
   * @return the fetch strategy
   */
  FetchType fetchType() default FetchType.DEFAULT;

}

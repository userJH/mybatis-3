package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify a method that provide an SQL for retrieving record(s).
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *
 *   &#064;SelectProvider(type = SqlProvider.class, method = "selectById")
 *   User selectById(int id);
 *
 *   public static class SqlProvider {
 *     public static String selectById() {
 *       return "SELECT id, name FROM users WHERE id = #{id}";
 *     }
 *   }
 *
 * }
 * </pre>
 *
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(SelectProvider.List.class)
public @interface SelectProvider {

  /**
   * Specify a type that implements an SQL provider method.
   *
   * @return a type that implements an SQL provider method
   * @since 3.5.2
   * @see #type()
   */
  Class<?> value() default void.class;

  /**
   * Specify a type that implements an SQL provider method.
   * <p>
   * This attribute is alias of {@link #value()}.
   * </p>
   *
   * @return a type that implements an SQL provider method
   * @see #value()
   */
  Class<?> type() default void.class;

  /**
   * Specify a method for providing an SQL.
   *
   * <p>
   * Since 3.5.1, this attribute can omit.
   * If this attribute omit, the MyBatis will call a method that decide by following rules.
   * <ul>
   *   <li>
   *     If class that specified the {@link #type()} attribute implements the
   *     {@link org.apache.ibatis.builder.annotation.ProviderMethodResolver},
   *     the MyBatis use a method that returned by it
   *   </li>
   *   <li>
   *     If cannot resolve a method by {@link org.apache.ibatis.builder.annotation.ProviderMethodResolver}(= not implement it or it was returned {@code null}),
   *     the MyBatis will search and use a fallback method that named {@code provideSql} from specified type
   *   </li>
   * </ul>
   *
   * @return a method name of method for providing an SQL
   */
  String method() default "";

  /**
   * @return A database id that correspond this provider
   * @since 3.5.5
   */
  String databaseId() default "";

  /**
   * Returns whether this select affects DB data.<br>
   * e.g. RETURNING of PostgreSQL or OUTPUT of MS SQL Server.
   *
   * @return {@code true} if this select affects DB data; {@code false} if otherwise
   * @since 3.5.12
   */
  boolean affectData() default false;

  /**
   * The container annotation for {@link SelectProvider}.
   * @author Kazuki Shimizu
   * @since 3.5.5
   */
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  @interface List {
    SelectProvider[] value();
  }

}

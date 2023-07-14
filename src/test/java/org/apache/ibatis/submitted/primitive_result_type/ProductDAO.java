package org.apache.ibatis.submitted.primitive_result_type;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class ProductDAO {

  public static List<Integer> selectProductCodes() {
    try (SqlSession session = IbatisConfig.getSession()) {
      ProductMapper productMapper = session.getMapper(ProductMapper.class);
      return productMapper.selectProductCodes();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Long> selectProductCodesL() {
    try (SqlSession session = IbatisConfig.getSession()) {
      ProductMapper productMapper = session.getMapper(ProductMapper.class);
      return productMapper.selectProductCodesL();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static List<BigDecimal> selectProductCodesB() {
    try (SqlSession session = IbatisConfig.getSession()) {
      ProductMapper productMapper = session.getMapper(ProductMapper.class);
      return productMapper.selectProductCodesB();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Product> selectAllProducts() {
    try (SqlSession session = IbatisConfig.getSession()) {
      ProductMapper productMapper = session.getMapper(ProductMapper.class);
      return productMapper.selectAllProducts();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}

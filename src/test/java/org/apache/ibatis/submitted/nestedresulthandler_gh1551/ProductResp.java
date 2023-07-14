package org.apache.ibatis.submitted.nestedresulthandler_gh1551;

import java.util.List;

public class ProductResp {

  private String id;
  private String code;
  private String name;
  private List<ProductSku> skus;
  private ProductInfo productInfo;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ProductSku> getSkus() {
    return skus;
  }

  public void setSkus(List<ProductSku> skus) {
    this.skus = skus;
  }

  public ProductInfo getProductInfo() {
    return productInfo;
  }

  public void setProductInfo(ProductInfo productInfo) {
    this.productInfo = productInfo;
  }

}

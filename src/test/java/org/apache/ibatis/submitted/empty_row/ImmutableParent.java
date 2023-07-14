package org.apache.ibatis.submitted.empty_row;

public class ImmutableParent {
  private final String col1;
  private final String col2;

  public ImmutableParent(String col1, String col2) {
    super();
    this.col1 = col1;
    this.col2 = col2;
  }

  public String getCol1() {
    return col1;
  }

  public String getCol2() {
    return col2;
  }
}

package org.apache.ibatis.submitted.record_type;

public record Property(int id, String value, String URL) {
  public String value() {
    // Differentiate between method call and field access
    return value + "!";
  }
}

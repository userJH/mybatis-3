package org.apache.ibatis.submitted.keygen;

// See gh-1719
public class NpeCountry {
  private Integer id;
  private String countryname;
  private String countrycode;

  public NpeCountry() {
  }

  public NpeCountry(String countryname, String countrycode) {
    this.countryname = countryname;
    this.countrycode = countrycode;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCountryname() {
    return countryname;
  }

  public void setCountryname(String countryname) {
    this.countryname = countryname;
  }

  public String getCountrycode() {
    return countrycode;
  }

  public void setCountrycode(String countrycode) {
    this.countrycode = countrycode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NpeCountry other = (NpeCountry) o;
    // throws NPE when id is null
    return id.equals(other.id);
  }

  @Override
  public int hashCode() {
    // throws NPE when id is null
    return id.hashCode();
  }
}

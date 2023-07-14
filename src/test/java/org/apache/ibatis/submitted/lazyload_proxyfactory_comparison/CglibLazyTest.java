package org.apache.ibatis.submitted.lazyload_proxyfactory_comparison;

import org.junit.jupiter.api.Tag;

@Tag("RequireIllegalAccess")
class CglibLazyTest extends AbstractLazyTest {
  @Override
  protected String getConfiguration() {
    return "cglib";
  }
}

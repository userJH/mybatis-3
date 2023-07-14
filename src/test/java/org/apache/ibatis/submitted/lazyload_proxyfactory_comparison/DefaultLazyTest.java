package org.apache.ibatis.submitted.lazyload_proxyfactory_comparison;

class DefaultLazyTest extends AbstractLazyTest {
  @Override
  protected String getConfiguration() {
    return "default";
  }
}

package org.apache.ibatis.submitted.nestedresulthandler_gh1551;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
  List<ProductResp> selectAllInfo(@Param("code") String code);
}

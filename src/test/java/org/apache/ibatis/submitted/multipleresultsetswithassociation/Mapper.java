package org.apache.ibatis.submitted.multipleresultsetswithassociation;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface Mapper {

  List<OrderDetail> getOrderDetailsWithHeaders();

  @Select(value = "{ call GetOrderDetailsAndHeaders() }")
  @ResultMap("orderDetailResultMap")
  @Options(statementType = StatementType.CALLABLE, resultSets = "orderDetailResultSet,orderHeaderResultSet")
  List<OrderDetail> getOrderDetailsWithHeadersAnnotationBased();

}

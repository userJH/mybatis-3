package org.apache.ibatis.submitted.association_nested;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author Loïc Guerrin <guerrin@fullsix.com>
 */
public interface FolderMapper {

  List<FolderFlatTree> findWithSubFolders(@Param("name") String name);

}

package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GroupUserMapper {
    Integer getLevel(@Param("group") Group group, Integer user);

    Integer insertMem(@Param("group") Group group, Integer user);

    Integer setAdmin(@Param("group") Group group, Integer user);

    Integer deleteMem(@Param("group") Group group, Integer user);

    Integer insertGroup(Integer groupId, Integer userId);
}

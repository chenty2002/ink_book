package com.summerProject.ink_book.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupUserMapper {
    Integer getLevel(Integer group, Integer user);

    Integer insertMem(Integer group, Integer user);

    Integer setAdmin(Integer group, Integer user);

    Integer deleteMem(Integer group, Integer user);

    Integer insertGroup(Integer groupId, Integer userId);

    Integer deleteGroup(Integer groupId);
}

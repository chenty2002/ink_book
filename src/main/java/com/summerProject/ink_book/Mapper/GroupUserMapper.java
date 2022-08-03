package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GroupUserMapper {
    Integer getLevel(@Param("group") Group group,Integer user);
    List<User> selectMemByGroup(Group group);
    Integer insertMem(@Param("group") Group group, Integer user);
    Integer setAdmin(@Param("group") Group group, Integer user);
    Integer deleteMem(@Param("group") Group group, Integer user);
    Integer insertGroup(Integer groupId, Integer userId);
}

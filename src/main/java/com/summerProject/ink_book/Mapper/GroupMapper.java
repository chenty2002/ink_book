package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
    Group selectGroupById(Integer id);

    Integer insertGroup(Group group);

    List<Group> selectGroupByMem(Integer id);

    List<Group> selectGroupByMemPage(Integer id, Integer page, Integer size);

    List<Group> selectGroupByFounder(Integer id);

    List<Group> selectGroupByFounderPage(Integer id, Integer page, Integer size);

    List<User> selectUserByGroup(Integer id);

    List<User> selectUserByGroupPage(Integer id, Integer page, Integer size);

    List<User> selectUserByGroupLevel(Integer id, Integer level);

    Integer updateGroup(Group group);
}

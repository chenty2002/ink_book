package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GroupMapper {
    Group selectGroupById(Integer id);
    Integer insertGroup(Group group);
    List<Group> selectGroupByMem(User user);
    List<Group> selectGroupByFounder(User user);
}

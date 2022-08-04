package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectUserMapper {
    Integer insertProjectUser(Integer pid, Integer uid);

    List<User> selectProjectUser(Integer projectId);

    Integer deleteProjectUser(Integer projectId, Integer userId);
}

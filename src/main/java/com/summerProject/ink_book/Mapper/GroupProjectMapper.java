package com.summerProject.ink_book.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupProjectMapper {
    Integer insertProject(Integer pid, Integer gid);

    Integer deleteProjectById(Integer projectId);

    Integer deleteGroupProject(Integer groupId);
}

package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    Integer insertProject(Project project);

    Integer deleteProjectById(Integer projectId);

    Project selectProjectById(Integer projectId);

    Integer modifyProject(Project project);

    List<Project> selectProjectByGroup(Integer id, Integer deleted);

    List<Project> selectProjectByCons(String word);
}

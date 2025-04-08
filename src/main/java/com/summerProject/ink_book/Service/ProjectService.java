package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface ProjectService {
    Result<Project> newProject(Project project, Integer userId, Integer groupId);

    Result<String> deleteProject(Integer userId, Integer groupId, Integer projectId);

    Result<String> modifyProject(Project project);

    Result<Project> getProjectInfo(Integer projectId);

    Result<List<Project>> getGroupProject(Integer id, Integer deleted, String sort);

    Result<List<Project>> getProjectByCons(Integer groupId, String word);
}

package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Utils.Result;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectService {
    Result<Project> newProject(Project project, Integer groupId);

    Result<String> deleteProject(Integer projectId);

    Result<String> modifyProject(Project project);

    Result<Project> getProjectInfo(Integer projectId);

    Result<List<Project>> getGroupProject(Integer id, Integer deleted);

    Result<List<Project>> getProjectByCons(Integer groupId, String word, LocalDateTime start, LocalDateTime end, Integer deleted);

    Result<String> restoreProject(Integer projectId);
}

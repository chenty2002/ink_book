package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface ProjectService {
    Result<Project> NewProject(Project project);

    Result<Project> DeleteProject(Integer projectId);

    Result<Project> ModifyProject(Project project);

    Result<Project> getProjectInfo(Integer projectId);

    Result<List<Project>> getUserProject(Integer id, Integer deleted);

    Result<List<Project>> getGroupProject(Integer id, Integer deleted);

    Result<List<Project>> getLeaderProject(Integer id, Integer deleted);
}

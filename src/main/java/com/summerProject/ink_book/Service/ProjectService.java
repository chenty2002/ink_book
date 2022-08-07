package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface ProjectService {
    Result<Project> newProject(Project project, Integer userId);

    Result<String> deleteProject(Integer projectId);

    Result<String> modifyProject(Project project);

    Result<Project> getProjectInfo(Integer projectId);

    Result<List<Project>> getUserProject(Integer id, Integer deleted);

    Result<List<Project>> getGroupProject(Integer id, Integer deleted);

    Result<List<Project>> getLeaderProject(Integer id, Integer deleted);

    Result<String> NewProjectUser(Integer projectId, Integer userId);

    Result<List<User>> getProjectUser(Integer pid);

    Result<String> deleteProjectUser(Integer pid, Integer uid);
}

package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Mapper.ProjectMapper;
import com.summerProject.ink_book.Mapper.ProjectUserMapper;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final ProjectUserMapper projectUserMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectUserMapper projectUserMapper) {
        this.projectMapper = projectMapper;
        this.projectUserMapper = projectUserMapper;
    }

    @Override
    public Result<Project> newProject(Project project, Integer userId) {
        if (projectMapper.insertProject(project) > 0) {//新增项目成功
            if (projectUserMapper.insertProjectUser(project.getProjectId(), userId) > 0) {
                project.setDeleted(0);
                return Result.success("success", project);
            }
        }
        return Result.fail("Failure");
    }

    @Override
    public Result<String> deleteProject(Integer projectId) {
        if (projectMapper.deleteProjectById(projectId) > 0) {
            projectUserMapper.deleteProject(projectId);
            return Result.success("delete project success", "");
        }
        return Result.fail("delete project Failure");
    }

    @Override
    public Result<String> modifyProject(Project project) {
        if (projectMapper.modifyProject(project) > 0)
            return Result.success("Modify Project Success", "");
        return Result.fail("modify project Failure");
    }

    @Override
    public Result<Project> getProjectInfo(Integer projectId) {
        Project result = projectMapper.selectProjectById(projectId);
        if (result != null) {
            return Result.success("Success", result);
        }
        return Result.fail("Project does not exist");
    }

    @Override
    public Result<List<Project>> getUserProject(Integer id, Integer deleted) {
        List<Project> projects = projectMapper.selectProjectByUser(id, deleted);
        return Result.success("All Projects of One User", projects);
    }

    @Override
    public Result<List<Project>> getGroupProject(Integer id, Integer deleted) {
        List<Project> projects = projectMapper.selectProjectByGroup(id, deleted);
        return Result.success("All Projects of One Group", projects);
    }

    @Override
    public Result<List<Project>> getLeaderProject(Integer id, Integer deleted) {
        List<Project> projects = projectMapper.selectProjectByLeader(id, deleted);
        return Result.success("All Projects of One Leader", projects);
    }

    @Override
    public Result<String> NewProjectUser(Integer projectId, Integer userId) {
        if (projectUserMapper.insertProjectUser(projectId, userId) > 0)
            return Result.success("New User Joined Project", "");
        return Result.fail("New User not Joined Project");
    }

    @Override
    public Result<List<User>> getProjectUser(Integer pid) {
        List<User> users = projectUserMapper.selectProjectUser(pid);
        return Result.success("All Project Users", users);
    }

    @Override
    public Result<String> deleteProjectUser(Integer pid, Integer uid) {
        if (projectUserMapper.deleteProjectUser(pid, uid) > 0)
            return Result.success("Project User Deleted", "");
        return Result.fail("Project User not Deleted");
    }
}

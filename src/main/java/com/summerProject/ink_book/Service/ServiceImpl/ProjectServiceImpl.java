package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Mapper.GroupProjectMapper;
import com.summerProject.ink_book.Mapper.ProjectMapper;
import com.summerProject.ink_book.Service.GroupService;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Utils.Result;
import com.summerProject.ink_book.Utils.UserLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final GroupProjectMapper groupProjectMapper;

    private final GroupService groupService;

    public ProjectServiceImpl(ProjectMapper projectMapper, GroupProjectMapper groupProjectMapper, GroupService groupService) {
        this.projectMapper = projectMapper;
        this.groupProjectMapper = groupProjectMapper;
        this.groupService = groupService;
    }

    @Override
    public Result<Project> newProject(Project project, Integer userId, Integer groupId) {
        if (!groupService.isAdmin(groupId, userId, UserLevel.ADMINISTRATOR.getCode()))
            return Result.fail("Unauthorized");
        if (projectMapper.insertProject(project) > 0) {//新增项目成功
            if (groupProjectMapper.insertProject(project.getProjectId(), groupId) > 0) {
                project.setDeleted(0);
                return Result.success("success", project);
            }
        }
        return Result.fail("Failure");
    }

    @Override
    public Result<String> deleteProject(Integer userId, Integer groupId, Integer projectId) {
        if (!groupService.isAdmin(groupId, userId, UserLevel.ADMINISTRATOR.getCode()))
            return Result.fail("Unauthorized");
        if (projectMapper.deleteProjectById(projectId) > 0) {
            groupProjectMapper.deleteProjectById(projectId);
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
    public Result<List<Project>> getGroupProject(Integer id, Integer deleted) {
        List<Project> projects = projectMapper.selectProjectByGroup(id, deleted);
        return Result.success("All Projects of a Group", projects);
    }

    @Override
    public Result<List<Project>> getProjectByCons(String word) {
        return Result.success("All Fitting Projects", projectMapper.selectProjectByCons("%" + word + "%"));
    }
}

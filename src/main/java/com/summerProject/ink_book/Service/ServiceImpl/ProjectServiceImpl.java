package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Mapper.GroupProjectMapper;
import com.summerProject.ink_book.Mapper.ProjectMapper;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final GroupProjectMapper groupProjectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper, GroupProjectMapper groupProjectMapper) {
        this.projectMapper = projectMapper;
        this.groupProjectMapper = groupProjectMapper;
    }

    @Override
    public Result<Project> newProject(Project project, Integer groupId) {
        if (projectMapper.insertProject(project) > 0) {//新增项目成功
            if (groupProjectMapper.insertProject(project.getProjectId(), groupId) > 0) {
                project.setDeleted(0);
                return Result.success("success", project);
            }
        }
        return Result.fail("Failure");
    }

    @Override
    public Result<String> deleteProject(Integer projectId) {
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
    public Result<List<Project>> getProjectByCons(Integer groupId, String word, LocalDateTime start, LocalDateTime end, Integer deleted) {
        if (start == null)
            start = LocalDateTime.now().minusYears(10);
        if (end == null)
            end = LocalDateTime.now();
        if (word == null)
            word = "";
        return Result.success("All Fitting Projects", projectMapper.selectProjectByCons(groupId, "%" + word + "%", start, end, deleted));
    }

    @Override
    public Result<String> restoreProject(Integer projectId) {
        if (projectMapper.restoreProject(projectId) > 0)
            return Result.success("Project Restored", "");
        return Result.fail("Project not Restored");
    }
}

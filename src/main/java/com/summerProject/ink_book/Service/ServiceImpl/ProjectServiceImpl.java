package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Mapper.ProjectMapper;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public Result<Project> NewProject(Project project) {
        if (projectMapper.insertProject(project) > 0) {//新增项目成功
            return Result.success("success", project);
        } else {
            return Result.fail("Failure");
        }
    }

    @Override
    public Result<Project> DeleteProject(Integer projectId) {
        if (projectMapper.deleteProjectById(projectId) > 0) {
            return Result.success("delete project success", projectMapper.selectProjectById(projectId));
        }
        return Result.fail("delete project Failure");
    }

    @Override
    public Result<Project> ModifyProject(Project project) {
        if (projectMapper.modifyProject(project) > 0)
            return Result.success("Modify Project Success", project);
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

}

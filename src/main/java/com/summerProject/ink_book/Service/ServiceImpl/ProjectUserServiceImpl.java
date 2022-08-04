package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Mapper.ProjectUserMapper;
import com.summerProject.ink_book.Service.ProjectUserService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    private final ProjectUserMapper projectUserMapper;

    public ProjectUserServiceImpl(ProjectUserMapper userProjectMapper) {
        this.projectUserMapper = userProjectMapper;
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

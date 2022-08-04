package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface ProjectUserService {
    Result<String> NewProjectUser(Integer projectId, Integer userId);

    Result<List<User>> getProjectUser(Integer pid);

    Result<String> deleteProjectUser(Integer pid, Integer uid);

}

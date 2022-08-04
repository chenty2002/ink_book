package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface GroupService {
    Result<Group> createGroup(User founder, String groupName);

    Boolean isAdmin(Group group, User user);

    Result<String> memJoin(Group group, User admin, Integer user);

    Result<String> memDelete(Group group, User admin, Integer user);

    Result<String> setAdmin(Group group, User admin, Integer user);

    Result<List<User>> getAllMems(Integer id);

    Result<List<Group>> getAllGroup(Integer id);

    Result<List<Group>> getFoundedGroup(Integer id);
}

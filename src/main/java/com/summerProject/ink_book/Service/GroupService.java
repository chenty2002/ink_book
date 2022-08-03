package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface GroupService {
    Result<Group> createGroup(User founder, String groupName);

    Result<String> isAdmin(Group group, User user);

    Result<String> memJoin(Group group, User admin, Integer user);

    Result<String> setAdmin(Group group, User admin, Integer user);

    Result<List<User>> getAllMems(Group group);

    Result<List<Group>> getAllGroup(User user);

    Result<List<Group>> getFoundedGroup(User user);
}

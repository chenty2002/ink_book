package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface GroupService {
    Result<Group> createGroup(Integer founder, String groupName, String groupProfile);

    Boolean isAdmin(Integer group, Integer user, Integer level);

    Result<String> memJoin(Integer group, Integer user);

    Result<String> memDelete(Integer group, Integer user);

    Result<String> setAdmin(Integer group, Integer user);

    Result<List<User>> getAllMems(Integer id);

    Result<List<Group>> getAllGroup(Integer id);

    Result<List<Group>> getFoundedGroup(Integer id);

    Result<String> modifyGroup(Group group);

    Result<String> deleteGroup(Integer id);
}

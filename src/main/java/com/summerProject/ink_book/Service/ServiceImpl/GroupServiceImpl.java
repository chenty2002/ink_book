package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Mapper.GroupMapper;
import com.summerProject.ink_book.Mapper.GroupUserMapper;
import com.summerProject.ink_book.Service.GroupService;
import com.summerProject.ink_book.Utils.Result;
import com.summerProject.ink_book.Utils.UserLevel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupMapper groupMapper;
    private final GroupUserMapper groupUserMapper;

    public GroupServiceImpl(GroupMapper groupMapper, GroupUserMapper groupUserMapper) {
        this.groupMapper = groupMapper;
        this.groupUserMapper = groupUserMapper;
    }

    @Override
    public Result<Group> createGroup(User founder, String groupName) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupProfile("");
        group.setGroupCreateTime(LocalDateTime.now());
        group.setFounder(founder);
        if (groupMapper.insertGroup(group) > 0 && groupUserMapper.insertGroup(group.getGroupId(), founder.getUserId()) > 0)
            return Result.success("Group Created", group);
        else return Result.fail("Group not Created");
    }

    @Override
    public Result<String> isAdmin(Group group, User user) {
        Integer level = groupUserMapper.getLevel(group, user.getUserId());
        if (level == null)
            return Result.fail("User " + user.getUserEmail() + " not in the Group");
        else if (level.equals(UserLevel.MEMBER.getCode()))
            return Result.fail("User " + user.getUserEmail() + " not an admin");
        return Result.success("User " + user.getUserEmail() + " is an admin", "");
    }

    @Override
    public Result<String> memJoin(Group group, User admin, Integer user) {
        if (groupUserMapper.insertMem(group, user) > 0)
            return Result.success("Member Joined", "");
        else return Result.fail("Member not Joined");
    }

    @Override
    public Result<String> setAdmin(Group group, User admin, Integer user) {
        if (groupUserMapper.setAdmin(group, user) > 0)
            return Result.success("New Admin Set", "");
        return Result.fail("New Admin not Set");
    }

    public Result<List<User>> getAllMems(Group group) {
        return Result.success("All Group Members", groupMapper.selectUserByGroup(group.getGroupId()));
    }

    public Result<List<Group>> getAllGroup(User user) {
        return Result.success("All Participated Groups", groupMapper.selectGroupByMem(user.getUserId()));
    }

    public Result<List<Group>> getFoundedGroup(User user) {
        return Result.success("All Groups Founded", groupMapper.selectGroupByFounder(user.getUserId()));
    }
}

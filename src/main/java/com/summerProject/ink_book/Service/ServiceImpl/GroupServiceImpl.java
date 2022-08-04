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
    public Boolean isAdmin(Group group, User user) {
        Integer level = groupUserMapper.getLevel(group, user.getUserId());
        if (level == null)
            return false;
        else return !level.equals(UserLevel.MEMBER.getCode());
    }

    @Override
    public Result<String> memJoin(Group group, User admin, Integer user) {
        if (groupUserMapper.insertMem(group, user) > 0)
            return Result.success("Member Joined", "");
        else return Result.fail("Member not Joined");
    }

    @Override
    public Result<String> memDelete(Group group, User admin, Integer user) {
        if (groupUserMapper.deleteMem(group, user) > 0)
            return Result.success("Member Deleted", "");
        else return Result.fail("Member not Deleted");
    }

    @Override
    public Result<String> setAdmin(Group group, User admin, Integer user) {
        if (groupUserMapper.setAdmin(group, user) > 0)
            return Result.success("New Admin Set", "");
        return Result.fail("New Admin not Set");
    }

    public Result<List<User>> getAllMems(Integer id) {
        return Result.success("All Group Members", groupMapper.selectUserByGroup(id));
    }

    public Result<List<Group>> getAllGroup(Integer id) {
        return Result.success("All Participated Groups", groupMapper.selectGroupByMem(id));
    }

    public Result<List<Group>> getFoundedGroup(Integer id) {
        return Result.success("All Groups Founded", groupMapper.selectGroupByFounder(id));
    }
}

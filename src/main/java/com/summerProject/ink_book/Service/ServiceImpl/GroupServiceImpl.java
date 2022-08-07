package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Mapper.GroupMapper;
import com.summerProject.ink_book.Mapper.GroupUserMapper;
import com.summerProject.ink_book.Service.GroupService;
import com.summerProject.ink_book.Utils.Result;
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
    public Result<Group> createGroup(Integer founder, String groupName, String groupProfile) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupProfile(groupProfile);
        group.setGroupCreateTime(LocalDateTime.now());
        if (groupMapper.insertGroup(group) > 0 && groupUserMapper.insertGroup(group.getGroupId(), founder) > 0)
            return Result.success("Group Created", group);
        else return Result.fail("Group not Created");
    }

    @Override
    public Boolean isAdmin(Integer group, Integer user, Integer level) {
        Integer l = groupUserMapper.getLevel(group, user);
        if (l == null)
            return false;
        return l >= level;
    }

    @Override
    public Result<String> memJoin(Integer group, Integer user) {
        if (groupUserMapper.insertMem(group, user) > 0)
            return Result.success("Member Joined", "");
        else return Result.fail("Member not Joined");
    }

    @Override
    public Result<String> memDelete(Integer group, Integer user) {
        if (groupUserMapper.deleteMem(group, user) > 0)
            return Result.success("Member Deleted", "");
        else return Result.fail("Member not Deleted");
    }

    @Override
    public Result<String> setAdmin(Integer group, Integer user) {
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

    @Override
    public Result<String> modifyGroup(Group group) {
        if (groupMapper.updateGroup(group) > 0)
            return Result.success("Group Info Modified", "");
        return Result.fail("Group Info not Modified");
    }

    @Override
    public Result<String> deleteGroup(Integer id) {
        if (groupMapper.deleteGroup(id) > 0)
            if (groupUserMapper.deleteGroup(id) > 0)
                return Result.success("Delete Success", "");
        return Result.fail("Delete Fail");
    }
}

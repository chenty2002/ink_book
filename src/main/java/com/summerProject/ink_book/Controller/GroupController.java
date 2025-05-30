package com.summerProject.ink_book.Controller;

import com.alibaba.fastjson.JSONObject;
import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.GroupService;
import com.summerProject.ink_book.Utils.Result;
import com.summerProject.ink_book.Utils.UserLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupservice;

    public GroupController(GroupService groupservice) {
        this.groupservice = groupservice;
    }


    // 创建团队 POST url?传用户id 请求体传团队信息
    /*
        url: /createGroup?userId=
        请求体参数
        {
            "groupName": ,
            "groupProfile":
        }
     */
    @PostMapping("/createGroup")
    public Result<Group> createGroup(@RequestParam("userId") Integer id, @RequestBody JSONObject param) {
        log.info("[GroupController.createGroup] --- requesting creating group");
        String groupName = param.getString("groupName");
        String groupProfile = param.getString("groupProfile");
        return groupservice.createGroup(id, groupName, groupProfile);

    }

    // 用户加入团队 POST url?传userId 请求体传加入的用户id和groupId
    /*
        url: /memJoin?userId=
        请求体参数
        {
            "userId": ,
            "groupId":
        }
     */
    @PostMapping("/memJoin")
    public Result<String> memJoin(@RequestParam("userId") Integer adminId, @RequestBody JSONObject object) {
        log.info("[GroupController.memJoin] --- requesting join a group");
        Integer user = object.getInteger("userId");
        Integer group = object.getInteger("groupId");
        if (groupservice.isAdmin(group, adminId, UserLevel.ADMINISTRATOR.getCode())) {
            log.info("[GroupController.createGroup] --- current user IS an administrator");
            return groupservice.memJoin(group, user);
        } else {
            log.info("[GroupController.createGroup] --- current user IS NOT an administrator");
            return Result.fail("Unauthorized");
        }
    }

    // 修改团队信息 POST url?传userId 请求体传团队信息
    /*
        url: /modifyInfo?userId=
        请求体参数
        {
            "groupId": ,
            "groupName": , (可选)
            "groupProfile": (可选)
        }
     */
    @PostMapping("/modifyInfo")
    public Result<String> modifyInfo(@RequestParam("userId") Integer adminId, @RequestBody JSONObject object) {
        log.info("[GroupController.modifyInfo] --- requesting modify group info");
        Integer groupId = object.getInteger("groupId");
        String groupName = object.getString("groupName");
        String groupProfile = object.getString("groupProfile");
        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setGroupProfile(groupProfile);
        if (groupservice.isAdmin(groupId, adminId, UserLevel.ADMINISTRATOR.getCode())) {
            log.info("[GroupController.modifyInfo] --- current user IS an administrator");
            return groupservice.modifyGroup(group);
        } else {
            log.info("[GroupController.modifyInfo] --- current user IS NOT an administrator");
            return Result.fail("Unauthorized");
        }
    }

    // 团队删除用户 POST url?传userId 请求体传删除的用户id和groupId
    /*
        url: /deleteMem?userId=
        请求体参数
        {
            "userId": ,
            "groupId":
        }
     */
    @PostMapping("/deleteMem")
    public Result<String> deleteMem(@RequestParam("userId") Integer admin, @RequestBody JSONObject object) {
        log.info("[GroupController.deleteMem] --- requesting delete a group member");
        Integer group = object.getInteger("groupId");
        Integer userId = object.getInteger("userId");
        if (groupservice.isAdmin(group, admin, UserLevel.ADMINISTRATOR.getCode())) {
            log.info("[GroupController.deleteMem] --- current user IS an administrator");
            return groupservice.memDelete(group, userId);
        } else {
            log.info("[GroupController.deleteMem] --- current user IS NOT an administrator");
            return Result.fail("Unauthorized");
        }
    }


    // 设置管理员 POST url?传userId 请求体传需要设置的用户id和groupId
    /*
        url: /setAdmin?userId=
        请求体参数
        {
            "userId": ,
            "groupId":
        }
     */
    @PostMapping("/setAdmin")
    public Result<String> setAdmin(@RequestParam("userId") Integer admin, @RequestBody JSONObject object) {
        log.info("[GroupController.setAdmin] --- requesting set an administrator");
        Integer userId = object.getInteger("userId");
        Integer group = object.getInteger("groupId");
        if (groupservice.isAdmin(group, admin, UserLevel.ADMINISTRATOR.getCode())) {
            log.info("[GroupController.setAdmin] --- current user IS an administrator");
            return groupservice.setAdmin(group, userId);
        } else {
            log.info("[GroupController.setAdmin] --- current user IS NOT an administrator");
            return Result.fail("Unauthorized");
        }
    }


    // 查询团队所有成员 GET url?传参
    /*
        url: /getGroupMems?groupId=
     */
    @GetMapping("/getGroupMems")
    public Result<List<User>> getGroupMem(@RequestParam("groupId") Integer groupId) {
        log.info("[GroupController.getGroupMem] --- requesting all members of a group");
        return groupservice.getAllMems(groupId);
    }


    // 查询本用户所有团队 GET url?传参
    /*
        url: /getMyGroups?userId=
     */
    @GetMapping("/getMyGroups")
    public Result<List<Group>> getMyGroup(@RequestParam("userId") Integer userId) {
        log.info("[GroupController.getMyGroup] --- requesting all groups participated");
        return groupservice.getAllGroup(userId);
    }

    // 查询本用户建立的所有团队 GET url?传参
    /*
        url: /getMyLeaderGroup?userId=
     */
    @GetMapping("/getMyLeaderGroup")
    public Result<List<Group>> getMyLeaderGroup(@RequestParam("userId") Integer userId) {
        log.info("[GroupController.getMyLeaderGroup] --- requesting all groups led");
        return groupservice.getFoundedGroup(userId);
    }

    // 删除团队 DELETE url?传用户id 请求体传团队id
    /*
        url: /deleteGroup?userId=
        请求体参数
        {
            "groupId":
        }
     */
    @DeleteMapping("/deleteGroup")
    public Result<String> deleteGroup(@RequestParam("userId") Integer userId, @RequestBody JSONObject object) {
        log.info("[GroupController.deleteGroup] --- requesting deleting a group");
        Integer groupId = object.getInteger("groupId");
        if (groupservice.isAdmin(groupId, userId, UserLevel.FOUNDER.getCode())) {
            log.info("[GroupController.deleteGroup] --- current user IS the founder");
            return groupservice.deleteGroup(groupId);
        } else {
            log.info("[GroupController.deleteGroup] --- current user IS NOT the founder");
            return Result.fail("Unauthorized");
        }
    }
}

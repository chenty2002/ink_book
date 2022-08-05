package com.summerProject.ink_book.Controller;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.GroupService;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupservice;

    public GroupController(GroupService groupservice) {
        this.groupservice = groupservice;
    }


    // 创建团队 POST 请求体传参
    @PostMapping("/createGroup")
    public Result<Group> createGroup(HttpServletRequest request, @RequestBody String groupName) {
        log.info("[GroupController.createGroup] --- requesting creating group");
        User user = (User) request.getSession().getAttribute("curUser");
        return groupservice.createGroup(user, groupName);

    }


    // 用户加入团队 POST url?传userId 请求体传group
    @PostMapping("/memJoin")
    public Result<String> memJoin(HttpServletRequest request, Integer userId, @RequestBody Group group) {
        log.info("[GroupController.memJoin] --- requesting join a group");
        User admin = (User) request.getSession().getAttribute("curUser");
        if (groupservice.isAdmin(group, admin)) {
            log.info("[GroupController.createGroup] --- current user IS an administrator:");
            log.info(admin.toString());
            return groupservice.memJoin(group, admin, userId);
        } else {
            log.info("[GroupController.createGroup] --- current user IS NOT an administrator:");
            log.info(admin.toString());
            return Result.fail("Unauthorized");
        }
    }

    // 修改团队信息 POST 请求体传参
    @PostMapping("/modifyInfo")
    public Result<String> modifyInfo(HttpServletRequest request, @RequestBody Group group) {
        log.info("[GroupController.modifyInfo] --- requesting modify group info");
        User admin = (User) request.getSession().getAttribute("curUser");
        if (groupservice.isAdmin(group, admin)) {
            log.info("[GroupController.modifyInfo] --- current user IS an administrator:");
            log.info(admin.toString());
            return groupservice.modifyGroup(group);
        } else {
            log.info("[GroupController.modifyInfo] --- current user IS NOT an administrator:");
            log.info(admin.toString());
            return Result.fail("Unauthorized");
        }
    }

    // 团队删除用户 POST url?传userId 请求体传group
    @PostMapping("/deleteMem")
    public Result<String> deleteMem(HttpServletRequest request, Integer userId, @RequestBody Group group) {
        log.info("[GroupController.deleteMem] --- requesting delete a group member");
        User admin = (User) request.getSession().getAttribute("curUser");
        if (groupservice.isAdmin(group, admin)) {
            log.info("[GroupController.deleteMem] --- current user IS an administrator:");
            log.info(admin.toString());
            return groupservice.memDelete(group, admin, userId);
        } else {
            log.info("[GroupController.deleteMem] --- current user IS NOT an administrator:");
            log.info(admin.toString());
            return Result.fail("Unauthorized");
        }
    }


    // 设置管理员 POST url?传userId 请求体传group
    @PostMapping("/setAdmin")
    public Result<String> setAdmin(HttpServletRequest request, Integer userId, @RequestBody Group group) {
        log.info("[GroupController.setAdmin] --- requesting set an administrator");
        User admin = (User) request.getSession().getAttribute("curUser");
        if (groupservice.isAdmin(group, admin)) {
            log.info("[GroupController.setAdmin] --- current user IS an administrator:");
            log.info(admin.toString());
            return groupservice.setAdmin(group, admin, userId);
        } else {
            log.info("[GroupController.setAdmin] --- current user IS NOT an administrator:");
            log.info(admin.toString());
            return Result.fail("Unauthorized");
        }
    }


    // 查询团队所有成员 GET url?传参
    @GetMapping("/getGroupMems/{groupId}")
    public Result<List<User>> getGroupMem(@PathVariable("groupId") Integer groupId) {
        log.info("[GroupController.getGroupMem] --- requesting all members of a group");
        return groupservice.getAllMems(groupId);
    }


    // 查询本用户所有团队 GET 无参数
    @GetMapping("/getMyGroups")
    public Result<List<Group>> getMyGroup(HttpServletRequest request) {
        log.info("[GroupController.getMyGroup] --- requesting all groups participated");
        Integer userId = ((User) request.getSession().getAttribute("curUser")).getUserId();
        return groupservice.getAllGroup(userId);
    }

    // 查询本用户建立的所有团队 GET 无参数
    @GetMapping("/getMyLeaderGroup")
    public Result<List<Group>> getMyLeaderGroup(HttpServletRequest request) {
        log.info("[GroupController.getMyLeaderGroup] --- requesting all groups led");
        Integer userId = ((User) request.getSession().getAttribute("curUser")).getUserId();
        return groupservice.getFoundedGroup(userId);
    }
}

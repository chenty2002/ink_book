package com.summerProject.ink_book.Controller;

import com.summerProject.ink_book.Entity.Group;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.GroupService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        User user = (User) request.getSession().getAttribute("curUser");
        return groupservice.createGroup(user, groupName);

    }


    // 用户加入团队 POST url?传userId 请求体传group
    @PostMapping("/memJoin")
    public Result<String> memJoin(HttpServletRequest request, Integer userId, @RequestBody Group group) {
        User admin = (User) request.getSession().getAttribute("curUser");
        if (groupservice.isAdmin(group, admin)) {
            return groupservice.memJoin(group, admin, userId);
        } else {
            return Result.fail("Unauthorized");
        }
    }


    // 团队删除用户 POST url?传userId 请求体传group
    @PostMapping("/deleteMem")
    public Result<String> deleteMem(HttpServletRequest request, Integer userId, @RequestBody Group group) {
        User admin = (User) request.getSession().getAttribute("curUser");
        if (groupservice.isAdmin(group, admin)) {
            return groupservice.memDelete(group, admin, userId);
        } else {
            return Result.fail("Unauthorized");
        }
    }


    // 设置管理员 POST url?传userId 请求体传group
    @PostMapping("/setAdmin")
    public Result<String> setAdmin(HttpServletRequest request, Integer userId, @RequestBody Group group) {
        User admin = (User) request.getSession().getAttribute("curUser");
        if (groupservice.isAdmin(group, admin)) {
            return groupservice.setAdmin(group, admin, userId);
        } else {
            return Result.fail("Unauthorized");
        }
    }


    // 查询团队所有成员 GET url?传参
    @GetMapping("/getGroupMems/{groupId}")
    public Result<List<User>> getGroupMem(@PathVariable("groupId") Integer groupId) {
        return groupservice.getAllMems(groupId);
    }


    // 查询本用户所有团队 GET 无参数
    @GetMapping("/getMyGroups")
    public Result<List<Group>> getMyGroup(HttpServletRequest request) {
        Integer userId = ((User) request.getSession().getAttribute("curUser")).getUserId();
        return groupservice.getAllGroup(userId);
    }

    // 查询本用户建立的所有团队 GET 无参数
    @GetMapping("/getMyLeaderGroup")
    public Result<List<Group>> getMyLeaderGroup(HttpServletRequest request) {
        Integer userId = ((User) request.getSession().getAttribute("curUser")).getUserId();
        return groupservice.getFoundedGroup(userId);
    }
}

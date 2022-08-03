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

    @PostMapping("/createGroup")
    public Result<Group> createGroup(HttpServletRequest request, @RequestBody String groupName) {
        User user = (User) request.getSession().getAttribute("user");
        return groupservice.createGroup(user, groupName);

    }

    @PostMapping("/memJoin")
    public Result<String> memJoin(HttpServletRequest request, @RequestBody Integer user, Group group) {
        User nowuser = (User) request.getSession().getAttribute("user");

        if (groupservice.isAdmin(group, nowuser).getCode() == 1) {
            return groupservice.memJoin(group, nowuser, user);
        } else {
            return Result.fail("Unauthorized");
        }
    }

    @PostMapping("/setAdmin")
    public Result<String> setAdmin(HttpServletRequest request, @RequestBody Integer user, Group group, User admin) {

        User nowuser = (User) request.getSession().getAttribute("user");
        Result<String> isAdmin = groupservice.isAdmin(group, nowuser);
        if (isAdmin.getCode() == 1) {
            return groupservice.setAdmin(group, nowuser, user);
        } else {
            return Result.fail("Unauthorized");
        }
    }

    @GetMapping
    public Result<List<User>> getGroupMem(HttpServletRequest request, @RequestBody Group group) {
        return groupservice.getAllMems(group);
    }

    @GetMapping
    public Result<List<Group>> getMyGroup(HttpServletRequest request, @RequestBody User user) {
        return groupservice.getAllGroup(user);
    }

    @GetMapping
    public Result<List<Group>> getMyLeaderGroup(HttpServletRequest request, @RequestBody User user) {
        return groupservice.getFoundedGroup(user);
    }
}

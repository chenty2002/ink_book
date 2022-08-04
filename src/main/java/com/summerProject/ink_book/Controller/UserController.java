package com.summerProject.ink_book.Controller;

import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.UserService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 登录 POST 请求体传参
    @PostMapping("/login")
    public Result<User> login(HttpServletRequest request, @RequestBody User user) {
        Result<User> result = userService.login(user);
        if (result.getCode() == 1)
            request.getSession().setAttribute("curUser", result.getData());
        return result;
    }


    // 注册 POST 请求体传参
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        return userService.register(user);
    }


    // 下线 GET 无参数
    @GetMapping("/logout")
    public Result<User> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("curUser");
        return Result.success("Logout Success", null);
    }


    // 本用户信息 GET 无参数
    @GetMapping("/info")
    public Result<User> getInfo(HttpServletRequest request) {
        Integer id = ((User) request.getSession().getAttribute("curUser")).getUserId();
        return userService.getInfo(id);
    }
}

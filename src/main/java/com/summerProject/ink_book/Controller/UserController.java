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

    @PostMapping("/login")
    public Result<User> login(HttpServletRequest request, @RequestBody User user) {
        Result<User> result = userService.login(user);
        if (result.getCode() == 1)
            request.getSession().setAttribute("userId", result.getData().getUserId());
        return result;
    }

    @PostMapping("/register")
    public Result<User> register(HttpServletRequest request, @RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/logout")
    public Result<User> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userId");
        return Result.success("Logout Success", null);
    }

    @GetMapping
    public Result<User> getInfo(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("userId");
        return userService.getInfo(id);
    }
}

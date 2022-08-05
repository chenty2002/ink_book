package com.summerProject.ink_book.Controller;

import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.UserService;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<User> login(HttpServletRequest request, @RequestBody User user) {
        log.info("[UserController.login] --- requesting login");
        Result<User> result = userService.login(user);
        if (result.getCode() == 1) {
            request.getSession().setAttribute("curUser", result.getData());
            log.info("[UserController.login] --- login SUCCESS with User:");
            log.info(result.getData().toString());
        } else {
            log.info("[UserController.login] --- login FAILURE with User:");
            log.info(user.toString());
        }
        return result;
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        log.info("[UserController.register] --- requesting register");
        return userService.register(user);
    }

    @PostMapping("/logout")
    public Result<User> logout(HttpServletRequest request) {
        log.info("[UserController.logout] --- requesting logout");
        request.getSession().removeAttribute("curUser");
        return Result.success("Logout Success", null);
    }

    @GetMapping("/info")
    public Result<User> getInfo(HttpServletRequest request) {
        log.info("[UserController.getInfo] --- requesting user info");
        Integer id = ((User) request.getSession().getAttribute("curUser")).getUserId();
        return userService.getInfo(id);
    }
}

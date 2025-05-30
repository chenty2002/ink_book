package com.summerProject.ink_book.Controller;

import com.alibaba.fastjson.JSONObject;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.MailService;
import com.summerProject.ink_book.Service.UserService;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final MailService mailService;

    public UserController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    //发送邮箱验证码 POST 请求体传参
    /*
        url: /sendEmail
        请求体参数
        {
            "email":
        }
     */
    @PostMapping("/sendEmail")
    public Result<String> sendEmail(@RequestBody JSONObject object) {
        log.info("[MailController.sendEmail] --- validating email and sending code");
        return mailService.sendEmail(object.getString("email"));
    }

    // 登录 POST 请求体传参
    /*
        url: /login
        请求体参数
        {
            "userEmail": ,
            "password":
        }
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody JSONObject object) {
        log.info("[UserController.login] --- requesting login");
        User user = new User();
        user.setPassword(object.getString("password"));
        user.setUserEmail(object.getString("userEmail"));
        Result<User> result = userService.login(user);
        if (result.getCode() == 1) {
            log.info("[UserController.login] --- login SUCCESS with User:");
            log.info(user.toString());
        } else {
            log.info("[UserController.login] --- login FAILURE with User:");
            log.info(user.toString());
        }
        return result;
    }


    // 注册 POST 请求体传参
    /*
        url: /register
        请求体参数
        {
            "userName": ,
            "password": ,
            "userRealName": ,
            "userProfile": ,
            "userEmail":
        }
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody JSONObject object) {
        log.info("[UserController.register] --- requesting register");
        User user = new User();
        user.setUserName(object.getString("userName"));
        user.setPassword(object.getString("password"));
        user.setUserEmail(object.getString("userEmail"));
        user.setUserRealName(object.getString("userRealName"));
        user.setUserProfile(object.getString("userProfile"));
        return userService.register(user);
    }


    // 用户信息 GET url?传参
    /*
        url: /info?userId=
     */
    @GetMapping("/info")
    public Result<User> getInfo(@RequestParam("userId") Integer id) {
        log.info("[UserController.getInfo] --- requesting user info");
        return userService.getInfo(id);
    }
}

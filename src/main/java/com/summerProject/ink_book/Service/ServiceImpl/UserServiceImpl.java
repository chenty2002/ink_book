package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Mapper.UserMapper;
import com.summerProject.ink_book.Service.UserService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result<User> login(User user) {
        String pwd = user.getPassword();
        String md5pwd = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        user.setUserEmail(user.getUserName());
        user.setPassword(md5pwd);
        User newUser = userMapper.selectUserByPwd(user);
        if (newUser != null) {
            newUser.setPassword(pwd);
            return Result.success("Login Success", newUser);
        } else return Result.fail("Login Failure");
    }

    @Override
    public Result<User> register(User user) {
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        User newUser = User.copyUser(user);
        user.setPassword(pwd);
        if (userMapper.insertUser(user) > 0) {
            newUser.setUserId(user.getUserId());
            return Result.success("Register success", newUser);
        } else return Result.fail("Register Failure");
    }

    @Override
    public Result<User> getInfo(Integer id) {
        return Result.success("User Info", userMapper.getUser(id));
    }
}

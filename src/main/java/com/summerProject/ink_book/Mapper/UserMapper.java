package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer insertUser(User user);

    User selectUserById(Integer id);

    User selectUserByName(String name);

    User selectUserByEmail(String email);

    List<User> selectUserByRealName(String name); // like %s%

    User selectUserByPwd(User user); // authenticate user

    User getUser(Integer id);

}

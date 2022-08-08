package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface UserService {
    Result<User> login(User user);

    Result<User> register(User user);

    Result<User> getInfo(Integer id);

    Result<List<User>> searchUser(String word);
}

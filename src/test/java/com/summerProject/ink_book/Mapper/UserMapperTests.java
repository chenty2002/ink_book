package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;


    @Test
    void selectTest() {
        System.out.println(userMapper.selectUserById(1));
    }
}

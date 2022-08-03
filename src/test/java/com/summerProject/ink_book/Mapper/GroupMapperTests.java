package com.summerProject.ink_book.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupMapperTests {
    @Autowired
    private GroupMapper groupMapper;

    @Test
    public void selectTest() {
//        System.out.println(groupMapper.selectGroupById(1));
//        System.out.println("-----");
//        User user = new User();
//        user.setUserId(2);
//        System.out.println(groupMapper.selectGroupByFounder(user));
//        System.out.println("-----");
//        System.out.println(groupMapper.selectGroupByMem(user));
        System.out.println(groupMapper.selectGroupByMemPage(2, 1, 1));
    }
}

package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupUserMapperTests {
    @Autowired
    private GroupUserMapper groupUserMapper;

    @Test
    void getLevelTest() {
        Group group = new Group();
        group.setGroupId(1);
        System.out.println(groupUserMapper.getLevel(group, 1));
        System.out.println(groupUserMapper.getLevel(group, 4));
    }

    @Test
    void selectTest() {
        Group group = new Group();
        group.setGroupId(1);
        System.out.println(groupUserMapper.selectMemByGroup(group));
    }
}

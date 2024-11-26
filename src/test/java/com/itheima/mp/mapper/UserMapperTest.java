package com.itheima.mp.mapper;

import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.po.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Song Laixiong
 * @Create: 2024-11-26
 * @Desciption:
 */

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo(UserInfo.of(18, "I am a student", "FEMALE"));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user =" + user);
    }

    @Test
    void testSelectByIds() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    @Test
    void testDelete() {
        int i = userMapper.deleteById(5L);
        System.out.println("i = " + i);
    }

    @Test
    void testQuery() {
        User user = userMapper.queryById(1L);
        System.out.println("user = " + user);
    }
}

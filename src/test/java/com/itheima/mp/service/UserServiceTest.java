package com.itheima.mp.service;

import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.po.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Song Laixiong
 * @Create: 2024-11-26
 * @Description:
 */

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testService() {
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    void testSaveOneByOne() {
        long b = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
            userService.save(buildUser(i));
        }

        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }

    private User buildUser(int i) {
        User user = new User();
        user.setUsername("user" + i);
        user.setPassword("123");
        user.setPhone("" + (18688190000L + i));
        user.setBalance(2000);
        user.setInfo(UserInfo.of(18, "intro" + i, "男"));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }

    @Test
    void testSaveBatch() {
        ArrayList<User> list = new ArrayList<>(1000);
        long b = System.currentTimeMillis();

        for (int i = 1; i <= 1000; i++) {
            list.add(buildUser(i));
            if (i % 500 == 0) {
                userService.saveBatch(list);
                list.clear();
            }
        }

        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }
}

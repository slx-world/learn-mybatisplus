package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    @Test
    void testQueryWrapper() {
        // 1. 构建查询条件 where name like '%o%' and balance >= 1000
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .select("id", "Username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);

        // 2. 查询数据
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateByQueryWrapper() {
        // 1. 构建查询条件 where name = "Jack"
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", "Jack");

        // 2. 更新数据，user 中非 null 的字段都会作为 set 语句
        User user = new User();
        user.setBalance(2000);
        int i = userMapper.update(user, wrapper);
        System.out.println("i = " + i);
    }

    @Test
    void testUpdateWrapper() {
        List<Long> ids = Arrays.asList(1L, 2L, 4L);

        // 1. 生成 SQL
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200") // 注意，这里 balance - 200 是一个 SQL 片段
                .in("id", ids);// 注意，这里 in 中的参数是 Long 类型

        // 2. 更新，注意第一个参数可以给 null，也就是不填更新字段和数据
        // 而是基于 UpdateWrapper 中的 setSql 来更新
        int i = userMapper.update(null, wrapper);
        System.out.println("i = " + i);
    }

    @Test
    void testLambdaQueryWrapper() {
        // 1. 构建条件 where username like '%o%' and balance >= 1000
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>()
                .lambda()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);

        // 2. 查询数据
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testCustomWrapper() {
        // 1. 准备自定义查询条件
        List<Long> ids = Arrays.asList(1L, 2L, 4L);

        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .in("id", ids);

        // 2. 调用 mapper 的自定义方法，直接传递 wrapper
        userMapper.deductBalanceByIds(500, wrapper);
    }

    @Test
    void testQueryUserByIdAndAddr() {
        List<Long> ids = Arrays.asList(1L, 2L, 4L);
        String city = "北京";
        List<User> users = userMapper.queryUserByIdAndAddr(ids, city);
        users.forEach(System.out::println);
        // List<Address> addresses = userMapper.queryUserByIdAndAddr(ids, city);
        // addresses.forEach(System.out::println);
    }

    @Test
    void testCustomJoinWrapper() {
        // 1. 准备自定义查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .in("u.id", Arrays.asList(1L, 2L, 4L))
                .eq("a.city", "北京");

        // 2. 调用 mapper 的自定义方法，直接传递 wrapper
        List<User> users = userMapper.queryUserByWrapper(wrapper);
        users.forEach(System.out::println);
    }
}

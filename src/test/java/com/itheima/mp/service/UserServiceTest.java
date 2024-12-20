package com.itheima.mp.service;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

@SpringBootTest(args = "--mpw.key=069b2c60ca43b985")
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

    @Test
    void testLambdaQuery() {
        // 1. 查询 1 个
        User rose = userService.lambdaQuery()
                .eq(User::getUsername, "Rose")
                .one(); // .one() 查询 1 个

        System.out.println(rose);

        // 2. 查询多个
        List<User> list = userService.lambdaQuery()
                .like(User::getUsername, "o")
                .list();// .list() 查询集合

        list.forEach(System.out::println);

        // 3. count 统计
        Long count = userService.lambdaQuery()
                .like(User::getUsername, "o")
                .count(); // .count() 统计

        System.out.println("count = " + count);
    }

    @Test
    void testQueryUser() {
        List<User> users = queryUser("o", 1, null, null);
        users.forEach(System.out::println);
    }

    private List<User> queryUser(String username, Integer status, Integer minBalance, Integer maxBalance) {
        return userService.lambdaQuery()
                .like(username != null, User::getUsername, username)
                .eq(status != null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .list();
    }

    @Test
    void testLambdaUpdate() {
        boolean i = userService.lambdaUpdate()
                .set(User::getBalance, 800) // set balance = 800
                .eq(User::getUsername, "Jack") // where username = 'Jack'
                .update();// 执行 Update

        System.out.println("i = " + i);
    }

    @Test
    void testUpdateBalance() {
        updateBalance(0L, 1L, null);
    }

    private void updateBalance(Long balance, Long id, String username) {
        userService.lambdaUpdate()
                .set(User::getBalance, balance)
                .set(balance == 0, User::getStatus, 2)
                .eq(id != null, User::getId, id)
                .eq(username != null, User::getUsername, username)
                .update();
    }

    @Test
    void testPageQuery() {
        // 1. 分页查询，new Page() 的两个参数分别是：页码，每页记录数
        Page<User> p = userService.page(new Page<User>(2, 2));
        // 2. 总条数
        System.out.println("总条数：" + p.getTotal());
        // 3. 总页数
        System.out.println("总页数：" + p.getPages());
        // 4. 当前页数据
        List<User> records = p.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    void testPageQuery2() {
        int pageNo = 1, pageSize = 3;
        // 分页参数
        Page<User> page = Page.of(pageNo, pageSize);
        // 排序参数，通过 OrderItem 来指定
        page.addOrder(new OrderItem("balance", true));
        Page<User> page1 = userService.page(page);
        page1.getRecords().forEach(System.out::println);
    }
}

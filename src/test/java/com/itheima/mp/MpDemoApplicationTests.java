package com.itheima.mp;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;

/**
 * @Author: Song Laixiong
 * @Create: 2024-11-26
 * @Description:
 */

public class MpDemoApplicationTests {

    @Test
    void contextLoads() {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println("randomKey = " + randomKey);

        // 利用密钥对用户名加密
        String username = AES.encrypt("slx", randomKey);
        System.out.println("username = " + username);

        // 利用密钥对密码加密
        String password = AES.encrypt("slx_9920", randomKey);
        System.out.println("password = " + password);
    }
}

package com.itheima.mp.service;

import com.itheima.mp.domain.po.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Song Laixiong
 * @Create: 2024-11-26
 * @Description:
 */

@SpringBootTest
public class AddressUserviceTest {

    @Autowired
    private AddressService addressService;

    @Test
    void testDeletedByLogic() {
        boolean b = addressService.removeById(60L);
        System.out.println(b);
    }

    @Test
    void testQuery() {
        List<Address> list = addressService.list();
        list.forEach(System.out::println);
    }
}

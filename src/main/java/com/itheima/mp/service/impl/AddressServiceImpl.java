package com.itheima.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.Address;
import com.itheima.mp.service.AddressService;
import com.itheima.mp.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
* @author slx99
* @description 针对表【address】的数据库操作Service实现
* @createDate 2024-11-26 19:54:17
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

}





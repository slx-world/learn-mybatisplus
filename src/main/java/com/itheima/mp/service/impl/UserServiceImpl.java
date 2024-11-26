package com.itheima.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.dto.PageDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.PageQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Song Laixiong
 * @Create: 2024-11-26
 * @Description:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public PageDTO<UserVO> queryUserrByPage(PageQuery query) {
        // 1. 构建条件
        // 1.1 分页条件
        Page<User> page = Page.of(query.getPageNo(), query.getPageSize());

        // 1.2 排序条件
        if (query.getSortBy() != null) {
            page.addOrder(new OrderItem(query.getSortBy(), query.getIsAsc()));
        } else {
            page.addOrder(new OrderItem("update_time", false));
        }

        // 2. 查询数据
        page(page);
        // 3. 数据非空校验
        List<User> records = page.getRecords();

        if (records == null || records.size() <= 0) {
            // 无数据，返回空结果
            return new PageDTO<>(page.getTotal(), page.getPages(), Collections.emptyList());
        }

        // 4. 有数据，转换为VO
        List<UserVO> list = BeanUtil.copyToList(records, UserVO.class);
        // 5. 封装数据返回
        return new PageDTO<>(page.getTotal(), page.getPages(), list);
    }
}

package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.dto.PageDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.PageQuery;
import com.itheima.mp.domain.vo.UserVO;

/**
 * @Author: Song Laixiong
 * @Create: 2024-11-26
 * @Description:
 */

public interface UserService extends IService<User> {

    PageDTO<UserVO> queryUserrByPage(PageQuery query);
}

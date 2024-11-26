package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    User queryById(Long id);

    @Select("update user set balance = balance + #{money} ${ew.customSqlSegment}")
    void deductBalanceByIds(@Param("money") int money, @Param("ew")QueryWrapper<User> wrapper);

    List<User> queryUserByIdAndAddr(@Param("ids") List<Long> ids, @Param("city") String city);

    @Select("select u.* from user u inner join address a on u.id = a.user_id ${ew.customSqlSegment}")
    List<User> queryUserByWrapper(@Param("ew") QueryWrapper<User> wrapper);
}

package com.wbb.generator.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbb.generator.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author william
 * @Description
 * @createTime 2022-12-31 15:28
 */
@Mapper
public interface IUserMapper extends BaseMapper<User> {
}

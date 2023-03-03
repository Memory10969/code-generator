package com.wbb.generator.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wbb.generator.example.entity.User;

import java.util.List;

/**
 * @author william
 * @Description
 * @createTime 2022-12-31 15:33
 */
public interface IUserService {

     List<User> selectAll();

     User selectById(String id);

     List<User> select(User user);

     List<User> selectPage(IPage<User> page, User user);

     int insert(User user);

     int update(User user);

     int deleteById(String id);

}

package com.wbb.generator.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbb.generator.example.dao.IUserMapper;
import com.wbb.generator.example.entity.User;
import com.wbb.generator.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author william
 * @Description
 * @createTime 2022-12-31 15:36
 */
@Service
@Primary
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;
    @Override
    public List<User> selectAll() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.selectList(wrapper);
    }

    @Override
    public User selectById(String userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<User> select(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.hasText(user.getUserId())){
            wrapper.like("user_id",user.getUserId());
        }
        if(StringUtils.hasText(user.getUserName())){
            wrapper.like("user_name",user.getUserName());
        }
        return userMapper.selectList(wrapper);
    }

    @Override
    public List<User> selectPage(IPage<User> page,User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.hasText(user.getUserId())){
            wrapper.like("user_id",user.getUserId());
        }
        if(StringUtils.hasText(user.getUserName())){
            wrapper.like("user_name",user.getUserName());
        }
        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        List<User> userList = userIPage.getRecords();
        return userList;
    }

    @Override
    public int insert(User user) {
        if(!StringUtils.hasText(user.getUserId())){
            UUID uuid = UUID.randomUUID();
            user.setUserId(uuid.toString());
        }
        int i = userMapper.insert(user);
        return i;
    }

    @Override
    public int update(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.hasText(user.getUserId())){
            wrapper.like("user_id",user.getUserId());
        }
        if(StringUtils.hasText(user.getUserName())){
            wrapper.like("user_name",user.getUserName());
        }
        int i = userMapper.update(user,wrapper);
        return i;
    }

    @Override
    public int deleteById(String userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        int i = userMapper.deleteById(userId);
        return i;
    }
}

package com.wbb.generator.tteacherclass.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbb.generator.tteacherclass.entity.TTeacherClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ITTeacherClassMapper extends BaseMapper<TTeacherClass> {
    @Select("select * from user")
    List<TTeacherClass> findAll();
}

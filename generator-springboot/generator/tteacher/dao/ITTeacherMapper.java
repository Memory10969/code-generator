package com.wbb.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbb.generator.tteacher.entity.TTeacher;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ITTeacherMapper extends BaseMapper<TTeacher> {
}

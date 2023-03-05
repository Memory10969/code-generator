package com.wbb.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbb.generator.tclass.entity.TClass;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ITClassMapper extends BaseMapper<TClass> {
}

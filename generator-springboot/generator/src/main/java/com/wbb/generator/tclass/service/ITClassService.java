package com.wbb.generator.tclass.service;

import com.wbb.generator.tclass.entity.TClass;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface ITClassService {

     List<TClass> selectAll();

     TClass selectById(String id);

     List<TClass> select(TClass tClass);

     List<TClass> selectPage(IPage<TClass> page, TClass tClass);

     int insert(TClass tClass);

     int update(TClass tClass);

     int updateById(TClass tClass);

     int deleteById(String id);

     int deleteByIds(String[] ids);

     long count(TClass tClass);


}

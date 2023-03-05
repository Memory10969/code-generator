package com.wbb.generator.service;

import com.wbb.generator.tteacherclass.entity.TTeacherClass;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface ITTeacherClassService {

     List<TTeacherClass> selectAll();

     TTeacherClass selectById(String id);

     List<TTeacherClass> select(TTeacherClass tTeacherClass);

     List<TTeacherClass> selectPage(IPage<TTeacherClass> page, TTeacherClass tTeacherClass);

     int insert(TTeacherClass tTeacherClass);

     int update(TTeacherClass tTeacherClass);

     int updateById(TTeacherClass tTeacherClass);

     int deleteById(String id);

     int deleteByIds(String[] ids);

     long count(TTeacherClass tTeacherClass);


}

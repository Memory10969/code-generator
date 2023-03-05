package com.wbb.generator.service;

import com.wbb.generator.tteacher.entity.TTeacher;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface ITTeacherService {

     List<TTeacher> selectAll();

     TTeacher selectById(String id);

     List<TTeacher> select(TTeacher tTeacher);

     List<TTeacher> selectPage(IPage<TTeacher> page, TTeacher tTeacher);

     int insert(TTeacher tTeacher);

     int update(TTeacher tTeacher);

     int updateById(TTeacher tTeacher);

     int deleteById(String id);

     int deleteByIds(String[] ids);

     long count(TTeacher tTeacher);


}

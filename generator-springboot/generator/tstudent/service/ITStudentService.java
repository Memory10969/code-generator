package com.wbb.generator.service;

import com.wbb.generator.tstudent.entity.TStudent;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface ITStudentService {

     List<TStudent> selectAll();

     TStudent selectById(String id);

     List<TStudent> select(TStudent tStudent);

     List<TStudent> selectPage(IPage<TStudent> page, TStudent tStudent);

     int insert(TStudent tStudent);

     int update(TStudent tStudent);

     int updateById(TStudent tStudent);

     int deleteById(String id);

     int deleteByIds(String[] ids);

     long count(TStudent tStudent);


}

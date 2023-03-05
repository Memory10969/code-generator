package com.wbb.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbb.generator.tstudent.dao.ITStudentMapper;
import com.wbb.generator.tstudent.entity.TStudent;
import com.wbb.generator.tstudent.service.ITStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class TStudentServiceImpl implements ITStudentService {

    @Autowired
    private ITStudentMapper tStudentMapper;
    @Override
    public List<TStudent> selectAll() {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        return tStudentMapper.selectList(wrapper);
    }

    @Override
    public TStudent selectById(String id) {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return tStudentMapper.selectOne(wrapper);
    }

    @Override
    public List<TStudent> select(TStudent tStudent) {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        if(tStudent != null && StringUtils.hasText(tStudent.getId())){
            wrapper.like("id",tStudent.getId());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getName())){
            wrapper.like("name",tStudent.getName());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getGender())){
            wrapper.like("gender",tStudent.getGender());
        }
        if("tStudent" != null && tStudent.getAge() != null){
            wrapper.eq("age",tStudent.getAge());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getPhoneNumber())){
            wrapper.like("phone_number",tStudent.getPhoneNumber());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getClassId())){
            wrapper.like("class_id",tStudent.getClassId());
        }

        return tStudentMapper.selectList(wrapper);
    }

    @Override
    public List<TStudent> selectPage(IPage<TStudent> page,TStudent tStudent) {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        if(tStudent != null && StringUtils.hasText(tStudent.getId())){
            wrapper.like("id",tStudent.getId());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getName())){
            wrapper.like("name",tStudent.getName());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getGender())){
            wrapper.like("gender",tStudent.getGender());
        }
        if("tStudent" != null && tStudent.getAge() != null){
            wrapper.eq("age",tStudent.getAge());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getPhoneNumber())){
            wrapper.like("phone_number",tStudent.getPhoneNumber());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getClassId())){
            wrapper.like("class_id",tStudent.getClassId());
        }

        IPage<TStudent> tStudentIPage = tStudentMapper.selectPage(page, wrapper);
        List<TStudent> tStudentList = tStudentIPage.getRecords();
        return tStudentList;
    }

    @Override
    public int insert(TStudent tStudent) {
        if(!StringUtils.hasText(tStudent.getId())){
              UUID uuid = UUID.randomUUID();
            tStudent.setId(uuid.toString());
        }

        int i = tStudentMapper.insert(tStudent);
        return i;
    }

    @Override
    public int update(TStudent tStudent) {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        if(tStudent != null && StringUtils.hasText(tStudent.getId())){
            wrapper.like("id",tStudent.getId());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getName())){
            wrapper.like("name",tStudent.getName());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getGender())){
            wrapper.like("gender",tStudent.getGender());
        }
        if("tStudent" != null && tStudent.getAge() != null){
            wrapper.eq("age",tStudent.getAge());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getPhoneNumber())){
            wrapper.like("phone_number",tStudent.getPhoneNumber());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getClassId())){
            wrapper.like("class_id",tStudent.getClassId());
        }

        int i = tStudentMapper.update(tStudent,wrapper);
        return i;
    }

    @Override
    public int updateById(TStudent tStudent) {
        int i = tStudentMapper.updateById(tStudent);
        return i;
    }

    @Override
    public int deleteById(String id) {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int i = tStudentMapper.deleteById(id);
        return i;
    }

        @Override
        @Transactional
        public int deleteByIds(String[] ids) {
            QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            int delete = tStudentMapper.delete(wrapper);
            return delete;
        }

    @Override
    public long count(TStudent tStudent){
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        if(tStudent != null && StringUtils.hasText(tStudent.getId())){
            wrapper.like("id",tStudent.getId());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getName())){
            wrapper.like("name",tStudent.getName());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getGender())){
            wrapper.like("gender",tStudent.getGender());
        }
        if("tStudent" != null && tStudent.getAge() != null){
            wrapper.eq("age",tStudent.getAge());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getPhoneNumber())){
            wrapper.like("phone_number",tStudent.getPhoneNumber());
        }
        if(tStudent != null && StringUtils.hasText(tStudent.getClassId())){
            wrapper.like("class_id",tStudent.getClassId());
        }

        Long i = tStudentMapper.selectCount(wrapper);
        return i;
    }
}

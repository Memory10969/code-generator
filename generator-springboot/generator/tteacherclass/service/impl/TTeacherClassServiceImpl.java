package com.wbb.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbb.generator.tteacherclass.dao.ITTeacherClassMapper;
import com.wbb.generator.tteacherclass.entity.TTeacherClass;
import com.wbb.generator.tteacherclass.service.ITTeacherClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class TTeacherClassServiceImpl implements ITTeacherClassService {

    @Autowired
    private ITTeacherClassMapper tTeacherClassMapper;
    @Override
    public List<TTeacherClass> selectAll() {
        QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
        return tTeacherClassMapper.selectList(wrapper);
    }

    @Override
    public TTeacherClass selectById(String id) {
        QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return tTeacherClassMapper.selectOne(wrapper);
    }

    @Override
    public List<TTeacherClass> select(TTeacherClass tTeacherClass) {
        QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getId())){
            wrapper.like("id",tTeacherClass.getId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getTeacherId())){
            wrapper.like("teacher_id",tTeacherClass.getTeacherId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getClassId())){
            wrapper.like("class_id",tTeacherClass.getClassId());
        }

        return tTeacherClassMapper.selectList(wrapper);
    }

    @Override
    public List<TTeacherClass> selectPage(IPage<TTeacherClass> page,TTeacherClass tTeacherClass) {
        QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getId())){
            wrapper.like("id",tTeacherClass.getId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getTeacherId())){
            wrapper.like("teacher_id",tTeacherClass.getTeacherId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getClassId())){
            wrapper.like("class_id",tTeacherClass.getClassId());
        }

        IPage<TTeacherClass> tTeacherClassIPage = tTeacherClassMapper.selectPage(page, wrapper);
        List<TTeacherClass> tTeacherClassList = tTeacherClassIPage.getRecords();
        return tTeacherClassList;
    }

    @Override
    public int insert(TTeacherClass tTeacherClass) {
        if(!StringUtils.hasText(tTeacherClass.getId())){
              UUID uuid = UUID.randomUUID();
            tTeacherClass.setId(uuid.toString());
        }

        int i = tTeacherClassMapper.insert(tTeacherClass);
        return i;
    }

    @Override
    public int update(TTeacherClass tTeacherClass) {
        QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getId())){
            wrapper.like("id",tTeacherClass.getId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getTeacherId())){
            wrapper.like("teacher_id",tTeacherClass.getTeacherId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getClassId())){
            wrapper.like("class_id",tTeacherClass.getClassId());
        }

        int i = tTeacherClassMapper.update(tTeacherClass,wrapper);
        return i;
    }

    @Override
    public int updateById(TTeacherClass tTeacherClass) {
        int i = tTeacherClassMapper.updateById(tTeacherClass);
        return i;
    }

    @Override
    public int deleteById(String id) {
        QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int i = tTeacherClassMapper.deleteById(id);
        return i;
    }

        @Override
        @Transactional
        public int deleteByIds(String[] ids) {
            QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            int delete = tTeacherClassMapper.delete(wrapper);
            return delete;
        }

    @Override
    public long count(TTeacherClass tTeacherClass){
        QueryWrapper<TTeacherClass> wrapper = new QueryWrapper<>();
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getId())){
            wrapper.like("id",tTeacherClass.getId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getTeacherId())){
            wrapper.like("teacher_id",tTeacherClass.getTeacherId());
        }
        if(tTeacherClass != null && StringUtils.hasText(tTeacherClass.getClassId())){
            wrapper.like("class_id",tTeacherClass.getClassId());
        }

        Long i = tTeacherClassMapper.selectCount(wrapper);
        return i;
    }
}

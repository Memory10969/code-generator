package com.wbb.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbb.generator.tteacher.dao.ITTeacherMapper;
import com.wbb.generator.tteacher.entity.TTeacher;
import com.wbb.generator.tteacher.service.ITTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class TTeacherServiceImpl implements ITTeacherService {

    @Autowired
    private ITTeacherMapper tTeacherMapper;
    @Override
    public List<TTeacher> selectAll() {
        QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
        return tTeacherMapper.selectList(wrapper);
    }

    @Override
    public TTeacher selectById(String id) {
        QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return tTeacherMapper.selectOne(wrapper);
    }

    @Override
    public List<TTeacher> select(TTeacher tTeacher) {
        QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
        if(tTeacher != null && StringUtils.hasText(tTeacher.getId())){
            wrapper.like("id",tTeacher.getId());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getTeacherName())){
            wrapper.like("teacher_name",tTeacher.getTeacherName());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getMajor())){
            wrapper.like("major",tTeacher.getMajor());
        }

        return tTeacherMapper.selectList(wrapper);
    }

    @Override
    public List<TTeacher> selectPage(IPage<TTeacher> page,TTeacher tTeacher) {
        QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
        if(tTeacher != null && StringUtils.hasText(tTeacher.getId())){
            wrapper.like("id",tTeacher.getId());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getTeacherName())){
            wrapper.like("teacher_name",tTeacher.getTeacherName());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getMajor())){
            wrapper.like("major",tTeacher.getMajor());
        }

        IPage<TTeacher> tTeacherIPage = tTeacherMapper.selectPage(page, wrapper);
        List<TTeacher> tTeacherList = tTeacherIPage.getRecords();
        return tTeacherList;
    }

    @Override
    public int insert(TTeacher tTeacher) {
        if(!StringUtils.hasText(tTeacher.getId())){
              UUID uuid = UUID.randomUUID();
            tTeacher.setId(uuid.toString());
        }

        int i = tTeacherMapper.insert(tTeacher);
        return i;
    }

    @Override
    public int update(TTeacher tTeacher) {
        QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
        if(tTeacher != null && StringUtils.hasText(tTeacher.getId())){
            wrapper.like("id",tTeacher.getId());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getTeacherName())){
            wrapper.like("teacher_name",tTeacher.getTeacherName());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getMajor())){
            wrapper.like("major",tTeacher.getMajor());
        }

        int i = tTeacherMapper.update(tTeacher,wrapper);
        return i;
    }

    @Override
    public int updateById(TTeacher tTeacher) {
        int i = tTeacherMapper.updateById(tTeacher);
        return i;
    }

    @Override
    public int deleteById(String id) {
        QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int i = tTeacherMapper.deleteById(id);
        return i;
    }

        @Override
        @Transactional
        public int deleteByIds(String[] ids) {
            QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            int delete = tTeacherMapper.delete(wrapper);
            return delete;
        }

    @Override
    public long count(TTeacher tTeacher){
        QueryWrapper<TTeacher> wrapper = new QueryWrapper<>();
        if(tTeacher != null && StringUtils.hasText(tTeacher.getId())){
            wrapper.like("id",tTeacher.getId());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getTeacherName())){
            wrapper.like("teacher_name",tTeacher.getTeacherName());
        }
        if(tTeacher != null && StringUtils.hasText(tTeacher.getMajor())){
            wrapper.like("major",tTeacher.getMajor());
        }

        Long i = tTeacherMapper.selectCount(wrapper);
        return i;
    }
}

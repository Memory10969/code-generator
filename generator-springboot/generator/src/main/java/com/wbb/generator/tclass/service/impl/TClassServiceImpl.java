package com.wbb.generator.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbb.generator.tclass.dao.ITClassMapper;
import com.wbb.generator.tclass.entity.TClass;
import com.wbb.generator.tclass.service.ITClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class TClassServiceImpl implements ITClassService {

    @Autowired
    private ITClassMapper tClassMapper;
    @Override
    public List<TClass> selectAll() {
        QueryWrapper<TClass> wrapper = new QueryWrapper<>();
        return tClassMapper.selectList(wrapper);
    }

    @Override
    public TClass selectById(String id) {
        QueryWrapper<TClass> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return tClassMapper.selectOne(wrapper);
    }

    @Override
    public List<TClass> select(TClass tClass) {
        QueryWrapper<TClass> wrapper = new QueryWrapper<>();
        if(tClass != null && StringUtils.hasText(tClass.getId())){
            wrapper.like("id",tClass.getId());
        }
        if(tClass != null && StringUtils.hasText(tClass.getClassName())){
            wrapper.like("class_name",tClass.getClassName());
        }

        return tClassMapper.selectList(wrapper);
    }

    @Override
    public List<TClass> selectPage(IPage<TClass> page,TClass tClass) {
        QueryWrapper<TClass> wrapper = new QueryWrapper<>();
        if(tClass != null && StringUtils.hasText(tClass.getId())){
            wrapper.like("id",tClass.getId());
        }
        if(tClass != null && StringUtils.hasText(tClass.getClassName())){
            wrapper.like("class_name",tClass.getClassName());
        }

        IPage<TClass> tClassIPage = tClassMapper.selectPage(page, wrapper);
        List<TClass> tClassList = tClassIPage.getRecords();
        return tClassList;
    }

    @Override
    public int insert(TClass tClass) {
        if(!StringUtils.hasText(tClass.getId())){
              UUID uuid = UUID.randomUUID();
            tClass.setId(uuid.toString());
        }

        int i = tClassMapper.insert(tClass);
        return i;
    }

    @Override
    public int update(TClass tClass) {
        QueryWrapper<TClass> wrapper = new QueryWrapper<>();
        if(tClass != null && StringUtils.hasText(tClass.getId())){
            wrapper.like("id",tClass.getId());
        }
        if(tClass != null && StringUtils.hasText(tClass.getClassName())){
            wrapper.like("class_name",tClass.getClassName());
        }

        int i = tClassMapper.update(tClass,wrapper);
        return i;
    }

    @Override
    public int updateById(TClass tClass) {
        int i = tClassMapper.updateById(tClass);
        return i;
    }

    @Override
    public int deleteById(String id) {
        QueryWrapper<TClass> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int i = tClassMapper.deleteById(id);
        return i;
    }

        @Override
        @Transactional
        public int deleteByIds(String[] ids) {
            QueryWrapper<TClass> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            int delete = tClassMapper.delete(wrapper);
            return delete;
        }

    @Override
    public long count(TClass tClass){
        QueryWrapper<TClass> wrapper = new QueryWrapper<>();
        if(tClass != null && StringUtils.hasText(tClass.getId())){
            wrapper.like("id",tClass.getId());
        }
        if(tClass != null && StringUtils.hasText(tClass.getClassName())){
            wrapper.like("class_name",tClass.getClassName());
        }

        Long i = tClassMapper.selectCount(wrapper);
        return i;
    }
}

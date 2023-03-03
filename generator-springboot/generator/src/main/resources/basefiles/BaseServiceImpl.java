package ${packageName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${packageName}.${lowerCaseName}.dao.I${className}Mapper;
import ${packageName}.${lowerCaseName}.entity.${className};
import ${packageName}.${lowerCaseName}.service.I${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class ${className}ServiceImpl implements I${className}Service {

    @Autowired
    private I${className}Mapper ${camel}Mapper;
    @Override
    public List<${className}> selectAll() {
        QueryWrapper<${className}> wrapper = new QueryWrapper<>();
        return ${camel}Mapper.selectList(wrapper);
    }

    @Override
    public ${className} selectById(String id) {
        QueryWrapper<${className}> wrapper = new QueryWrapper<>();
        wrapper.eq("${primary_key}",id);
        return ${camel}Mapper.selectOne(wrapper);
    }

    @Override
    public List<${className}> select(${className} ${camel}) {
        QueryWrapper<${className}> wrapper = new QueryWrapper<>();
${fieldCondition}
        return ${camel}Mapper.selectList(wrapper);
    }

    @Override
    public List<${className}> selectPage(IPage<${className}> page,${className} ${camel}) {
        QueryWrapper<${className}> wrapper = new QueryWrapper<>();
${fieldCondition}
        IPage<${className}> ${camel}IPage = ${camel}Mapper.selectPage(page, wrapper);
        List<${className}> ${camel}List = ${camel}IPage.getRecords();
        return ${camel}List;
    }

    @Override
    public int insert(${className} ${camel}) {
${primaryCondition}
        int i = ${camel}Mapper.insert(${camel});
        return i;
    }

    @Override
    public int update(${className} ${camel}) {
        QueryWrapper<${className}> wrapper = new QueryWrapper<>();
${fieldCondition}
        int i = ${camel}Mapper.update(${camel},wrapper);
        return i;
    }

    @Override
    public int updateById(${className} ${camel}) {
        int i = ${camel}Mapper.updateById(${camel});
        return i;
    }

    @Override
    public int deleteById(String id) {
        QueryWrapper<${className}> wrapper = new QueryWrapper<>();
        wrapper.eq("${primary_key}",id);
        int i = ${camel}Mapper.deleteById(id);
        return i;
    }

        @Override
        @Transactional
        public int deleteByIds(String[] ids) {
            QueryWrapper<${className}> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            int delete = ${camel}Mapper.delete(wrapper);
            return delete;
        }

    @Override
    public long count(${className} ${camel}){
        QueryWrapper<${className}> wrapper = new QueryWrapper<>();
${fieldCondition}
        Long i = ${camel}Mapper.selectCount(wrapper);
        return i;
    }
}

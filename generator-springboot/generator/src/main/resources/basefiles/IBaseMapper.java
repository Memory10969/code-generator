package ${packageName}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}.${lowerCaseName}.entity.${className};
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface I${className}Mapper extends BaseMapper<${className}> {
}

package ${packageName}.service;

import ${packageName}.${lowerCaseName}.entity.${className};
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface I${className}Service {

     List<${className}> selectAll();

     ${className} selectById(String id);

     List<${className}> select(${className} ${camel});

     List<${className}> selectPage(IPage<${className}> page, ${className} ${camel});

     int insert(${className} ${camel});

     int update(${className} ${camel});

     int updateById(${className} ${camel});

     int deleteById(String id);

     int deleteByIds(String[] ids);

     long count(${className} ${camel});


}

package com.wbb.generator.utils.generator;

import com.wbb.generator.utils.CommonUtil;
import com.wbb.generator.utils.GeneratorConfig;
import com.wbb.generator.utils.MySqlJDBC;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author:William
 * @Date:2023/1/1 23:41
 */
public class DaoGenerator extends BaseGenerator{

    public DaoGenerator(GeneratorConfig config) throws Exception {
        super(config);
    }

    @Override
    protected String convertTemplateToContent(String template) throws SQLException {
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String packageName = config.getPackageName();
        String lowerCaseName = CommonUtil.getLowerCaseName(tableName);
        String result = template.replace("${packageName}",packageName)
                .replace("${className}",className)
                .replace("${lowerCaseName}",lowerCaseName);

        return result;
    }

    @Override
    public boolean generateFile() throws IOException, SQLException {
        String template = CommonUtil.readTemplate("/basefiles/IBaseMapper.java");
        String content = this.convertTemplateToContent(template);
        String basePath = CommonUtil.mkOutDir("dao",rsMeta,config);
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String path = basePath + "I"+className+"Mapper.java";
        CommonUtil.writeFile(content,path);
        return true;
    }
}

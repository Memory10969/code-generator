package com.wbb.generator.utils.generator;

import com.wbb.generator.utils.CommonUtil;
import com.wbb.generator.utils.GeneratorConfig;
import com.wbb.generator.utils.MySqlJDBC;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author:William
 * @Date:2023/1/2 00:02
 */
public class ServiceGenerator extends BaseGenerator {
    public ServiceGenerator(GeneratorConfig config) throws Exception {
        super(config);
    }

    @Override
    protected String convertTemplateToContent(String template) throws SQLException {
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String camel = CommonUtil.under2camelCase(tableName);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String lowerCaseName = CommonUtil.getLowerCaseName(tableName);
        String packageName = config.getPackageName();
        String result = template.replace("${packageName}",packageName)
                .replace("${lowerCaseName}",lowerCaseName)
                .replace("${className}",className)
                .replace("${camel}",camel);
        return result;
    }

    @Override
    public boolean generateFile() throws IOException, SQLException {
        String template = CommonUtil.readTemplate("/basefiles/IBaseService.java");
        String content = this.convertTemplateToContent(template);
        String basePath = CommonUtil.mkOutDir("service",rsMeta,config);
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String path = basePath + "I"+className+"Service.java";
        CommonUtil.writeFile(content,path);
        return true;
    }
}

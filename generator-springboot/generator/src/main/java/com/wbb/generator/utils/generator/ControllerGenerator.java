package com.wbb.generator.utils.generator;

import com.wbb.generator.utils.CommonUtil;
import com.wbb.generator.utils.GeneratorConfig;
import com.wbb.generator.utils.MySqlJDBC;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author:William
 * @Date:2023/2/25 01:01
 */
public class ControllerGenerator extends BaseGenerator{

    public ControllerGenerator(GeneratorConfig config) throws Exception {
        super(config);
    }

    @Override
    protected String convertTemplateToContent(String template) throws SQLException {
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String camel = CommonUtil.under2camelCase(tableName);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String lowerCaseName = CommonUtil.getLowerCaseName(tableName);
        String packageName = config.getPackageName();
        String resultPackageName = config.getResultPackageName();
        String result = template.replace("${packageName}",packageName)
                .replace("${lowerCaseName}",lowerCaseName)
                .replace("${className}",className)
                .replace("${camel}",camel)
                .replace("${resultPackageName}",resultPackageName);
        return result;
    }

    @Override
    public boolean generateFile() throws IOException, SQLException {
        String template = CommonUtil.readTemplate("/basefiles/BaseController.java");
        String content = this.convertTemplateToContent(template);
        String controllerPath = CommonUtil.mkOutDir("controller",rsMeta,config);
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String path = controllerPath+"/"+className+"Controller.java";
        CommonUtil.writeFile(content,path);
        return true;
    }
}

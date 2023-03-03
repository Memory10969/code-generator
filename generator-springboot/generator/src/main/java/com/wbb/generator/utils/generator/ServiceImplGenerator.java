package com.wbb.generator.utils.generator;

import com.wbb.generator.utils.CommonUtil;
import com.wbb.generator.utils.GeneratorConfig;
import com.wbb.generator.utils.MySqlJDBC;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author:William
 * @Date:2023/2/24 20:50
 */
public class ServiceImplGenerator extends BaseGenerator{

    public ServiceImplGenerator(GeneratorConfig config) throws Exception {
        super(config);
    }

    @Override
    protected String convertTemplateToContent(String template) throws SQLException {
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String camel = CommonUtil.under2camelCase(tableName);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String lowerCaseName = CommonUtil.getLowerCaseName(tableName);
        String packageName = config.getPackageName();

        String primaryKey_m = CommonUtil.firstUpperCamelCase(primaryKey);
        String javaType = MySqlJDBC.toJavaType(primaryKeyTypeName);
        StringBuffer primaryCondition = new StringBuffer();
        if("String".equals(javaType)){
            primaryCondition.append("        if(!StringUtils.hasText("+camel+".get"+primaryKey_m+"())){\n" +
                    "              UUID uuid = UUID.randomUUID();\n" +
                    "            "+camel+".set"+primaryKey_m+"(uuid.toString());\n" +
                    "        }\r\n");
        }

        StringBuffer fieldCondition = new StringBuffer();
        //生成过滤条件
        for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
            String columnName = rsMeta.getColumnName(i);
            String camelField = CommonUtil.firstUpperCamelCase(columnName);
            String columnTypeName = rsMeta.getColumnTypeName(i);
            String s = MySqlJDBC.toJavaType(columnTypeName);
            if("String".equals(s)){
                fieldCondition.append("        if("+camel+" != null && StringUtils.hasText("+camel+".get"+camelField+"())){\n" +
                        "            wrapper.like(\""+columnName+"\","+camel+".get"+camelField+"());\n" +
                        "        }\r\n");
            }else{
                fieldCondition.append("        if(\""+camel+"\" != null && "+camel+".get"+camelField+"() != null){\n" +
                        "            wrapper.eq(\""+columnName+"\","+camel+".get"+camelField+"());\n" +
                        "        }\r\n");
            }
        }

        String result = template.replace("${packageName}",packageName)
                .replace("${lowerCaseName}",lowerCaseName)
                .replace("${className}",className)
                .replace("${camel}",camel)
                .replace("${primary_key}",primaryKey)
                .replace("${fieldCondition}",fieldCondition)
                .replace("${primary_keyVar_m}",primaryKey_m)
                .replace("${primaryCondition}",primaryCondition);
        return result;
    }

    @Override
    public boolean generateFile() throws IOException, SQLException {
        String template = CommonUtil.readTemplate("/basefiles/BaseServiceImpl.java");
        String content = this.convertTemplateToContent(template);
        String serviceImplPath = CommonUtil.mkOutDir("/service/impl",rsMeta,config);
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String path = serviceImplPath + className+"ServiceImpl.java";
        CommonUtil.writeFile(content,path);
        return true;
    }
}

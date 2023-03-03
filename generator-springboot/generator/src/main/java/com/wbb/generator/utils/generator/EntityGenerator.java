package com.wbb.generator.utils.generator;

import com.wbb.generator.utils.CommonUtil;
import com.wbb.generator.utils.GeneratorConfig;
import com.wbb.generator.utils.MySqlJDBC;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author:William
 * @Date:2023/1/1 23:06
 */
public class EntityGenerator extends BaseGenerator {

    public EntityGenerator(GeneratorConfig config) throws Exception {
        super(config);
    }

    @Override
    protected String convertTemplateToContent(String template) throws SQLException {
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String lowerCaseName = CommonUtil.getLowerCaseName(tableName);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String varName = CommonUtil.under2camelCase(tableName);
        //将定义好的变量替换掉
        String result = template.replace("${packageName}", config.getPackageName())
                .replace("${lowerCaseName}", lowerCaseName)
                .replace("${className}", className)
                .replace("${varName}", varName);

        //给注解添加内容
        String javaType = MySqlJDBC.toJavaType(primaryKeyTypeName);
        String varIdName = CommonUtil.under2camelCase(primaryKey);
        result =  result.replace("@TableName()","@TableName(\""+tableName+"\")");

        //拼接主键
        StringBuffer buffer = new StringBuffer();
        buffer.append("\t@TableId(\"").append(primaryKey).append("\")\r\n")
                .append("\tprivate ").append(javaType).append(" ").append(varIdName).append(";\r\n");
        //替换
        result =  result.replace("@TableId()",buffer.toString());
        String pkFirstUpper = CommonUtil.firstUpperCamelCase(primaryKey);
        //拼接普通字段
        buffer = new StringBuffer();
        StringBuffer construct = new StringBuffer();
        for(int i = 1;i<=rsMeta.getColumnCount();i++){
            String columnName = rsMeta.getColumnName(i);
            if(columnName.equals(primaryKey)){
                construct.append("\t\tthis.").append(varIdName).append(" = ").append(varName).append("Vo").append(".get").append(pkFirstUpper).append("();\r\n");
                continue;
            }
            String columnVarName = CommonUtil.under2camelCase(columnName);
            String columnTypeName = rsMeta.getColumnTypeName(i);
            String columnJavaType = MySqlJDBC.toJavaType(columnTypeName);
            buffer.append("\t@TableField(\"").append(columnName).append("\")\r\n")
                    .append("\tprivate ").append(columnJavaType).append(" ").append(columnVarName).append(";\r\n");
            String columnFirstUpper = CommonUtil.firstUpperCamelCase(columnName);
            construct.append("\t\tthis.").append(columnVarName).append(" = ").append(varName).append("Vo").append(".get").append(columnFirstUpper).append("();\r\n");
        }
        construct.deleteCharAt(construct.length()-1);
        result = result.replace("@TableField()",buffer.toString());

        return result;
    }

    @Override
    public boolean generateFile() throws IOException, SQLException {
        String template = CommonUtil.readTemplate("/basefiles/BaseEntity.java");
        String content = this.convertTemplateToContent(template);
        String entityPath = CommonUtil.mkOutDir("entity",rsMeta,config);
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String className = CommonUtil.firstUpperCamelCase(tableName);
        String path = entityPath + className+".java";
        CommonUtil.writeFile(content,path);
        return true;
    }
}

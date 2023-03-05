package com.wbb.generator.utils.generator;

import com.wbb.generator.utils.CommonUtil;
import com.wbb.generator.utils.ForeignKey;
import com.wbb.generator.utils.GeneratorConfig;
import com.wbb.generator.utils.MySqlJDBC;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author:William
 * @Date:2023/3/2 21:56
 */
public class FrontGenerator extends BaseGenerator{

    public FrontGenerator(GeneratorConfig config) throws Exception {
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

        List<ForeignKey> foreignKeyList = config.getForeignKeyList();

        StringBuffer insertFormItems = new StringBuffer();
        StringBuffer updateFormItems = new StringBuffer();
        StringBuffer tableColumnsObj = new StringBuffer();
        StringBuffer fieldListObj = new StringBuffer();
        StringBuffer emptyFieldInObj = new StringBuffer();


        StringBuffer foreignKeyListField = new StringBuffer();
        StringBuffer selectForeignKey = new StringBuffer();
        for (int j = 0; foreignKeyList != null && j < foreignKeyList.size(); j++) {
            ForeignKey foreignKey = foreignKeyList.get(j);
            String joinTable = foreignKey.getJoinTable();
            String joinTableVarName = CommonUtil.under2camelCase(joinTable);
            String lowerCaseNameTableName = CommonUtil.getLowerCaseName(joinTable);

            String foreignKeyName = foreignKey.getForeignKey();
            String joinTablePrimaryKey = foreignKey.getJoinTablePrimaryKey();
            String joinTablePrimaryKeyVarName = CommonUtil.under2camelCase(joinTablePrimaryKey);
            String joinTableLabelColumn = foreignKey.getJoinTableLabelColumn();
            String joinTableLabelColumnVarName = CommonUtil.under2camelCase(joinTableLabelColumn);

            foreignKeyListField.append("\t\t\t\t"+joinTableVarName+"List:[],\r\n");
            selectForeignKey.append("\t\t\tthis.selectForeignKey('"+lowerCaseNameTableName+"','"+joinTableVarName+"List')\r\n");
        }

        for(int i = 1;i<=rsMeta.getColumnCount();i++){
            String columnName = rsMeta.getColumnName(i);
            String columnVarName = CommonUtil.under2camelCase(columnName);
            String columnTypeName = rsMeta.getColumnTypeName(i);
            String columnJavaType = MySqlJDBC.toJavaType(columnTypeName);
            String remark = columnVarName;
            ResultSet columns = dbMeta.getColumns(null, "%", tableName, columnName);
            if(columns.next()){
                String remarks = columns.getString("REMARKS");
                if(StringUtils.hasText(remarks)){
                    remark = remarks;
                }
            }
            boolean flag = false;
            for (int j = 0; foreignKeyList != null && j < foreignKeyList.size(); j++) {
                ForeignKey foreignKey = foreignKeyList.get(j);
                String joinTable = foreignKey.getJoinTable();
                String joinTableVarName = CommonUtil.under2camelCase(joinTable);
                String foreignKeyName = foreignKey.getForeignKey();
                String joinTablePrimaryKey = foreignKey.getJoinTablePrimaryKey();
                String joinTablePrimaryKeyVarName = CommonUtil.under2camelCase(joinTablePrimaryKey);
                String joinTableLabelColumn = foreignKey.getJoinTableLabelColumn();
                String joinTableLabelColumnVarName = CommonUtil.under2camelCase(joinTableLabelColumn);

                if(columnName.equals(foreignKeyName)){
                    insertFormItems.append("                    <a-form-model-item label=\""+remark+"\">\n" +
                            "                        <a-select v-model=\"insertForm."+columnVarName+"\" placeholder=\"\">\n" +
                            "                            <a-select-option v-for=\"item in "+joinTableVarName+"List\" :key=\"item."+joinTablePrimaryKeyVarName+"\" :value=\"item."+joinTablePrimaryKeyVarName+"\">\n" +
                            "                               {{item."+joinTableLabelColumnVarName+"}}\n" +
                            "                            </a-select-option>\n" +
                            "                        </a-select>\n" +
                            "                    </a-form-model-item>");
                    updateFormItems.append("                    <a-form-model-item label=\""+remark+"\">\n" +
                            "                        <a-select v-model=\"updateForm."+columnVarName+"\" placeholder=\"\">\n" +
                            "                            <a-select-option v-for=\"item in "+joinTableVarName+"List\" :key=\"item."+joinTablePrimaryKeyVarName+"\" :value=\"item."+joinTablePrimaryKeyVarName+"\">\n" +
                            "                               {{item."+joinTableLabelColumnVarName+"}}\n" +
                            "                            </a-select-option>\n" +
                            "                        </a-select>\n" +
                            "                    </a-form-model-item>");
                    flag = true;
                    break;
                }
            }
            if(flag){
                continue;
            }
            insertFormItems.append("                <a-form-model-item ref=\""+columnVarName+"\" label=\""+remark+"\" prop=\""+columnVarName+"\">\n" +
                    "                        <a-input v-model=\"insertForm."+columnVarName+"\" @blur=\"() => {$refs."+columnVarName+".onFieldBlur();}\" />\n" +
                    "                    </a-form-model-item>\r\n");
            updateFormItems.append("                <a-form-model-item ref=\""+columnVarName+"\" label=\""+remark+"\" prop=\""+columnVarName+"\">\n" +
                    "                        <a-input v-model=\"updateForm."+columnVarName+"\" @blur=\"() => {$refs."+columnVarName+".onFieldBlur();}\" />\n" +
                    "                    </a-form-model-item>\r\n");

            if(primaryKey.equals(columnName)){
                tableColumnsObj.append("\t{ title: '"+remark+"', width: 330, dataIndex: '"+columnVarName+"', key: '"+columnVarName+"', fixed: 'left' },\r\n");
            }else{
                tableColumnsObj.append("\t{ title: '"+remark+"', width: 100, dataIndex: '"+columnVarName+"', key: '"+columnVarName+"'},\r\n");
            }

            fieldListObj.append("\t{name:'"+columnVarName+"',label:'"+remark+"'},\r\n");
            emptyFieldInObj.append("\t\t\t\t"+columnVarName+":'',\r\n");
        }

        String result = template.replace("${insertFormItems}",insertFormItems)
                .replace("${updateFormItems}",updateFormItems)
                .replace("${lowerCaseName}",lowerCaseName)
                .replace("${tableColumnsObj}",tableColumnsObj)
                .replace("${fieldListObj}",fieldListObj)
                .replace("${emptyFieldInObj}",emptyFieldInObj)
                .replace("${selectForeignKey}",selectForeignKey)
                .replace("${foreignKeyList}",foreignKeyListField);

        return result;
    }

    @Override
    public boolean generateFile() throws IOException, SQLException {
        String template = CommonUtil.readTemplate("/basefiles/baseFront.vue");
        String content = this.convertTemplateToContent(template);
        String basePath = CommonUtil.mkOutDir("front",rsMeta,config);
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String camel = CommonUtil.under2camelCase(tableName);
        String path = basePath + camel+".vue";
        CommonUtil.writeFile(content,path);
        return true;
    }
}

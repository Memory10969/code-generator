package com.wbb.generator.utils.generator;

import com.wbb.generator.utils.GeneratorConfig;
import com.wbb.generator.utils.MySqlJDBC;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author:William
 * @Date:2023/1/1 23:04
 */
public abstract class BaseGenerator {
    protected GeneratorConfig config;
    protected DatabaseMetaData dbMeta;
    protected ResultSetMetaData rsMeta;
    protected String primaryKey;
    protected String primaryKeyTypeName;

    public BaseGenerator(GeneratorConfig config) throws Exception {
        this.config = config;
        Map<String,Object> map = MySqlJDBC.executeQuery(config);
        DatabaseMetaData databaseMetaData = (DatabaseMetaData)map.get("dbMeta");
        dbMeta = databaseMetaData;
        ResultSetMetaData resultSetMetaData = (ResultSetMetaData)map.get("rsMeta");
        rsMeta = resultSetMetaData;
        if(resultSetMetaData == null){
            throw new Exception("Can not obtain MetaData by current config");
        }
        Map<String,String> primaryKeyMap = MySqlJDBC.getPrimaryKey(rsMeta,dbMeta,config);
        String primaryKey = primaryKeyMap.get("primaryKey");
        String typeName = primaryKeyMap.get("typeName");
        this.primaryKey = primaryKey;
        this.primaryKeyTypeName = typeName;
    }
    protected abstract String convertTemplateToContent(String template) throws SQLException;
    public abstract boolean generateFile() throws IOException, SQLException;
}

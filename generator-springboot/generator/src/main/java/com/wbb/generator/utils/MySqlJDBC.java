package com.wbb.generator.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class MySqlJDBC {
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3307/jwdb?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";

    private static Connection connection;

    private static void getConn(String driverName, String username, String password, String url) throws Exception {
        if(connection != null){
           connection.close();
        }
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url, username, password);
        if(connection == null){
            throw new Exception("Can not open connection by providing params");
        }
        MySqlJDBC.connection = connection;
    }

    /*
     * @desc 一次简单的查询，用来获取元数据
     * @author William
     * @create 2022/12/31
     * @return java.sql.ResultSet
     * @param [config]
     */
    public static Map<String, Object> executeQuery(GeneratorConfig config) {
        String driverName = config.getDriverName();
        String userName = config.getUserName();
        String password = config.getPassword();
        String url = config.getUrl();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            Map map = new HashMap();

            getConn(driverName, userName, password, url);
            String sqlString = config.getSql();
            DatabaseMetaData dbMetaData = connection.getMetaData();
            map.put("dbMeta",dbMetaData);
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            map.put("rsMeta",metaData);
            return map;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
        }
    }

//    //转换成list
//    public static List resultSetToList(ResultSet rs, Class beanClass) {
//        List list = new ArrayList();
//        try {
////            获取元数据
//            ResultSetMetaData metaData = rs.getMetaData();
//            int colNum = metaData.getColumnCount();
////            遍历resultset
//            while (rs.next()) {
////                每一条数据生成一个对象
//                Object o = beanClass.newInstance();
//                for (int i = 1; i <= colNum; i++) {
//                    Object value = rs.getObject(i);
//                    String colName = metaData.getColumnName(i);
//                    if (colName.indexOf(".") != -1) {
//                        colName = colName.split("[.]")[1];
//                    }
//                    String setMethodName = CommonUtils.setMethodName(colName);
//                    String valueType = metaData.getColumnTypeName(i);
//                    Class classType = null;
//                    if (valueType.equalsIgnoreCase("int")) {
//                        classType = Integer.class;
//                    } else {
//                        classType = String.class;
//                    }
//                    Method method = beanClass.getMethod(setMethodName, classType);
//                    method.invoke(o, value);
//                }
//                list.add(o);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }


    public static String getTableName(ResultSetMetaData rsMeta) throws SQLException {
        String tableName = rsMeta.getTableName(1);
        return tableName;
    }


    /*
     * @desc 获取主键 todo：增加
     * @author William
     * @create 2023/1/1 20:15
     * @return java.util.Map
     * @param []
     */
    public static Map getPrimaryKey(ResultSetMetaData rsMeta,DatabaseMetaData dbMeta,GeneratorConfig config) throws SQLException {
        String tableName = rsMeta.getTableName(1);
        String dbName = config.getDbName();
        ResultSet primaryKeys = dbMeta.getPrimaryKeys(dbName, "", tableName);
        String primaryKey = "";
        String typeName = "";
        if (primaryKeys.next()){
//            System.out.print("数据库名称:"+primaryKeys.getString("TABLE_CAT")+"                  ");
//            System.out.print("表名称:"+primaryKeys.getString("TABLE_NAME")+"                  ");
//            System.out.print("主键列的名称:"+primaryKeys.getString("COLUMN_NAME")+"                  ");
//            System.out.print("类型:"+primaryKeys.getString("PK_NAME")+"                  ");
//            System.out.println("");
            primaryKey = primaryKeys.getString("COLUMN_NAME");
        }

//        System.out.println("------------------------------分隔符--------------------------------------------");
        // 获取表的相对应的列的名字
        ResultSet tableInfo = dbMeta.getColumns(null,"%", tableName, "%");
        while (tableInfo.next()) {
//            // 表的名字
//            System.out.print("表名:" + tableInfo.getString("TABLE_NAME") + "                  ");
//            // 列的名称
//            System.out.print("列名:" + tableInfo.getString("COLUMN_NAME") + "                  ");
//            // 默认值
//            System.out.print("默认值 :" + tableInfo.getString("COLUMN_DEF") + "                  ");
//            // 字段的类型
//            System.out.print("字段的类型:" + tableInfo.getString("TYPE_NAME") + "                  ");
//            // 是否可以为空
//            System.out.print("是否可以为空:" + tableInfo.getString("IS_NULLABLE") + "                  ");
//            // 是否为自增
//            System.out.print("是否为自增:" + tableInfo.getString("IS_AUTOINCREMENT") + "                  ");
//            // 字段说明
//            System.out.print("字段说明:" + tableInfo.getString("REMARKS") + "                  ");
//            // 长度(有时候是错的)
//            System.out.print("长度:" + tableInfo.getString("COLUMN_SIZE") + "                  ");
//            System.out.println();
            String columnName = tableInfo.getString("COLUMN_NAME");
            if(columnName.equals(primaryKey)){
                typeName = tableInfo.getString("TYPE_NAME");
            }
        }

        if(StringUtils.hasText(primaryKey) && StringUtils.hasText(typeName)){
            Map<String,String> map = new HashMap();
            map.put("primaryKey",primaryKey);
            map.put("typeName",typeName);
            return map;
        }
        return null;
    }

    /*
     * @desc 将jdbc类型转换成java类型 todo：完善剩余的类型映射
     * @author William
     * @create 2023/1/1 19:03
     * @return java.lang.String
     * @param [jdbcType]
     */
    public static String toJavaType(String jdbcType){
        String result = "";
        switch (jdbcType){
            case "CHAR":
            case "VARCHAR":
            case "LONGVARCHAR":
                result = "String";break;
            case "BOOLEAN":
                result =  "Boolean";break;
            case "SMALLINT":
                result =  "Short";break;
            case "INTEGER":
            case "INT":
                result =  "Integer";break;
            case "BIGINT":
                result =  "Long";break;
            case "FLOAT":
            case "DOUBLE":
                result =  "Double";break;
            case "DATE":
                result =  "Date";break;
        }
        return result;
    }

}

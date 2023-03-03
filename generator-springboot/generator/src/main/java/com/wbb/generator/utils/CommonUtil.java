package com.wbb.generator.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Author:William
 * @Date:2023/1/1 23:14
 */
public class CommonUtil {


    /*
     * @desc 读取模板文件内容
     * @author William
     * @create 2022/12/31 23:44
     * @return java.lang.String
     * @param []
     */
    public static String readTemplate(String templatePath) throws IOException {
        InputStream poStream = ApplicationContext.class.getResourceAsStream(templatePath);
        InputStreamReader reader = new InputStreamReader(poStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        String s;
        while ((s = bufferedReader.readLine())!= null){
            buffer.append(s).append("\r\n");
        }
        bufferedReader.close();
        return buffer.toString();
    }

    /*
     * @desc 写入文件
     * @author William
     * @create 2023/1/1 20:50
     * @return boolean
     * @param [content, filePath]
     */
    public static boolean writeFile(String content,String filePath) throws IOException {
        File file = new File(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(content);
        bufferedWriter.close();
        return true;
    }


    /*
     * @desc 生成目录
     * @author William
     * @create 2023/1/1 20:40
     * @return String
     * @param [path]
     */
    public static String mkOutDir(String path,ResultSetMetaData rsMeta,GeneratorConfig config) throws SQLException {
        String baseOutPutPath = baseOutPutPath(rsMeta,config);
        String dirPath = baseOutPutPath+path+"/";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dirPath;
    }

    /*
     * @desc 获取基础输出目录
     * @author William
     * @create 2023/1/1 20:28
     * @return java.lang.String
     * @param []
     */
    public static String baseOutPutPath(ResultSetMetaData rsMeta,GeneratorConfig config) throws SQLException {
        String tableName = MySqlJDBC.getTableName(rsMeta);
        String varName = under2camelCase(tableName);
        String packageName = varName.toLowerCase();
        String dir = config.getDirPath();
        if(!dir.endsWith("/")){
            dir = dir+"/";
        }
        String result = config.getDirPath()+packageName+"/";
        return result;
    }


    /*
     * @desc 把下滑线改成驼峰
     * @author William
     * @create 2022/12/31 22:53
     * @return java.lang.String
     * @param [underlineCase]
     */
    public static String under2camelCase(String underlineCase){
        String poCamelCase = StrUtil.toCamelCase(underlineCase);
        return poCamelCase;
    }

    /*
     * @desc 包名用到的全小写
     * @author William
     * @create 2022/12/31 23:52
     * @return java.lang.String
     * @param [underlineCase]
     */
    public static String getLowerCaseName(String underlineCase){
        String camelCase = StrUtil.toCamelCase(underlineCase);
        String s = camelCase.toLowerCase();
        return s;
    }

    /*
     * @desc 首字母大写的驼峰
     * @author William
     * @create 2022/12/31 22:56
     * @return java.lang.String
     * @param [underLineCase]
     */
    public static String firstUpperCamelCase(String underLineCase){
        String camelCase = under2camelCase(underLineCase);
        String s = Character.toUpperCase(camelCase.charAt(0))+camelCase.substring(1);
        return s;
    }


}

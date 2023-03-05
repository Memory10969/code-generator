package com.wbb.generator.utils;

import com.wbb.generator.utils.generator.BaseGenerator;

import java.util.LinkedList;
import java.util.List;

public class FileGenerator {

    public static void main(String[] args) {

        String driverName = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3307/test?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
        String sql = "select * from t_teacher_class";

        try {
            ForeignKey foreignKey1 = new ForeignKey.ForeignKeyBuilder()
                    .foreignKey("class_id")
                    .joinTable("t_class")
                    .joinTablePrimaryKey("id")
                    .joinTableLabelColumn("class_name")
                    .build();

            ForeignKey foreignKey2 = new ForeignKey.ForeignKeyBuilder()
                    .foreignKey("teacher_id")
                    .joinTable("t_teacher")
                    .joinTablePrimaryKey("id")
                    .joinTableLabelColumn("teacher_name")
                    .build();
//
            List<ForeignKey> list = new LinkedList<>();
            list.add(foreignKey1);
            list.add(foreignKey2);

            GeneratorConfig config = new GeneratorConfig.GeneratorConfigBuilder()
                    .driverName(driverName)
                    .userName(username)
                    .password(password)
                    .url(url)
                    .sql(sql)
                    .dbName("test")//数据库名称
                    .packageName("com.wbb.generator")//包名
                    .resultPackageName("com.wbb.generator.utils")//返回值类
                    .dirPath("./")//文件夹路径，不会改就别改
                    .foreignKeyList(list)//外键列表
                    .build();

            //entity
            BaseGenerator entity = FileGeneratorFactory.getInstance("entity", config);
            entity.generateFile();
            //dao层
            BaseGenerator dao = FileGeneratorFactory.getInstance("dao",config);
            dao.generateFile();
            //service
            BaseGenerator service = FileGeneratorFactory.getInstance("service", config);
            service.generateFile();
            //serviceImpl
            BaseGenerator serviceImpl = FileGeneratorFactory.getInstance("serviceImpl", config);
            serviceImpl.generateFile();
            //controller
            BaseGenerator controller = FileGeneratorFactory.getInstance("controller",config);
            controller.generateFile();
            //vue
            BaseGenerator front = FileGeneratorFactory.getInstance("front",config);
            front.generateFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

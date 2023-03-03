package com.wbb.generator.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class GeneratorConfig {

    private String driverName;
    private String userName;
    private String password;
    private String url;
    //一张表的所有字段的简单查询 SELECT * FROM tableName
    private String sql;

    //文件保存路径
    private String dirPath = "./";

    private String packageName = "com.wbb";

    private String resultPackageName = "com.wbb.utils";

    private String dbName;

    List<ForeignKey> foreignKeyList;

}

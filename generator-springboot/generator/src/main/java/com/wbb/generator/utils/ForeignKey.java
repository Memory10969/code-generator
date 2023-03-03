package com.wbb.generator.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author:William
 * @Date:2023/3/3 14:12
 */
@AllArgsConstructor
@Data
@Builder
public class ForeignKey {
    private String joinTable;//关联表

    private String foreignKey;//外键

    private String joinTablePrimaryKey;//关联表主键

    private String joinTableLabelColumn;//关联表label字段
}

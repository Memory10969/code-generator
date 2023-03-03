package com.wbb.generator.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author william
 * @Description
 * @createTime 2022-12-31 11:05
 */
@Data
@AllArgsConstructor
public class UserRolePo {
    private String userId;
    private String roleId;
}

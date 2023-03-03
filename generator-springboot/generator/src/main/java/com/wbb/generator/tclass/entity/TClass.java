package com.wbb.generator.tclass.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("t_class")
public class TClass {
	@TableId("id")
	private String id;

	@TableField("class_name")
	private String className;

}

package com.wbb.generator.tstudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("t_student")
public class TStudent {
	@TableId("id")
	private String id;

	@TableField("name")
	private String name;
	@TableField("gender")
	private String gender;
	@TableField("age")
	private Integer age;
	@TableField("phone_number")
	private String phoneNumber;
	@TableField("class_id")
	private String classId;

}

package com.wbb.generator.tteacher.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("t_teacher")
public class TTeacher {
	@TableId("id")
	private String id;

	@TableField("teacher_name")
	private String teacherName;
	@TableField("major")
	private String major;

}

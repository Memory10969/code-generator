package com.wbb.generator.tteacherclass.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("t_teacher_class")
public class TTeacherClass {
	@TableId("id")
	private String id;

	@TableField("teacher_id")
	private String teacherId;
	@TableField("class_id")
	private String classId;

}

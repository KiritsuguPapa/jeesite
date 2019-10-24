/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 班级管理Entity
 * @author Kiritsugu
 * @version 2019-10-23
 */
public class MyClass extends DataEntity<MyClass> {
	
	private static final long serialVersionUID = 1L;
	private String className;		// 班级名称
	private String classTecher;		// 班主任
	private List<Student> studentList = Lists.newArrayList();		// 子表列表
	
	public MyClass() {
		super();
	}

	public MyClass(String id){
		super(id);
	}

	@Length(min=0, max=255, message="班级名称长度必须介于 0 和 255 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=255, message="班主任长度必须介于 0 和 255 之间")
	public String getClassTecher() {
		return classTecher;
	}

	public void setClassTecher(String classTecher) {
		this.classTecher = classTecher;
	}
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
}
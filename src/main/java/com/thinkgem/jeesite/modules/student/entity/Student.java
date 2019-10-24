/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 班级管理Entity
 * @author Kiritsugu
 * @version 2019-10-23
 */
public class Student extends DataEntity<Student> {
	
	private static final long serialVersionUID = 1L;
	private String stuName;		// 学生姓名
	private String stuGender;		// 学生性别
	private String stuAge;		// 学生年龄
	private MyClass myClass;		// 班级编号 父类
	
	public Student() {
		super();
	}

	public Student(String id){
		super(id);
	}

	public Student(MyClass myClass){
		this.myClass = myClass;
	}

	@Length(min=0, max=50, message="学生姓名长度必须介于 0 和 50 之间")
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Length(min=0, max=4, message="学生性别长度必须介于 0 和 4 之间")
	public String getStuGender() {
		return stuGender;
	}

	public void setStuGender(String stuGender) {
		this.stuGender = stuGender;
	}
	
	@Length(min=0, max=11, message="学生年龄长度必须介于 0 和 11 之间")
	public String getStuAge() {
		return stuAge;
	}

	public void setStuAge(String stuAge) {
		this.stuAge = stuAge;
	}
	
	@Length(min=0, max=64, message="班级编号长度必须介于 0 和 64 之间")
	public MyClass getMyClass() {
		return myClass;
	}

	public void setMyClass(MyClass myClass) {
		this.myClass = myClass;
	}
	
}
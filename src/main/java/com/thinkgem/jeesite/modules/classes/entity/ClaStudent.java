/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 班级管理Entity
 * @author Kiritsugu
 * @version 2019-10-23
 */
public class ClaStudent extends DataEntity<ClaStudent> {
	
	private static final long serialVersionUID = 1L;
	private String stuName;		// 姓名
	private String stuGender;		// 性别
	private ClaClasses classId;		// 班级id 父类
	
	public ClaStudent() {
		super();
	}

	public ClaStudent(String id){
		super(id);
	}

	public ClaStudent(ClaClasses classId){
		this.classId = classId;
	}

	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getStuGender() {
		return stuGender;
	}

	public void setStuGender(String stuGender) {
		this.stuGender = stuGender;
	}
	
	@Length(min=0, max=64, message="班级id长度必须介于 0 和 64 之间")
	public ClaClasses getClassId() {
		return classId;
	}

	public void setClassId(ClaClasses classId) {
		this.classId = classId;
	}
	
}
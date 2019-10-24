/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 班级管理Entity
 * @author Kiritsugu
 * @version 2019-10-23
 */
public class ClaClasses extends DataEntity<ClaClasses> {
	
	private static final long serialVersionUID = 1L;
	private String className;		// 班级名
	private String classTecher;		// 班主任
	private List<ClaStudent> claStudentList = Lists.newArrayList();		// 子表列表
	
	public ClaClasses() {
		super();
	}

	public ClaClasses(String id){
		super(id);
	}

	@Length(min=0, max=64, message="班级名长度必须介于 0 和 64 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=64, message="班主任长度必须介于 0 和 64 之间")
	public String getClassTecher() {
		return classTecher;
	}

	public void setClassTecher(String classTecher) {
		this.classTecher = classTecher;
	}
	
	public List<ClaStudent> getClaStudentList() {
		return claStudentList;
	}

	public void setClaStudentList(List<ClaStudent> claStudentList) {
		this.claStudentList = claStudentList;
	}
}
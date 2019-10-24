/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.student.entity.Student;

/**
 * 班级管理DAO接口
 * @author Kiritsugu
 * @version 2019-10-23
 */
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {
	
}
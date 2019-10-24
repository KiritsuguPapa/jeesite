/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.classes.entity.ClaStudent;

/**
 * 班级管理DAO接口
 * @author Kiritsugu
 * @version 2019-10-23
 */
@MyBatisDao
public interface ClaStudentDao extends CrudDao<ClaStudent> {
	
}
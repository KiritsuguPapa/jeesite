/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.classes.entity.ClaStudent;
import com.thinkgem.jeesite.modules.classes.dao.ClaStudentDao;

/**
 * 学生管理Service
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class ClaStudentService extends CrudService<ClaStudentDao, ClaStudent> {

	public ClaStudent get(String id) {
		return super.get(id);
	}
	
	public List<ClaStudent> findList(ClaStudent claStudent) {
		return super.findList(claStudent);
	}
	
	public Page<ClaStudent> findPage(Page<ClaStudent> page, ClaStudent claStudent) {
		return super.findPage(page, claStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(ClaStudent claStudent) {
		super.save(claStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(ClaStudent claStudent) {
		super.delete(claStudent);
	}
	
}
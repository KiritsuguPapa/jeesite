/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.classes.entity.ClaClasses;
import com.thinkgem.jeesite.modules.classes.dao.ClaClassesDao;
import com.thinkgem.jeesite.modules.classes.entity.ClaStudent;
import com.thinkgem.jeesite.modules.classes.dao.ClaStudentDao;

/**
 * 班级管理Service
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class ClaClassesService extends CrudService<ClaClassesDao, ClaClasses> {

	@Autowired
	private ClaStudentDao claStudentDao;
	
	public ClaClasses get(String id) {
		ClaClasses claClasses = super.get(id);
		claClasses.setClaStudentList(claStudentDao.findList(new ClaStudent(claClasses)));
		return claClasses;
	}
	
	public List<ClaClasses> findList(ClaClasses claClasses) {
		return super.findList(claClasses);
	}
	
	public Page<ClaClasses> findPage(Page<ClaClasses> page, ClaClasses claClasses) {
		return super.findPage(page, claClasses);
	}
	
	@Transactional(readOnly = false)
	public void save(ClaClasses claClasses) {
		super.save(claClasses);
		for (ClaStudent claStudent : claClasses.getClaStudentList()){
			if (claStudent.getId() == null){
				continue;
			}
			if (ClaStudent.DEL_FLAG_NORMAL.equals(claStudent.getDelFlag())){
				if (StringUtils.isBlank(claStudent.getId())){
					claStudent.setClassId(claClasses);
					claStudent.preInsert();
					claStudentDao.insert(claStudent);
				}else{
					claStudent.preUpdate();
					claStudentDao.update(claStudent);
				}
			}else{
				claStudentDao.delete(claStudent);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ClaClasses claClasses) {
		super.delete(claClasses);
		claStudentDao.delete(new ClaStudent(claClasses));
	}
	
}
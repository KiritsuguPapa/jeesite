/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.student.entity.MyClass;
import com.thinkgem.jeesite.modules.student.dao.MyClassDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.dao.StudentDao;

/**
 * 班级管理Service
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class MyClassService extends CrudService<MyClassDao, MyClass> {

	@Autowired
	private StudentDao studentDao;
	
	public MyClass get(String id) {
		MyClass myClass = super.get(id);
		myClass.setStudentList(studentDao.findList(new Student(myClass)));
		return myClass;
	}
	
	public List<MyClass> findList(MyClass myClass) {
		return super.findList(myClass);
	}
	
	public Page<MyClass> findPage(Page<MyClass> page, MyClass myClass) {
		return super.findPage(page, myClass);
	}
	
	@Transactional(readOnly = false)
	public void save(MyClass myClass) {
		super.save(myClass);
		for (Student student : myClass.getStudentList()){
			if (student.getId() == null){
				continue;
			}
			if (Student.DEL_FLAG_NORMAL.equals(student.getDelFlag())){
				if (StringUtils.isBlank(student.getId())){
					student.setMyClass(myClass);
					student.preInsert();
					studentDao.insert(student);
				}else{
					student.preUpdate();
					studentDao.update(student);
				}
			}else{
				studentDao.delete(student);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MyClass myClass) {
		super.delete(myClass);
		studentDao.delete(new Student(myClass));
	}
	
}
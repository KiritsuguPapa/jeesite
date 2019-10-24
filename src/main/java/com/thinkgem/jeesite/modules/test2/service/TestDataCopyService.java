/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.test2.entity.TestDataCopy;
import com.thinkgem.jeesite.modules.test2.dao.TestDataCopyDao;

/**
 * 测试Service
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class TestDataCopyService extends CrudService<TestDataCopyDao, TestDataCopy> {

	public TestDataCopy get(String id) {
		return super.get(id);
	}
	
	public List<TestDataCopy> findList(TestDataCopy testDataCopy) {
		return super.findList(testDataCopy);
	}
	
	public Page<TestDataCopy> findPage(Page<TestDataCopy> page, TestDataCopy testDataCopy) {
		return super.findPage(page, testDataCopy);
	}
	
	@Transactional(readOnly = false)
	public void save(TestDataCopy testDataCopy) {
		super.save(testDataCopy);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestDataCopy testDataCopy) {
		super.delete(testDataCopy);
	}
	
}
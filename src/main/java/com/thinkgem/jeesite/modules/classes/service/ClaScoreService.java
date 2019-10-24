/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.classes.entity.ClaScore;
import com.thinkgem.jeesite.modules.classes.dao.ClaScoreDao;

/**
 * 成绩管理Service
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class ClaScoreService extends CrudService<ClaScoreDao, ClaScore> {

	public ClaScore get(String id) {
		return super.get(id);
	}
	
	public List<ClaScore> findList(ClaScore claScore) {
		return super.findList(claScore);
	}
	
	public Page<ClaScore> findPage(Page<ClaScore> page, ClaScore claScore) {
		return super.findPage(page, claScore);
	}
	
	@Transactional(readOnly = false)
	public void save(ClaScore claScore) {
		super.save(claScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(ClaScore claScore) {
		super.delete(claScore);
	}
	
}
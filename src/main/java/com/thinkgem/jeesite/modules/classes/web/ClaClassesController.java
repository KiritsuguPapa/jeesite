/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.classes.entity.ClaClasses;
import com.thinkgem.jeesite.modules.classes.service.ClaClassesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 班级管理Controller
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/classes/claClasses")
public class ClaClassesController extends BaseController {

	@Autowired
	private ClaClassesService claClassesService;
	
	@ModelAttribute
	public ClaClasses get(@RequestParam(required=false) String id) {
		ClaClasses entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = claClassesService.get(id);
		}
		if (entity == null){
			entity = new ClaClasses();
		}
		return entity;
	}
	
	@RequiresPermissions("classes:claClasses:view")
	@RequestMapping(value = {"list", ""})
	public String list(ClaClasses claClasses, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ClaClasses> page = claClassesService.findPage(new Page<ClaClasses>(request, response), claClasses); 
		model.addAttribute("page", page);
		return "modules/classes/claClassesList";
	}

	@RequiresPermissions("classes:claClasses:view")
	@RequestMapping(value = "form")
	public String form(ClaClasses claClasses, Model model) {
		model.addAttribute("claClasses", claClasses);
		return "modules/classes/claClassesForm";
	}

	@RequiresPermissions("classes:claClasses:edit")
	@RequestMapping(value = "save")
	public String save(ClaClasses claClasses, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, claClasses)){
			return form(claClasses, model);
		}
		claClassesService.save(claClasses);
		addMessage(redirectAttributes, "保存班级管理成功");
		return "redirect:"+Global.getAdminPath()+"/classes/claClasses/?repage";
	}
	
	@RequiresPermissions("classes:claClasses:edit")
	@RequestMapping(value = "delete")
	public String delete(ClaClasses claClasses, RedirectAttributes redirectAttributes) {
		claClassesService.delete(claClasses);
		addMessage(redirectAttributes, "删除班级管理成功");
		return "redirect:"+Global.getAdminPath()+"/classes/claClasses/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String,Object>> treeData(@RequestParam(required=false) String name ,HttpServletResponse response){
	List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
	ClaClasses claClasses =new ClaClasses();
	List<ClaClasses> list = claClassesService.findList(claClasses);
	for (int i = 0 ; i<list.size();i++){
		ClaClasses c = list.get(i);
		if(c!=null){
			Map<String,Object> map = Maps.newHashMap();
			map.put("id",c.getId());
			map.put("name",c.getClassName());
			map.put("TeacherName",c.getClassTecher());
			mapList.add(map);
		}
	}
	return mapList;
	}




}
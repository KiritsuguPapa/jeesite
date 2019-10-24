/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.modules.student.entity.Student;
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
import com.thinkgem.jeesite.modules.classes.entity.ClaStudent;
import com.thinkgem.jeesite.modules.classes.service.ClaStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学生管理Controller
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/classes/claStudent")
public class ClaStudentController extends BaseController {

	@Autowired
	private ClaStudentService claStudentService;
	
	@ModelAttribute
	public ClaStudent get(@RequestParam(required=false) String id) {
		ClaStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = claStudentService.get(id);
		}
		if (entity == null){
			entity = new ClaStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("classes:claStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(ClaStudent claStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ClaStudent> page = claStudentService.findPage(new Page<ClaStudent>(request, response), claStudent); 
		model.addAttribute("page", page);
		return "modules/classes/claStudentList";
	}

	@RequiresPermissions("classes:claStudent:view")
	@RequestMapping(value = "form")
	public String form(ClaStudent claStudent, Model model) {
		model.addAttribute("claStudent", claStudent);
		return "modules/classes/claStudentForm";
	}

	@RequiresPermissions("classes:claStudent:edit")
	@RequestMapping(value = "save")
	public String save(ClaStudent claStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, claStudent)){
			return form(claStudent, model);
		}
		claStudentService.save(claStudent);
		addMessage(redirectAttributes, "保存学生管理成功");
		return "redirect:"+Global.getAdminPath()+"/classes/claStudent/?repage";
	}
	
	@RequiresPermissions("classes:claStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(ClaStudent claStudent, RedirectAttributes redirectAttributes) {
		claStudentService.delete(claStudent);
		addMessage(redirectAttributes, "删除学生管理成功");
		return "redirect:"+Global.getAdminPath()+"/classes/claStudent/?repage";
	}


	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String,Object>> treeData(@RequestParam(required = false) String name,HttpServletResponse response){
		List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
		ClaStudent  clastudent = new ClaStudent();
		List<ClaStudent> list = claStudentService.findList(clastudent);
		for(int i =0 ;i<list.size();i++){
			ClaStudent c = list.get(i);
			if (c!=null){
				Map<String,Object> map = Maps.newHashMap();
				map.put("id",c.getId());
				map.put("name",c.getStuName());
				mapList.add(map);
			}
		}
		return  mapList;

	}
}
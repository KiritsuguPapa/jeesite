/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.student.entity.MyClass;
import com.thinkgem.jeesite.modules.student.service.MyClassService;

/**
 * 班级管理Controller
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/student/myClass")
public class MyClassController extends BaseController {

	@Autowired
	private MyClassService myClassService;

	
	private int i;

	@ModelAttribute
	public MyClass get(@RequestParam(required=false) String id) {
		MyClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = myClassService.get(id);
		}
		if (entity == null){
			entity = new MyClass();
		}
		return entity;
	}
	
	@RequiresPermissions("student:myClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyClass myClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MyClass> page = myClassService.findPage(new Page<MyClass>(request, response), myClass); 
		model.addAttribute("page", page);
		return "modules/student/myClassList";
	}

	@RequiresPermissions("student:myClass:view")
	@RequestMapping(value = "form")
	public String form(MyClass myClass, Model model) {
		model.addAttribute("myClass", myClass);
		return "modules/student/myClassForm";
	}

	@RequiresPermissions("student:myClass:edit")
	@RequestMapping(value = "save")
	public String save(MyClass myClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, myClass)){
			return form(myClass, model);
		}
		myClassService.save(myClass);
		addMessage(redirectAttributes, "保存班级信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/myClass/?repage";
	}
	
	@RequiresPermissions("student:myClass:edit")
	@RequestMapping(value = "delete")
	public String delete(MyClass myClass, RedirectAttributes redirectAttributes) {
		myClassService.delete(myClass);
		addMessage(redirectAttributes, "删除班级信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/myClass/?repage";
	}

}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.web;

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
import com.thinkgem.jeesite.modules.classes.entity.ClaScore;
import com.thinkgem.jeesite.modules.classes.service.ClaScoreService;

/**
 * 成绩管理Controller
 * @author Kiritsugu
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/classes/claScore")
public class ClaScoreController extends BaseController {

	@Autowired
	private ClaScoreService claScoreService;
	
	@ModelAttribute
	public ClaScore get(@RequestParam(required=false) String id) {
		ClaScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = claScoreService.get(id);
		}
		if (entity == null){
			entity = new ClaScore();
		}
		return entity;
	}
	
	@RequiresPermissions("classes:claScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(ClaScore claScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ClaScore> page = claScoreService.findPage(new Page<ClaScore>(request, response), claScore); 
		model.addAttribute("page", page);
		return "modules/classes/claScoreList";
	}

	@RequiresPermissions("classes:claScore:view")
	@RequestMapping(value = "form")
	public String form(ClaScore claScore, Model model) {
		model.addAttribute("claScore", claScore);
		return "modules/classes/claScoreForm";
	}

	@RequiresPermissions("classes:claScore:edit")
	@RequestMapping(value = "save")
	public String save(ClaScore claScore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, claScore)){
			return form(claScore, model);
		}
		claScoreService.save(claScore);
		addMessage(redirectAttributes, "保存成绩管理成功");
		return "redirect:"+Global.getAdminPath()+"/classes/claScore/?repage";
	}
	
	@RequiresPermissions("classes:claScore:edit")
	@RequestMapping(value = "delete")
	public String delete(ClaScore claScore, RedirectAttributes redirectAttributes) {
		claScoreService.delete(claScore);
		addMessage(redirectAttributes, "删除成绩管理成功");
		return "redirect:"+Global.getAdminPath()+"/classes/claScore/?repage";
	}



}
package com.zh.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zh.entity.ClassAdmin;
import com.zh.service.ClassService;
import com.zh.utils.MyFileUtils;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	@Resource
	private ClassService classService;
	
	@RequestMapping("/classes")
	public String find(Model model){
		model.addAttribute("classes", classService.find());
		return "class/classes";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {

		model.addAttribute(new ClassAdmin());
		return "class/add";
	}

/*	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(ClassAdmin classAdmin) {
		
		classService.save(classAdmin);
		return "redirect:/class/classes";
	}*/
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addClass(ClassAdmin classAdmin) {
		
		if(classService.save(classAdmin)==true)
			return "success";
		return "";
	}
	
	@RequestMapping(value = "/{classId}/update")
	@ResponseBody
	public String update(ClassAdmin classAdmin, @PathVariable String classId) {
		classAdmin.setClassId(classId);
		classService.update(classAdmin);
		return "修改成功";
		//return "redirect:/class/classes";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String id,HttpSession session) {
		ClassAdmin classAdmin = classService.get(id);
		if(classAdmin!=null){
			this.deleteFile(classAdmin, session);
			classService.delete(classAdmin);
		}
		return "redirect:/class/classes";
	}
	
	private void deleteFile(ClassAdmin classAdmin ,HttpSession session){
		String path = session.getServletContext().getRealPath("/WEB-INF/upload/homework");
		File f= new File(path+"\\"+classAdmin.getClassId());
		MyFileUtils.deleteFile(f);
		
		
	}
	
	
}

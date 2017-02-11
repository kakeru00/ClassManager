package com.zh.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zh.entity.ClassAdmin;
import com.zh.entity.Teacher;
import com.zh.service.TeacherService;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Resource
	private TeacherService teacherService;
	
	@RequestMapping(value = {"/teachers"}, method = RequestMethod.GET)
	public String find(Model model ,HttpSession session) {
		ClassAdmin classAdmin = (ClassAdmin) session.getAttribute("classAdmin");
		DetachedCriteria criteria = DetachedCriteria.forClass(Teacher.class);
		criteria.add(Restrictions.eq("classAdmin", classAdmin));
		model.addAttribute("teachers",teacherService.find(criteria));
		return "teacher/teachers";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Teacher teacher,HttpSession session) {
		ClassAdmin classAdmin = (ClassAdmin)session.getAttribute("classAdmin");
		
		teacher.setClassAdmin(classAdmin);
		teacherService.save(teacher);
		return "添加成功";
		//return "redirect:/teacher/teachers";
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(Model model, @PathVariable int id) {
		model.addAttribute(teacherService.get(id));
		return "teacher/update";
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(Teacher teacher, @PathVariable int id) {

		teacherService.update(teacher);
		return "";
	}
	
	
	
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		Teacher teacher = teacherService.get(id);
		if(teacher!=null){
			
			teacherService.delete(teacher);;
		}
		return "redirect:/teacher/teachers";
	}
	
	@ModelAttribute  
	public void getTeacher(@RequestParam(value="id",required=false)Integer id, Map<String,Object> map){
	    if (id != null) {
	        map.put("teacher", teacherService.get(id));
	    }

		
	}
}

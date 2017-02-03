package com.zh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zh.entity.ClassAdmin;
import com.zh.entity.Student;
import com.zh.entity.SuperAdmin;
import com.zh.entity.Teacher;
import com.zh.service.ClassService;
import com.zh.service.StudentService;
import com.zh.service.SuperAdminService;
import com.zh.service.TeacherService;

@Controller
@RequestMapping(value = {"/login","/"})
public class LoginController {
	@Resource
	private StudentService studentService;
	@Resource
	private SuperAdminService superAdminService;
	@Resource
	private ClassService classService;
	@Resource
	private TeacherService teacherService;
	//@RequestMapping(value={"/test"})
	public String test(){
		
		return "index";
	}
	
	@RequestMapping(value={"/login","/"},method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	
	@RequestMapping(value="/student",method=RequestMethod.POST)
	@ResponseBody
	public String login(String id,String password,HttpSession session){
		Student student = studentService.get(id);
		String message = null;
		if(student==null){
			//用户名不存在
			message =  "学号不存在";
		}else if(!student.getPassword().equals(password)){
			//密码不正确
			message =  "密码不正确";
		}else{
			this.logout(session);
			session.setAttribute("student", student);
			message =  "学生登录成功";
		}
		System.out.println(message);
		return message;
	}
	@RequestMapping(value="/teacher",method=RequestMethod.POST)
	@ResponseBody
	public String teacherLogin(int id, String password, HttpSession session){
		Teacher teacher = teacherService.get(id);
		String mess = null;
		if(teacher==null){
			mess = "教师id不存在";
		}else if(!teacher.getPassword().equals(password)){
			mess = "密码错误";
		}else{
			this.logout(session);
			session.setAttribute("teacher", teacher);
			mess = "教师登录成功";
		}
		return mess;
	}
	
	
	@RequestMapping(value="/admin",method=RequestMethod.POST)
	@ResponseBody
	public String adminLogin(String id,String password,HttpSession session){
		SuperAdmin superAdmin = superAdminService.get(id);
		if(superAdmin!=null){
			if(superAdmin.getPassword().equals(password)){
				this.logout(session);
				session.setAttribute("superAdmin", superAdmin);
				//return "redirect:/class/classes";
				return "超管登录成功";
			}
		}else{
			/*int i = 0 ;
			try{
				i = Integer.parseInt(id);
				
			}catch(Exception e){
				return "redirect:/";
			}*/
			
			ClassAdmin classAdmin = classService.get(id);
			if(classAdmin!=null){
				if(password.equals(classAdmin.getPassword())){
					this.logout(session);
					session.setAttribute("classAdmin", classAdmin);
					//return "redirect:/student/students";
					return "管理员登录成功";
				}else
					return "密码错误";
			}
		}
		return "管理帐号不存在";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("superAdmin");
		session.removeAttribute("classAdmin");
		session.removeAttribute("student");
		return "redirect:/";
	}
	
	@RequestMapping("/mainHead")
	public String head(){
		
		return "mainHead";
	}
	
	@RequestMapping("/main")
	public String main(){
		
		return "main";
	}
}

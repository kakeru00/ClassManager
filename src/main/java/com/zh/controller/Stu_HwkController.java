package com.zh.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zh.entity.Homework;
import com.zh.entity.Student;
import com.zh.entity.Student_Homework;
import com.zh.service.HomeworkService;
import com.zh.service.Stu_HwkService;

@Controller
public class Stu_HwkController {
	@Resource
	private Stu_HwkService stu_hwkService;
	@Resource
	private HomeworkService  homeworkService;
	
	
	public String handIn(int homeworkId,@RequestParam("file")MultipartFile file,HttpServletRequest request) throws IOException{
		this.upload(file, request);
		Homework homework = homeworkService.load(homeworkId);
		Student student = (Student) request.getSession().getAttribute("student");
		Student_Homework  s_h = new Student_Homework();
		s_h.setHomework(homework);
		s_h.setStudent(student);
		stu_hwkService.saveOrUpdate(s_h);
		return "redirect:/homework/homeworks";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
		String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/homework");
		File f = new File(realpath + "\\" + file.getOriginalFilename());
		FileUtils.copyInputStreamToFile(file.getInputStream(), f);
		return "redirect:/homework/homeworks";
	}
}

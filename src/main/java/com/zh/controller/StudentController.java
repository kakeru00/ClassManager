package com.zh.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zh.entity.ClassAdmin;
import com.zh.entity.Student;
import com.zh.service.StudentService;
import com.zh.utils.MyFileUtils;
import com.zh.utils.Pager;

@Controller
@RequestMapping(value = {"/student"})
public class StudentController {
	@Resource
	private StudentService studentService;

	@RequestMapping(value = {"/all"}, method = RequestMethod.GET)
	public String find(Model model ,HttpSession session) {
		ClassAdmin classAdmin = (ClassAdmin) session.getAttribute("classAdmin");
		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("classAdmin", classAdmin));
		model.addAttribute("students",studentService.find(criteria));
		return "student/students";
	}
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public String find() {

		
		return "redirect:/student/students";
	}
/*	@RequestMapping(value="/students/{currentPage}", method = RequestMethod.GET)
	public String findByPage(@PathVariable int currentPage,Model model,@ModelAttribute Pager<Student> p,HttpSession session){
		ClassAdmin classAdmin = (ClassAdmin) session.getAttribute("classAdmin");
		
		p.setCurrentPage(currentPage);
		p.setTotal(studentService.count());


		model.addAttribute("students", studentService.findByPage(p,classAdmin));
		return "student/students";
	}*/
	
	@RequestMapping(value = {"/students"})
	public String findByPage(Integer goPage,Model model,@ModelAttribute Pager<Student> p,HttpSession session ){
		ClassAdmin classAdmin = (ClassAdmin) session.getAttribute("classAdmin");
		int studentAmount = studentService.count(classAdmin);
		if(goPage==null)goPage=1;
		p.setCurrentPage(goPage);
		p.setTotal(studentAmount);
		int firstResult = p.getOffset();
		int maxResults = p.getPageSize();
		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("classAdmin", classAdmin));

		model.addAttribute("students", studentService.findByPage(criteria,firstResult,maxResults));
		model.addAttribute("studentAmount", studentAmount);
		return "student/students";
	}
	
/*	@RequestMapping(value="/students", method = RequestMethod.GET)
	public String findByPage(Model model,@ModelAttribute Pager<Student> p){

		p.setCurrentPage(1);
		model.addAttribute("students", studentService.findByPage(p));
		return "student/students";
	}*/
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {

		model.addAttribute(new Student());
		return "student/add";
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Student student,HttpSession session) {
		ClassAdmin classAdmin = (ClassAdmin)session.getAttribute("classAdmin");
		String studentId = classAdmin.getClassId()+student.getId();
		student.setClassAdmin(classAdmin);
		student.setId(studentId);
		studentService.save(student);
		return "redirect:/student/students";
	}
	
	//新增用户同时上传用户文件
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newStudent(Student student,@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException{
		System.out.println(file.getOriginalFilename());
		this.upload(file,request);
		student.setPhoto(request.getSession().getServletContext().getRealPath("/upload")+"/"+file.getOriginalFilename());
		this.add(student,request.getSession());
		return "redirect:/student/students";
	}
	
	@RequestMapping("/addmany")
	@ResponseBody
	public String add(int studentAmount,HttpSession session){
		if(studentAmount>=100){
			return null;
		}
		ClassAdmin classAdmin = (ClassAdmin)session.getAttribute("classAdmin");
		String classId = classAdmin.getClassId();
		DecimalFormat df=new DecimalFormat("00");
		List<Student> l = studentService.find("from Student where id = (select max(id) from Student where classid = "+classId+") ");
		int beginIndex = 1;
		
		if(l.size()>0) {
			String id = l.get(0).getId();
			beginIndex = Integer.parseInt(id.substring(id.length()-2))+1;
			System.out.println(beginIndex);
			studentAmount = beginIndex + studentAmount - 1;
		}
		if(studentAmount>=100) return null;
		List<Student> students = new ArrayList<Student>();
		for (int i = beginIndex; i <= studentAmount; i++) {
			String studentId = classId+df.format(i);
			Student student = new Student();
			student.setId(studentId);
			student.setClassAdmin(classAdmin);
			students.add(student);
		}
		studentService.save(students);
		//return "redirect:/student/students";
		return "添加成功";
	}
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(Model model, @PathVariable String id) {
		model.addAttribute(studentService.get(id));
		return "student/update";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(Student student, @PathVariable String id) {

		studentService.update(student);
		return "redirect:/student/students";
	}
/*	@RequestMapping(value = "/update")
	public String update(Model model,HttpSession session){
		Student student = (Student)session.getAttribute("student");
		return update(model, student.getId());
		
	}*/
	
	@RequestMapping(value = "/{id}/changeAccess")
	@ResponseBody
	public Student changeAccess(@PathVariable String id,HttpSession session) {
		Student student = studentService.get(id);
		if(student.isAccess()==true)
			student.setAccess(false);
		else student.setAccess(true);
		studentService.update(student);
		return student;
	}
	
	
	@ModelAttribute  
	public void getStudent(@RequestParam(value="id",required=false)String id, Map<String,Object> map){
	    if (id != null) {
	        map.put("student", studentService.get(id));
	    }

		
	}
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String id) {
		Student student = studentService.get(id);
		if(student!=null){
			
			studentService.delete(student);;
		}
		return "redirect:/student/students";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
		String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		File f = new File(realpath + "/" + file.getOriginalFilename());
		System.out.println(f.getPath());
		FileUtils.copyInputStreamToFile(file.getInputStream(), f);

		return "redirect:/student/students";
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String fileName, HttpServletRequest request) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(path+"/"+fileName);
		//if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
			fileName = URLEncoder.encode(fileName, "UTF-8");//解决下载的文件名乱码
		//} else {
			//fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//IE之外
		//}
		//fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");//解决下载的文件名乱码
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		
		System.out.println("download:"+file.getName());
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);//IE下需为OK，其他CREATE
	}
	
	@RequestMapping("/downloadAll")
	public ResponseEntity<byte[]> downloadAll(String zipName,HttpServletRequest request){
		String uploadPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		File folder = new File(uploadPath);
		File[] files = folder.listFiles();
		if(zipName==null) zipName= "all.zip";
		try {
			MyFileUtils.packageZip(uploadPath, zipName, files);
			return download(zipName,request);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			new File(uploadPath,zipName).delete();
		}
		return null;
	}

}

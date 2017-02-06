package com.zh.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zh.entity.ClassAdmin;
import com.zh.entity.Homework;
import com.zh.entity.Student;
import com.zh.entity.Student_Homework;
import com.zh.entity.Teacher;
import com.zh.service.HomeworkService;
import com.zh.service.Stu_HwkService;
import com.zh.service.StudentService;
import com.zh.utils.MyFileUtils;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
	@Resource
	private HomeworkService homeworkService;
	@Resource
	private Stu_HwkService stu_hwkService;
	@Resource
	private StudentService studentService;
	
/*	@RequestMapping(value="/homeworks")
	public String find(Model model,HttpSession session){
		Student student = (Student) session.getAttribute("student");
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		ClassAdmin classAdmin = student==null?teacher.getClassAdmin():student.getClassAdmin();
		DetachedCriteria criteria = DetachedCriteria.forClass(Homework.class);
		criteria.add(Restrictions.eq("classAdmin", classAdmin));
		model.addAttribute("homeworks",homeworkService.find(criteria));
		return "homework/homeworks";
	}*/
	
	@RequestMapping(value="/homeworks")
	public String find(Model model,String hql,HttpSession session){
		Student student = (Student) session.getAttribute("student");
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		ClassAdmin classAdmin = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Homework.class);
		if(student!=null){
			classAdmin = student.getClassAdmin(); 
			criteria.add(Restrictions.eq("classAdmin", classAdmin));
		}else if(teacher!=null){
			criteria.add(Restrictions.eq("creator", teacher));
			
		}
		model.addAttribute("homeworks",homeworkService.find(criteria));
		return "homework/homeworks";
	}
	
	
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(){
		
		return "homework/add";
	}
	
	public String add(Homework homework){
		

		homeworkService.save(homework);
		return null;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Homework homework,@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException{
		//Student author = (Student) request.getSession().getAttribute("student");
		Teacher creator = (Teacher) request.getSession().getAttribute("teacher");
		ClassAdmin classAdmin = creator.getClassAdmin();
		homework.setClassAdmin(classAdmin);
		
		homework.setCreator(creator);
		if(!file.isEmpty()){
			String folderName = homework.getClassAdmin().getClassId()+"\\attach";
			String attachName = this.attachName(homework,file.getOriginalFilename());
			String attach =this.upload(file, folderName,attachName ,request);
			
			 //request.getSession().getServletContext().getRealPath("/WEB-INF/upload/homework")+"\\"+folderName+"\\"+attachName;
			homework.setAttach(attach);
		
		}
		this.add(homework);
		return "redirect:/homework/homeworks";
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable int id,HttpSession session) {
		Homework homework = homeworkService.get(id);
		if(homework!=null){
			String attach = homework.getAttach();
			if(attach!=null&&!"".equals(attach))
				MyFileUtils.deleteFile(new File(attach));
			
			this.deleteFinished(homework, session);
			
			homeworkService.delete(homework);;
		}
		return "redirect:/homework/homeworks";
	}
	
	private void deleteFinished(Homework homework,HttpSession session){
		String realpath = session.getServletContext().getRealPath("/WEB-INF/upload/homework");
		String finishedFolder = homework.getClassAdmin().getClassId()+"\\finished\\"+homework.getId();
		File f = new File(realpath+"\\"+finishedFolder);
		MyFileUtils.deleteFile(f);
	}
	
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(Model model, @PathVariable int id) {
		model.addAttribute(homeworkService.get(id));
		return "homework/update";
	}

	public String update(Homework homework,HttpSession session) {

		homeworkService.update(homework);
		return "redirect:/homework/homeworks";
	}
	
	//update时自动填充字段
	@ModelAttribute  
	public void getHomework(@RequestParam(value="id",required=false)Integer id, Map<String,Object> map){
	    if (id != null) {
	        map.put("homework", homeworkService.get(id));
	    }

		
	}
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(Homework homework, @RequestParam("file")MultipartFile file,HttpServletRequest request){
		try {
			if(!file.isEmpty()){
				String folderName =  homework.getClassAdmin().getClassId()+"/attach";
				String attachName = this.attachName(homework,file.getOriginalFilename());
				String attach = this.upload(file, folderName,attachName,request);
				homework.setAttach(attach);
			}
			return this.update(homework, request.getSession());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	public String upload(MultipartFile file, String folder, String fileName, HttpServletRequest request) throws IOException {
		String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/homework");
		if(fileName==null||"".equals(fileName))fileName=file.getOriginalFilename();
		File f = new File(realpath + "\\" + folder);
		if(!f.exists()){
			f.mkdir();
		}
		f = new File(realpath + "\\" + folder + "\\" + fileName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), f);
	
		return f.getAbsolutePath();
	}
	
	@RequestMapping(value="/handIn")
	@ResponseBody
	public String handIn(int homeworkId,@RequestParam("file")MultipartFile file,HttpServletRequest request){
		Homework homework = homeworkService.get(homeworkId);
		Student student = (Student) request.getSession().getAttribute("student");
		Student_Homework  s_h = new Student_Homework(student,homework);
		try {
			String folderName = homework.getClassAdmin().getClassId()+"\\finished\\"+homework.getId();
			String path = this.upload(file, folderName ,this.homeworkName(student,homework,file.getOriginalFilename()), request);
			s_h.setPath(path);
			stu_hwkService.saveOrUpdate(s_h);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return "redirect:/homework/homeworks";
	}
	
	@RequestMapping(value="/download")
	public ResponseEntity<byte[]> download(String fileName, HttpServletRequest request) throws IOException {
		//String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/homework/attach");
		File file = new File(fileName);
		//if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);//从文件路径中截取文件名	
		fileName = URLEncoder.encode(fileName, "UTF-8");//解决大部分浏览器下载的文件名乱码 
		//} else {
			//fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//IE之外
		//}
		//fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");//解决下载的文件名乱码
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);//IE下需为OK，其他CREATE
	}
	
	//下载附件
	@RequestMapping(value="/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable int id,HttpServletRequest request) throws IOException{
		String fileName = homeworkService.get(id).getAttach();
		return this.download(fileName, request);
	}
	


	//收集作业
	@RequestMapping(value="/collect/{id}")
	public String collect(@PathVariable int id,Model model){
		Homework homework = homeworkService.get(id);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Student_Homework.class);
		criteria.add(Restrictions.eq("homework", homework));
		List<Student_Homework> stu_hwks = stu_hwkService.find(criteria);
		model.addAttribute("stu_hwks",stu_hwks);
		
		//统计欠交名单
		criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("classAdmin", homework.getClassAdmin()));
		for (Student_Homework student_Homework : stu_hwks) {
			
			criteria.add(Restrictions.ne("id", student_Homework.getStudent().getId()));
		}
		model.addAttribute("students",studentService.find(criteria));
		
		return "homework/collect";
	}
	
	public String statistics(){
		
		return null;
	}
	@RequestMapping("/{homeworkId}/downloadAll")
	public ResponseEntity<byte[]> downloadAll(String zipName,@PathVariable int homeworkId,HttpServletRequest request,HttpSession session){
		String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload/homework");
		Student student =(Student) session.getAttribute("student");
		uploadPath = uploadPath + "/" +student.getClassAdmin().getClassId() + "/finished/" + homeworkId;
		File folder = new File(uploadPath);
		File[] files = folder.listFiles();
		if(zipName==null) zipName= "all.zip";
		String path = uploadPath +"\\"+ zipName;
		try {
			MyFileUtils.packageZip(uploadPath, zipName, files);
			return download(path,request);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			new File(uploadPath,zipName).delete();
		}
		return null;
	}
	
	private String attachName(Homework homework, String sourceName){
		return homework.getTitle()+sourceName.substring(sourceName.lastIndexOf("."));
	}
	
	private String homeworkName(Student student,Homework homework,String sourceName){
		return student.getId()+"-"+student.getName()+"-"+homework.getTitle()+sourceName.substring(sourceName.lastIndexOf("."));
	}
/*	
	private String attachPath(){
		return null;
	}
	private String finishedPath(){
		
		return null;
	}*/
	
	/**
	 * 前端→后台，转换日期格式必备
	 * 或使用注解代替@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	 * 传入的字符串需与pattern格式一致
	 * @param bin
	 */
	@org.springframework.web.bind.annotation.InitBinder

    public void InitBinder(ServletRequestDataBinder bin) {

              bin.registerCustomEditor(Date.class, new CustomDateEditor(

                                new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));

    }

}

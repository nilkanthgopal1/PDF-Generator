package com.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.generatepdfreport1.GeneratePdfReport;
import com.model.Student;
import com.service.Controllerservice;
import com.serviceinterface.ServiceInterface;
@org.springframework.stereotype.Controller
public class Controller 
{
	@Autowired
	private ServiceInterface service;
	
	 @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_PDF_VALUE)
	 
	    public ResponseEntity<InputStreamResource> studentReport() throws IOException
	 {

		    System.out.println("Hello");
	        List<Student> student = (List<Student>) service.findAll();

	        ByteArrayInputStream bis = GeneratePdfReport.studentReport(student);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=studentreport.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	    }

	@RequestMapping("/reg")
	public String register(@ModelAttribute Student stu)
		{
			System.out.println("In Register");
			service.save(stu);
			System.out.println("Successfully registerd");
			return "login";
			
		}
	@RequestMapping("/regpage")
	public String registerpage()
	{
		System.out.println("In register page");
		return "register";
		
	}
	@RequestMapping("/")
	public String loginpage()
	{
		System.out.println("In Login");
		return "login";
		
	}
	@GetMapping("/log")
	public String login(@ModelAttribute Student stu, Model m)
	{
		System.out.println("\nusername : " +stu.getUsername());
		System.out.println("\npassword : " +stu.getPassword());
		Student stu1=service.login(stu.getUsername(), stu.getPassword());
		System.out.println(stu1);
		System.out.println(stu1!=null);
		if(stu1!=null)
			
		{
			List<Student> l=service.getallrecord();
			m.addAttribute("key", l);
			// System.out.println("After If Condition"+l);
			return "success";
		}
		else{
			
			return "login";
		}
		
		
}
	@RequestMapping("/edit")
	public String edit(Model m,@ModelAttribute Student stu,@RequestParam int id )
	{
	System.out.println(id);
		Student stu1=service.edit(id);
		m.addAttribute("key",stu1);
		return "edit";
		
	}
@RequestMapping("/update")
	public String update(Model m, @ModelAttribute Student stu)
	{
		service.save(stu);
		List<Student> l=service.getallrecord();
		m.addAttribute("key", l);
		return "success";
		
	}
@RequestMapping("/delete")
public String delete(Model m,@ModelAttribute Student stu,@RequestParam int id)
{
	System.out.println(id);
	service.delete(id);
	List<Student> l=service.getallrecord();
	m.addAttribute("key", l);
	return "success";
	
}
	
}

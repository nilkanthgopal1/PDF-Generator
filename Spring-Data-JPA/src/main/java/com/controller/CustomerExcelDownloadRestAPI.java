package com.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.model.Student;
import com.studentrepository.StudentRepository;
import com.util.ExcelGenerator;

@RestController
@RequestMapping("/api/student")
public class CustomerExcelDownloadRestAPI {
    @Autowired
    StudentRepository studentRepository;
 
    @GetMapping(value = "/download/student.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
        List<Student> students = (List<Student>) studentRepository.findAll();
		
		ByteArrayInputStream in = ExcelGenerator.studentsToExcel(students);
		// return IOUtils.toByteArray(in);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
		
		 return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(new InputStreamResource(in));
    }
}

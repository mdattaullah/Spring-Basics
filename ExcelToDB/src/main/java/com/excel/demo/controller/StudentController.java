package com.excel.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.demo.excel.UserExcelImporter;
import com.excel.demo.model.Student;
import com.excel.demo.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repository;
	
	@PostMapping("/save")
	public String save(@RequestParam("file") MultipartFile file) {
		repository.saveAll(new UserExcelImporter(file).excelImport());
		return "Data Saved";
	}
	
	@GetMapping("/get")
	public List<Student> getData(){
		return repository.findAll();
	}
}

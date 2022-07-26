package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.model.Student;
import com.serviceinterface.ServiceInterface;
import com.studentrepository.StudentRepository;
@Service
public class Controllerservice implements ServiceInterface
{
	@Autowired
	private StudentRepository repository;

	public void save(Student s) 
	{
	
		System.out.println("in service");
		System.out.println(s.getName());
		System.out.println(s.getUsername());
		System.out.println(s.getPassword());
		System.out.println("End Service");
		repository.save(s);
	}

	@Override
	public List<Student> getallrecord() {
		// TODO Auto-generated method stub
		return (List<Student>) repository.findAll();
	}

	@Override
	public Student login(String username, String password) {
		// TODO Auto-generated method stub
		return repository.findAllByUsernameAndPassword(username, password);
	}
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void update(Student stu)
	{
		
		repository.save(stu);
	}

	/*
	 * private Student findById(int id) { // TODO Auto-generated method stub return
	 * repository.findOne(id); }
	 */

	@Override
	public Student edit(int id)
	{
		// TODO Auto-generated method stub
		return repository.save(id);
	}

	@Override
	public List<Student> findAll() {
		List<Student> stu = (List<Student>) repository.findAll();
        System.out.println(stu);
        return stu;
	}
	

}

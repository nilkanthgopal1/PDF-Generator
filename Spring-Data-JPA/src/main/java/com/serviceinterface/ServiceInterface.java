package com.serviceinterface;


import java.util.List;

import com.model.Student;

public interface ServiceInterface 
{
	public void save(Student s);

	public List<Student> getallrecord();

	public Student login(String username, String password);
	
	
	public void update(Student stu);


	void delete(int id);

	public Student edit(int id);

	public List<Student> findAll();

	
}

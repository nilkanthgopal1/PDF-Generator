package com.studentrepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> 
{

	
	public Student findAllByUsernameAndPassword(String username,String password);


//public List<Student> getallrecord();


public Student save(int id);
}

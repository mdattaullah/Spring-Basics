package com.excel.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.demo.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}

package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Student;
import com.example.demo.repository.querydsl.StudentRepositoryCustom;

public interface StudentRepository extends CrudRepository<Student, Long>, StudentRepositoryCustom {

}

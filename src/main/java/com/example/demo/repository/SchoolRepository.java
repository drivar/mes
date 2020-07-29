package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.School;
import com.example.demo.repository.querydsl.SchoolRepositoryCustom;

public interface SchoolRepository extends CrudRepository<School, Long>, SchoolRepositoryCustom {

}

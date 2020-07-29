package com.example.demo.repository.querydsl;

import org.springframework.stereotype.Repository;

import com.example.demo.model.School;

@Repository
public interface SchoolRepositoryCustom {
	School querydslFindById(Long id);
}

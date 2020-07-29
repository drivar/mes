package com.example.demo.repository.querydsl.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.QSchool;
import com.example.demo.model.School;
import com.example.demo.repository.querydsl.SchoolRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SchoolRepositoryImpl implements SchoolRepositoryCustom{

	@Autowired
	private JPAQueryFactory queryFactory;
	
	@Override
	public School querydslFindById(Long id) {
		QSchool school = QSchool.school;
		JPAQuery<School> query= queryFactory.selectFrom(school).where(school.id.eq(id));
		return query.fetchOne();
	}
}

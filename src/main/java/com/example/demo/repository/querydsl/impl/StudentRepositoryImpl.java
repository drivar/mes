package com.example.demo.repository.querydsl.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.QSchool;
import com.example.demo.model.QStudent;
import com.example.demo.model.Student;
import com.example.demo.model.Ticket;
import com.example.demo.repository.querydsl.StudentRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class StudentRepositoryImpl implements StudentRepositoryCustom{

	@Autowired
	private JPAQueryFactory queryFactory;
	
	@Override
	public Student querydslFindById(Long id) {
		QStudent student = QStudent.student;
		JPAQuery<Student> query= queryFactory.select(student).from(student).where(student.id.eq(id));
		return query.fetchOne();
	}

	@Override
	public Iterable<Ticket> getAllTickets() {
		// schoolName => school
		// name, email => student
		QStudent student = QStudent.student;
		QSchool school = QSchool.school;
		
		JPAQuery<Ticket> query =
				queryFactory.select(
						Projections.fields(Ticket.class,
								school.name.as("schoolName"), 
								student.name.as("name"),
								student.email.as("email")))
				.from(student)
				.join(school).on(school.id.eq(student.school_id))
				.orderBy(school.name.asc())
				.orderBy(student.name.asc())
				;
				
		return query.fetch();
	}
}

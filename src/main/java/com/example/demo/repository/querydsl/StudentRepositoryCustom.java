package com.example.demo.repository.querydsl;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;
import com.example.demo.model.Ticket;

@Repository
public interface StudentRepositoryCustom {
	Student querydslFindById(Long id);
	Iterable<Ticket> getAllTickets();
}

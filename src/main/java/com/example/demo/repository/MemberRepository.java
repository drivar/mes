package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
	
	public Member findById(long objId);
	
	public Member findByName(String name);

}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.Student;
import com.example.demo.model.Ticket;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CompanyInfoService;

@ComponentScan
@RestController
@CrossOrigin
public class TestController {
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private CompanyInfoService comInfoRepo;
	
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String hello() {
		return "Hello, Boot!";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveNew")
	public Student saveNewStudent() {
		Student s = new Student(null, "yj", "a@sk.com", "test msg", null);
		return repo.save(s);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/showAll")
	public Iterable<Student> showAllStudent() {
		return repo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findAllMember")
	public Iterable<Member> findAllMember() {
		return memberRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveStudent/{name}/{mail}/{msg}")
	public Student saveNewStudent(@PathVariable("name") String name, @PathVariable("mail") String mail, @PathVariable String msg){
		System.out.println(name + mail + msg);
		return repo.save(new Student(null, name, mail, msg, null));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/show/{id}")
	public Student showStudentById(@PathVariable Long id) {
		return repo.querydslFindById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allTicket")
	public Iterable<Ticket> showAllTickets() {
		return repo.getAllTickets();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveMember")
	public String oneDirfromMember(String teamName, String name) {
		String sucessYn = comInfoRepo.saveMember(teamName,name);
		return sucessYn;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateMember")
	public String updateMember(long objId, String teamName, String name) {
		String sucessYn = comInfoRepo.UpdateMember(objId, teamName, name);
		return sucessYn;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findMember")
	public Member oneDirfromMemberGet() {
		Member member = comInfoRepo.findMember();
		return member;
	}
	//findMemberWithPara
	@RequestMapping(method = RequestMethod.GET, value = "/findMemberLazy")
	public String findMemberLazy() {
		String name = comInfoRepo.findMemberLazy();
		return name;
	}
	//
	@RequestMapping(method = RequestMethod.GET, value = "/findMemberWithParamLazy")
	public ResponseEntity<Member> findMemberWithParamLazy(String memberNm) {
		Member memberInfo = comInfoRepo.findMemberWithParamLazy(memberNm);
		return new ResponseEntity<Member>(memberInfo, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveEntitytest")
	public String saveEntitytest() {
		String sucessYn = comInfoRepo.useEntityManger();
		return sucessYn;
	}
	
//	@RequestMapping(method = RequestMethod.GET, value = "/findUsingBidirectionalMapping")
//	public String findUsingBidirectionalMapping() {
//		String sucessYn = comInfoRepo.findUsingBidirectionalMapping();
//		return sucessYn;
//	}
}

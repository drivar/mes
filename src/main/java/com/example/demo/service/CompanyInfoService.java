package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Entitytest;
import com.example.demo.model.Member;
import com.example.demo.model.Team;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TeamRepository;

@Service
public class CompanyInfoService {
	
	/** Swagger 주소 http://localhost:8080/swagger-ui.html */
	// Enumerate 도 실습 대상 포함 
	@Autowired
	TeamRepository teamRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	/**
	 *  @PersistenceContext = EntitiyManagerFactory emf = Persistence.createEntityManagerFactory() 와 동일
	 *  EntitiyManagerFactory emf = Persistence.createEntityManagerFactory()
	 */
	@PersistenceContext 
	private EntityManager em;
	
	/** 
	 *  Banana JPA
	 *  @Transactional =  EntityTransaction tx = em.getTransaction();,tx.begin(); , tx.commit 역할 을 다함 (즉 트랜잭션 시작, 롤백 역할등)
	 *  Spring Data JPA 에서는 Repository들이 이런 역할들을 한다. 
	 */
	@Transactional
	public String useEntityManger() {
		/**
		 * EntityTransaction tx = em.getTransaction();
		 * tx.begin();
		*/
		try {
			Entitytest et = new Entitytest();
			et.setName("hello test~!33");
			//em.persist(et);
			//em.find(Entitytest.class, 1L); /** 1차캐시 테스트 */
			//System.out.println("getmember.name = " + et.getName()); /** 1차캐시 테스트 */
		} catch (Exception e) {
			/** tx.rollback(); */
		} finally {
			em.close();
		}
		return "SUCESS";
	}
	
	/** 단방향 맵핑 (다대일) - 저장 */
	public String saveMember(String teamNm,String name){
	
		
		Team team = new Team();
		team.setName(teamNm);
		teamRepo.save(team);
		
		Member member = new Member();
		member.setName(name);
		member.setTeam(team);
		memberRepo.save(member);
		
		return "SUCESS";
	}
	
	
	/** 단방향 맵핑 (다대일) - 저장 */
	public String UpdateMember(long objId, String teamNm,String name){
	
		
		Team team = new Team();
		team.setName(teamNm);
		teamRepo.save(team);
		
		Member member = new Member();
		member.setName(name);
		member.setId(objId);
		member.setTeam(team);
		memberRepo.save(member);
		
		return "SUCESS";
	}
	
	/** 단방향 맵핑 - 조회  */
	public Member findMember(){
		/** 
		 * 해당 ID는 기본 getID 클래스를 가져오는 것이 아니라 
		 * findby 로 직접 지정하여 가져오는것 이다. 
		 * column에 선언한 Id로 가져오면 @Column(name="MEMBER_ID") 해준것 같이 자동으로 Id 가 MemberId와 맴핑하여 데이터를 가져온다.
		 * 두 테이블 조인 한방 쿼리 실행  
		 */
		long objId = 1;
		Member findMember = memberRepo.findById(objId); 
		return findMember;
	}
	

	/** 단방향 맵핑 - 조회 지연로딩  */
	public String findMemberLazy(){
		/** 
		 * 해당 ID는 기본 getID 클래스를 가져오는 것이 아니라 
		 * findby 로 직접 지정하여 가져오는것 이다. 
		 * column에 선언한 Id로 가져오면 @Column(name="MEMBER_ID") 해준것 같이 자동으로 Id 가 MemberId와 맴핑하여 데이터를 가져온다.
		 * 두 테이블 조인 한방 쿼리 실행  
		 */
		long objId = 1;
		Member findMember = memberRepo.findById(objId); 
		Team findTeam = findMember.getTeam();
		String name = findTeam.getName(); /** get 컬럼을 할때 team 테이블 조회 쿼리가 실행되면 지연로딩*/
		return name;
	}
	
	/** 단방향 맵핑 - 조회  */
	public Member findMemberWithParamLazy(String memberNm){
		/** 
		 * 해당 ID는 기본 getID 클래스를 가져오는 것이 아니라 
		 * findby 로 직접 지정하여 가져오는것 이다. 
		 * column에 선언한 Id로 가져오면 @Column(name="MEMBER_ID") 해준것 같이 자동으로 Id 가 MemberId와 맴핑하여 데이터를 가져온다.
		 * 두 테이블 조인 한방 쿼리 실행  
		 */
		Member findMember = memberRepo.findByName(memberNm); 
		Team findTeam = findMember.getTeam();
		String name = findTeam.getName(); /** get 컬럼을 할때 team 테이블 조회 쿼리가 실행되면 지연로딩*/
		return findMember;
	}

	/** 양방향 맵핑 - 조회  */
//	public String findUsingBidirectionalMapping() {
//		
//		long objId = 3;
//		Member findMember = memberRepo.findById(objId);
//		Team findTeam = findMember.getTeam();
//		
//		List<Member> members = findTeam.getMembers();
//		for (Member getmember : members) {
//			System.out.println("getmember.name = " + getmember.getName());
//		}
//		return "";
//	}

}

package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Member_Dongwoo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
public class Member implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	//@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MEMBER_ID")
	private long id;
	
	@Column(name="USERNAME", length = 50)
	private String name;
	
	/**단방향 설정*/
	//@ManyToOne/** 디폴트 즉시 로딩 (EAGER) */
	@ManyToOne(fetch = FetchType.EAGER) /** 지연로딩 */
	//@ManyToOne(fetch = FetchType.EAGER) /** 즉시로딩 */
	@JoinColumn(name = "TEAM_ID") 
	private Team team;
}

package com.seat_reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seat_reserve.entity.Member;
import com.seat_reserve.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	public List<Member>seachAll(){
		return  (List<Member>) memberRepository.findAll();
		
	}
}

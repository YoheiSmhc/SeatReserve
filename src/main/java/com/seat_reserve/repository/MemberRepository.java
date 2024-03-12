package com.seat_reserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seat_reserve.entity.Member;

/**
*メンバー情報 Repository
*/

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer>{
}

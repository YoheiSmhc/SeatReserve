package com.seat_reserve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seat_reserve.entity.User;

/**
*メンバー情報 Repository
*/

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	List<User>findAll();
	Optional<User> findById(Integer id);
}

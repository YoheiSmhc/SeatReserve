package com.seat_reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seat_reserve.entity.User;
import com.seat_reserve.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/*
	ユーザー情報を全検索
	*/
	public List<User>seachAll(){
		return  (List<User>) userRepository.findAll();
		
	}

	/*
	ユーザー情報をIdで検索
	*/
	public User findById(Integer id) {
        return  userRepository.findById(id).get();
    }
	

}

package com.seat_reserve.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seat_reserve.entity.Reserve;

/**
*メンバー情報 Repository
*/

@Repository
public interface ReserveRepository extends CrudRepository<Reserve,Integer>{
}


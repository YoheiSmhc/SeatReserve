package com.seat_reserve.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


	@Data
	@Entity
	@Table(name="reserve")
	public class Reserve {
		@Id
	    @Column(name = "reserveId")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer reserveID;
		
		@Column(name="userId")
		private Integer userId;
		
		@Column(name="seatId")
		private Integer seat1d;
		
		@Column(name = "reserveDate")
		private String reserveDate;
		
		
		}
	


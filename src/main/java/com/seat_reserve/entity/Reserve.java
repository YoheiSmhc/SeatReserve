package com.seat_reserve.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Data
@Entity
@Table(name = "reserve")
public class Reserve {
    @Id
    @Column(name = "reserve_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserveID;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "seat_id")
    private Integer seatId;
    
    @Column(name = "reserve_date")
    private String reserveDate;
    
    @Transient
    private String username;
    
}



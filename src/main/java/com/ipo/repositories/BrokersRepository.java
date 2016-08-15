package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Brokers;



public interface BrokersRepository extends PagingAndSortingRepository<Brokers, Long> {
	
	Brokers findBybrkCode(BigDecimal brkcode);
}

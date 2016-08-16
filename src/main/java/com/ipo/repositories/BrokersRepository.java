package com.ipo.repositories;

import java.math.BigDecimal;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Brokers;



public interface BrokersRepository extends PagingAndSortingRepository<Brokers, Long> {
	
	Brokers findBybrkCode(BigDecimal brkcode);
	
	@Query("select u from Brokers u where u.brkName like %?1%")
	Page<Brokers> findByBrkName(String brokername, Pageable pageable);

	
}

package com.ipo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Banks;

public interface BankRepository extends PagingAndSortingRepository<Banks, Long> {

	
	
}

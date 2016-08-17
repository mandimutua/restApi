package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.ipo.entities.Customers;

public interface CustomerRepository extends PagingAndSortingRepository<Customers, Long> {
	
	Customers findByCusPalCode(BigDecimal cusPalCode);

}

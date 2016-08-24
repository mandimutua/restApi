package com.ipo.repositories;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Customers;

public interface CustomerRepository extends PagingAndSortingRepository<Customers, Long> {
	
	Customers findByCusPalCode(BigDecimal cusPalCode);
	
	@Query("select u from Customers u where u.cusName like %?1%")
	Page<Customers> findByCusName(String customername, Pageable pageable);

	@Query("select u from Customers u where u.cusName like %?1%")
	List<Customers> findByCusName(String customername);
}

package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Application;
import com.ipo.entities.Batch;



public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {

	Application findByAppCode(BigDecimal appcode);
	
	BigDecimal countByAppBatCode(Batch batcode);
	
	@Query("select app from Application app ")
	Page<Application> findAll(Pageable pageable);
	
}

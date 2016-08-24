package com.ipo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Application;
import com.ipo.entities.Batch;



public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {

	Application findByAppCode(BigDecimal appcode);
	
	Page<Application> findByAppBatCode(Batch appBatCode, Pageable page);
	
	@Query("select a from Application a where a.appStatus = 1 and a.appBatCode = :appBatCode")
	List<Application> findBatchSize(@Param("appBatCode") Batch appBatCode);
	
	BigDecimal countByAppBatCode(Batch batcode);
	
	@Query("select app from Application app ")
	Page<Application> findAll(Pageable pageable);
	
}

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
import com.ipo.entities.Brokers;



public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {

	Application findByAppCode(BigDecimal appcode);
	
	Page<Application> findByAppBatCode(Batch appBatCode, Pageable page);
	
	@Query("select a from Application a where a.appStatus = 1 and a.appBatCode = :appBatCode")
	List<Application> findBatchSize(@Param("appBatCode") Batch appBatCode);
	
	BigDecimal countByAppBatCode(Batch batcode);
	
	@Query("select app from Application app ")
	Page<Application> findAll(Pageable pageable);
	
	
	@Query("select a from Application a  where a.appBatCode in(select b.batCode from Batch b where b.batBrkCode =:batBrkCode)")
	Page<Application> findSpecific(@Param("batBrkCode")Brokers bat, Pageable pageable);
	
	
//	select * from APPLICATION where APPLICATION.APP_CUS_PAL_CODE in(select CUSTOMERS.CUS_PAL_CODE FROM CUSTOMERS where CUSTOMERS.CUS_NAME like '%S%');
	
	@Query("select a from Application a  where a.appCusPalCode in(select b.cusPalCode from Customers b where b.cusName like %?1%)")
	Page<Application> findSpecificCus(String cus, Pageable pageable);
	
	
}

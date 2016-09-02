package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Batch;

import com.ipo.entities.Refunds;

public interface RefundsRepository extends PagingAndSortingRepository<Refunds, Long> {

	Refunds findByRfdCode(BigDecimal rfcode);
	
	
	@Query("select a from Refunds a where a.rfdAppCode in(select b.appCusPalCode from Application b where b.appBatCode =:appBatCode)")
	Page<Refunds> findSpecific(@Param("appBatCode")Batch bat, Pageable pageable);
	
}

package com.ipo.repositories;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Application;

import com.ipo.entities.Brokers;
import com.ipo.entities.Recieving;

public interface RecievingRepository extends PagingAndSortingRepository<Recieving, Long> {
	
	@Query("select a, b from Application a, Batch b  where b.batBrkCode =:batBrkCode and b.batNumber =:batNum and b.batCode=a.appBatCode)")
	Page<Application> findSpecificForRecieving(@Param("batBrkCode")Brokers bat, @Param("batNum")BigInteger batNum,Pageable pageable);

	Recieving findByRcvCode(BigDecimal rcvCode);
}

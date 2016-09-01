package com.ipo.repositories;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Payments;

public interface PaymentRepository extends PagingAndSortingRepository<Payments, Long> {

	Payments findByPayCode(BigDecimal brkcode);
	
	Page<Payments> findByPayAppCusPalCode(BigDecimal brkcode, Pageable page);
	
	@Query("select a from Payments a where a.payStatus = 1 and a.payAppCusPalCode = :cus_pal_code")
	List<Payments> findAmount(@Param("cus_pal_code") BigDecimal cus_pal_code);
	
}

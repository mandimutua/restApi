package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Brokers;



public interface BrokersRepository extends PagingAndSortingRepository<Brokers, Long> {
	
	Brokers findBybrkCode(BigDecimal brkcode);
	
	@Query("select u from Brokers u where u.brkName like %?1%")
	Page<Brokers> findByBrkName(String brokername, Pageable pageable);
	
	
//select PAY_AMOUNT from PAYMENTS where PAY_APP_CUS_PAL_CODE in(select APP_CUS_PAL_CODE FROM APPLICATION WHERE APP_BAT_CODE in(select BAT_CODE FROM BATCH WHERE BAT_BRK_CODE  in(select BRK_CODE FROM BROKERS WHERE BRK_CODE ='101')));

	@Query("select a from Payments a where a.payAppCusPalCode in (select b.appCusPalCode from Application b where b.appBatCode in (select c.batCode from Batch c where c.batBrkCode in (select d.brkCode from Brokers d where d.brkCode =:BrkCode)))")
	Page<Brokers> findForReports(@Param("BrkCode")BigDecimal bat, Pageable pageable);


	
}

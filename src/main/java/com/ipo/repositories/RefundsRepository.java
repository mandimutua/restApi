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
	
	
	
//select * from refunds where RFD_PAY_CODE in(select pay_code from payments where PAY_APP_CUS_PAL_CODE in(select app_cus_pal_code from application where APP_CUS_PAL_CODE in(select cus_pal_code from customers where cus_name like 'RICH%')));

@Query("select a from Refunds a where a.rfdPayCode in(select b.payCode from Payments b where b.payAppCusPalCode in(select c.appCusPalCode from Application c where c.appCusPalCode in(select d.cusPalCode from Customers d where d.cusSurname like %?1%)))")	
Page<Refunds> findRefund(String bat, Pageable pageable);
	
}

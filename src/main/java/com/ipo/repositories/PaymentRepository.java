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
import com.ipo.entities.Customers;
import com.ipo.entities.Payments;

public interface PaymentRepository extends PagingAndSortingRepository<Payments, Long> {

	Payments findByPayCode(BigDecimal brkcode);
	
	@Query("select a from Payments a where a.payAppCusPalCode = :payAppCusPalCode")
	Page<Payments> findPayment(@Param("payAppCusPalCode") Application brkcode, Pageable page);
	
	@Query("select a from Payments a where a.payStatus = 1 and a.payAppCusPalCode = :cus_pal_code")
	List<Payments> findAmount(@Param("cus_pal_code") BigDecimal cus_pal_code);
	
	//select * from PAYMENTS where PAYMENTS.PAY_APP_CUS_PAL_CODE in(select CUSTOMERS.CUS_PAL_CODE FROM CUSTOMERS where CUSTOMERS.CUS_NAME like '%Gle%');

	@Query("select a from Payments a  where a.payAppCusPalCode in(select b.cusPalCode from Customers b where b.cusName like %?1%)")
	Page<Payments> findSpecificPay(String cus, Pageable pageable);
	
	@Query("select a from Payments a where a.payAppCusPalCode in(select b.appCusPalCode from Application b where b.appBatCode =:appBatCode)")
	Page<Payments> findSpecific(@Param("appBatCode")Batch bat, Pageable pageable);
}

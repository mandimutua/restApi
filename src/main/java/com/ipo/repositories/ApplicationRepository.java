package com.ipo.repositories;

import java.math.BigDecimal;
import java.math.BigInteger;
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
	
	//Select application.*, payments.pay_amount from application, PAYMENTS where application.APP_CUS_PAL_CODE in(select application.APP_CUS_PAL_CODE from application where application.APP_BAT_CODE=182);
	//Select application.*, payments.pay_amount from APPLICATION, PAYMENTS where PAYMENTS.PAY_APP_CUS_PAL_CODE = application.APP_CUS_PAL_CODE and APPLICATION.APP_BAT_CODE=13;
	@Query("select a, b from Application a, Payments b where b.payAppCusPalCode = a.appCusPalCode and a.appBatCode =:batCode)")
	Page<Application> findByAppBatCode(@Param("batCode")Batch bat, Pageable page);
	
	@Query("select a from Application a where a.appStatus = 2 and a.appBatCode = :appBatCode")
	List<Application> findBatchSize(@Param("appBatCode") Batch appBatCode);
	
	@Query("select a from Application a where a.appBatCode = :appBatCode")
	List<Application> findBatchSizeForReports(@Param("appBatCode") Batch appBatCode);
	
	BigDecimal countByAppBatCode(Batch batcode);
	
	@Query("select app from Application app ")
	Page<Application> findAll(Pageable pageable);
	
	
	@Query("select a from Application a  where a.appBatCode in(select b.batCode from Batch b where b.batBrkCode =:batBrkCode)")
	Page<Application> findSpecific(@Param("batBrkCode")Brokers bat, Pageable pageable);
	
	
	@Query("select a, b from Application a, Payments b  where a.appCusPalCode=b.payAppCusPalCode and a.appBatCode in(select b.batCode from Batch b where b.batBrkCode =:batBrkCode and b.batNumber =:batNum)")
	Page<Application> findSpecificForReports(@Param("batBrkCode")Brokers bat, @Param("batNum")BigInteger batNum,Pageable pageable);
	
	@Query("select a from Application a  where a.appBatCode in(select b.batCode from Batch b where b.batBrkCode =:batBrkCode and b.batNumber =:batNum)")
	Application findSpecificForReportsUnpaginated(@Param("batBrkCode")Brokers bat, @Param("batNum")BigInteger batNum);
	
//SELECT SUM (PAY_AMOUNT) FROM PAYMENTS WHERE (PAYMENTS.PAY_APP_CUS_PAL_CODE IN(SELECT APPLICATION.APP_CUS_PAL_CODE FROM APPLICATION WHERE APP_BAT_CODE ='13'));	
	@Query("select a.payAmount from Payments a  where a.payAppCusPalCode in(select b.appCusPalCode from Application b where b.appBatCode =:batNum))")
	Page<Application> findTotalAmountInBatch(@Param("batNum")BigInteger batNum,Pageable pageable);
	
	
//	select * from APPLICATION where APPLICATION.APP_CUS_PAL_CODE in(select CUSTOMERS.CUS_PAL_CODE FROM CUSTOMERS where CUSTOMERS.CUS_NAME like '%S%');
	
	@Query("select a from Application a  where a.appCusPalCode in(select b.cusPalCode from Customers b where b.cusSurname like %?1%)")
	Page<Application> findSpecificCus(String cus, Pageable pageable);
	
	@Query("select b from Application b where b.appStatus =1 order by b.appCode DESC")
	List<Application> findAllByAppCode();
	
	
}

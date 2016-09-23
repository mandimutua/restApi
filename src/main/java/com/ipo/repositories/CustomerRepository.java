package com.ipo.repositories;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Brokers;
import com.ipo.entities.Customers;

public interface CustomerRepository extends PagingAndSortingRepository<Customers, Long> {
	
	Customers findByCusPalCode(BigDecimal cusPalCode);
	
	//@Query("select u from Customers u where u.cusName like %?1% and cusInputter=43")
	@Query("select u from Customers u where u.cusFormSerialNo =:cusSerialNo")
	Page<Customers> findByCusSerialNo(@Param("cusSerialNo")BigInteger cusSerialNo, Pageable pageable);
	
	@Query("select a from Customers a  where a.cusBrkCode =:batBrkCode)")
	Page<Customers> findSpecific(@Param("batBrkCode")Brokers bat, Pageable pageable);

	@Query("select u from Customers u where u.cusSurname like %?1%" )
	List<Customers> findByCusSurname(String customername);
	
	
}

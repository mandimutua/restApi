package com.ipo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Batch;
import com.ipo.entities.Brokers;




public interface BatchRepository extends PagingAndSortingRepository<Batch, Long> {
	
	Page<Batch> findByBatCode(BigDecimal batcode, Pageable pageable);
	
	@Query("select a from Batch a  where a.batBrkCode =:batBrkCode)")
	Page<Batch> findSpecific(@Param("batBrkCode")Brokers bat, Pageable pageable);
	
	Batch findByBatCode(BigDecimal batcode);
	
	//select u from Customers u where u.cusName like %?1%"
	@Query("select b from Batch b where b.batStatus =2 order by b.batCode DESC")
	List<Batch> findAllByOrderByBatCodeDesc();
	
	@Query("select b from Batch b where b.batStatus =2 and b.batBrkCode =:batBrkCode order by b.batCode DESC)")
	List<Batch> findSpecificBatch(@Param("batBrkCode")Brokers bat);
	

}

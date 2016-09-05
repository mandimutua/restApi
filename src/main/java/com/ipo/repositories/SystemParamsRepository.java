package com.ipo.repositories;

import java.math.BigDecimal;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.ipo.entities.SystemParameters;

public interface SystemParamsRepository extends PagingAndSortingRepository<SystemParameters, Long>{

	SystemParameters findByParamCode(BigDecimal paramCode);
	
	SystemParameters findByParamName(String name);
	
	@Query("select u from SystemParameters u where u.paramName like %?1%")
	Page<SystemParameters> findSpecif(String paramName, Pageable page);
}

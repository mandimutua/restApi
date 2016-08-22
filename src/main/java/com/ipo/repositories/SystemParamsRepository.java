package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.ipo.entities.SystemParameters;

public interface SystemParamsRepository extends PagingAndSortingRepository<SystemParameters, Long>{

	SystemParameters findByParamCode(BigDecimal paramCode);
	
}

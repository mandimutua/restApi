package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Refunds;

public interface RefundsRepository extends PagingAndSortingRepository<Refunds, Long> {

	Refunds findByRfdCode(BigDecimal rfcode);
}

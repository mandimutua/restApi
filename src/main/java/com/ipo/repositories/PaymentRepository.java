package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Payments;

public interface PaymentRepository extends PagingAndSortingRepository<Payments, Long> {

	Payments findByPayCode(BigDecimal brkcode);
}

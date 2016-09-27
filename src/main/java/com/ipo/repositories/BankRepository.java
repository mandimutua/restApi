package com.ipo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Banks;

public interface BankRepository extends PagingAndSortingRepository<Banks, Long> {
	
	@Query("select a.bankBranch from Banks a where a.bankName like %?1%" )
	List<Banks> findByBankName(String bankName);

	
	
}

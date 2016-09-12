package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Permissions;

public interface PermissionRepository extends PagingAndSortingRepository<com.ipo.entities.Permissions, Long> {

	Permissions findByPermCode(BigDecimal pemCode);
}

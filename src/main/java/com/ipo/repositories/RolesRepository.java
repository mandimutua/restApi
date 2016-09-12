package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Roles;

public interface RolesRepository extends PagingAndSortingRepository<Roles, Long> {

	Roles findByRoleCode(BigDecimal role);
}

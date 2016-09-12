package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.ipo.entities.Brokers;
import com.ipo.entities.Users;


public interface UsersRepository extends PagingAndSortingRepository<Users, Long> {
	
	Page<Users> findAll();
	Users findByusrNameIgnoreCase(String username);
	Users findByusrEmailIgnoreCase(String useremail);
	Users findByusrCode(BigDecimal usercode);
	
	@Query("select a from Users a  where a.usrBrkCode =:batBrkCode)")
	Page<Users> findSpecific(@Param("batBrkCode")Brokers bat, Pageable pageable);
}

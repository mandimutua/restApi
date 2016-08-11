package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.ipo.entities.Users;


public interface UsersRepository extends PagingAndSortingRepository<Users, Long> {
	Page<Users> findAll();
	Users findByusrNameIgnoreCase(String username);
	Users findByusrEmailIgnoreCase(String useremail);
	Users findByusrCode(BigDecimal usercode);
}

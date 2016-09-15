package com.ipo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Roles;
import com.ipo.entities.Users;

public interface RolesRepository extends PagingAndSortingRepository<Roles, Long> {

	Roles findByRoleCode(BigDecimal role);
	
//	@Query("select b from Roles b where b.roleStatus =1 order by b.roleCode DESC")

	@Query("select b from Roles b order by b.roleCode DESC")
	Page<Roles> findAllByRoleCode(Pageable page);
	
	@Query("select b from Roles b order by b.roleCode DESC")
	List<Roles>findAllRoleCodes();
	
	//SELECT * FROM ROLES WHERE ROLE_CODE NOT IN(SELECT USROLE_ROLE_CODE FROM USER_ROLES WHERE USROLE_USR_CODE=43);
	@Query("select a from Roles a  where a.roleCode not in(select b.usroleRoleCode from UserRoles b where b.usroleUsrCode =:userCode)and a.roleStatus=1")
	Page<Roles> findSpecific(@Param("userCode")Users userCode, Pageable pageable);
	
	
}

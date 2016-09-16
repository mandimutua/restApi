package com.ipo.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ipo.entities.Roles;
import com.ipo.entities.UserRoles;
import com.ipo.entities.Users;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRoles, Long> {

	UserRoles findByUsroleCode(BigDecimal usrRoleCode);
	
	@Query("select r.roleName, u.usroleStatus,r.roleCode from Roles r, UserRoles u where r.roleCode = u.usroleRoleCode and u.usroleUsrCode =:userCode")
    Page<UserRoles> findSpecific(@Param("userCode")Users bat, Pageable pageable);

	
	//DELETE USER_ROLES where USROLE_USR_CODE='66' and USROLE_ROLE_CODE ='21'
	@Query("select a from UserRoles a where a.usroleUsrCode =:userCode and a.usroleRoleCode =:roleCode")
	UserRoles findForDeletion(@Param("userCode")Users bat,@Param("roleCode")Roles bats);
	
	
	Page<UserRoles> findByUsroleUsrCode(Users role, Pageable page);
	
	
	UserRoles findByUsroleUsrCode(Users role);
}

package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Roles;
import com.ipo.entities.UserRoles;
import com.ipo.repositories.RolesRepository;

@Service
public class RolesService {

	@Autowired
	private RolesRepository rolesRepository;

	public RestResponseObject create(Roles req) {
		Roles rol = new Roles();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating Role");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			rol.setRoleName(req.getRoleName());
			rol.setRoleDesc(req.getRoleDesc());
			rol.setRoleDate(Calendar.getInstance().getTime());
			rol.setRoleInputter(req.getRoleInputter());
			rol.setRoleStatus(BigInteger.valueOf(2));
			rol.setRoleCdate(Calendar.getInstance().getTime());

			Roles createdbatch = rolesRepository.save(rol);
			resp.setMessage("Success");
			resp.setPayload(createdbatch);
			resp.setRequestStatus(true);
		} catch (Exception er) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(er.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseObject edit(Roles req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Role");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Roles role = rolesRepository.findByRoleCode(req.getRoleCode());
		try {

			if (role == null) {
				resp.setMessage("Role not found");
				resp.setRequestStatus(true);
			} else {
				// check batch status
				if (role.getRoleStatus() == BigInteger.valueOf(2)) {
					role.setRoleName(req.getRoleName());
					role.setRoleDesc(req.getRoleDesc());
					role.setRoleDate(Calendar.getInstance().getTime());
					role.setRoleInputter(req.getRoleInputter());
					role.setRoleStatus(BigInteger.valueOf(2));
					role.setRoleCdate(Calendar.getInstance().getTime());

					resp.setMessage("Role Edit Successfull");
					Roles createdbroker = rolesRepository.save(role);
					resp.setPayload(createdbroker);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Role is Inactive");
					resp.setRequestStatus(true);
				}

			}

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject listRoles(Roles rol, Pageable page) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);

		try {
			resp.setRequestStatus(true);
			resp.setPayload(rolesRepository.findAllByRoleCode(page));
			resp.setMessage("Roles found");

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			resp.setRequestStatus(true);
			System.err.println(e.toString());
		}

		return resp;
	}

	public RestResponseObject listUserRoles(UserRoles usr, Pageable page) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);
		Page<Roles> cust = rolesRepository.findSpecific(usr.getUsroleUsrCode(), page);
		System.out.println("User Code being sent===========" + usr.getUsroleUsrCode().toString());

		if (cust == null) {
			resp.setRequestStatus(true);
			resp.setPayload(cust);
			resp.setMessage("Roles not found");
		} else {
			resp.setRequestStatus(true);
			resp.setPayload(cust);
			resp.setMessage("Success");
		}

		return resp;
	}

	public RestResponseObject approve(RestRequestObject<Roles[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Role");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Roles r : req.getObject()) {
				Roles bth = rolesRepository.findByRoleCode(r.getRoleCode());

				if (bth == null) {
					resp.setMessage("Role not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (bth.getRoleStatus() == BigInteger.valueOf(2)) {

						bth.setRoleDate(Calendar.getInstance().getTime());
						bth.setRoleAuthoriser(r.getRoleAuthoriser());
						System.out.println("Role Authorizer================" + r.getRoleAuthoriser());
						bth.setRoleMdate((Calendar.getInstance().getTime()));
						bth.setRoleStatus(BigInteger.valueOf(1));
						resp.setMessage("Role Approval Successfull");
						Roles createdbatch = rolesRepository.save(bth);
						resp.setPayload(createdbatch);
						resp.setRequestStatus(true);
					}

					else {
						resp.setMessage("Role is not set for approval");
						resp.setRequestStatus(true);
					}

				}
			}
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject reject(RestRequestObject<Roles[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Role");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Roles r : req.getObject()) {
				Roles bth = rolesRepository.findByRoleCode(r.getRoleCode());

				if (bth == null) {
					resp.setMessage("Role not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (bth.getRoleStatus() == BigInteger.valueOf(2)) {

						bth.setRoleDate(Calendar.getInstance().getTime());
						bth.setRoleAuthoriser(r.getRoleAuthoriser());
						bth.setRoleMdate((Calendar.getInstance().getTime()));
						bth.setRoleStatus(BigInteger.valueOf(3));
						resp.setMessage("Role Rejection Successfull");
						Roles createdbatch = rolesRepository.save(bth);
						resp.setPayload(createdbatch);
						resp.setRequestStatus(true);
					}

					else {
						resp.setMessage("Role is not set for approval");
						resp.setRequestStatus(true);
					}

				}
			}
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject listAllRoles(Roles rol) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);

		try {
			resp.setRequestStatus(true);
			List<Roles> rols = rolesRepository.findAllRoleCodes();
			resp.setPayload(rols);
			resp.setMessage("Roles found");

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			resp.setRequestStatus(true);
			System.err.println(e.toString());
		}

		return resp;
	}
	
}

package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.UserRoles;
import com.ipo.repositories.UserRoleRepository;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	public RestResponseObject create(RestRequestObject<UserRoles[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating User Roles");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (UserRoles r : req.getObject()) {
		
			try {
				UserRoles app = new UserRoles();
				app.setUsroleCdate(Calendar.getInstance().getTime());
				app.setUsroleDate(Calendar.getInstance().getTime());
				app.setUsroleInputter(r.getUsroleInputter());
				app.setUsroleStatus(BigInteger.valueOf(2));
				app.setUsroleRoleCode(r.getUsroleRoleCode());
				app.setUsroleUsrCode(r.getUsroleUsrCode());
				app.setUsroleRoleCode(r.getUsroleRoleCode());
				UserRoles createdParam = userRoleRepository.save(app);
				resp.setMessage("Success");
				resp.setPayload(createdParam);
				resp.setRequestStatus(true);

			} catch (Exception er) {
				resp.setMessage("Server Error. Please try again later.");
				System.err.println(er.toString());
				resp.setRequestStatus(true);
			}
		}
		return resp;
	}

	public RestResponseObject revoke(RestRequestObject<UserRoles[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Revoking User Roles");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (UserRoles r : req.getObject()) {

			try {

				UserRoles app = userRoleRepository.findForDeletion(r.getUsroleUsrCode(), r.getUsroleRoleCode());
				userRoleRepository.delete(app);
				resp.setMessage("Role Removal Success");
				resp.setRequestStatus(true);

			} catch (Exception er) {
				resp.setMessage("Server Error. Please try again later.");
				System.err.println(er.toString());
				resp.setRequestStatus(true);
			}
		}
		return resp;
	}

	public RestResponseObject edit(UserRoles req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		UserRoles app = userRoleRepository.findByUsroleCode(req.getUsroleCode());

		if (app == null) {
			resp.setMessage("System Param not found");
			resp.setRequestStatus(true);
		} else {

			try {
				if (app.getUsroleStatus() == BigInteger.valueOf(2)) {
					app.setUsroleDate(Calendar.getInstance().getTime());
					app.setUsroleInputter(req.getUsroleInputter());
					app.setUsroleStatus(BigInteger.valueOf(2));
					app.setUsroleRoleCode(req.getUsroleRoleCode());
					app.setUsroleCode(req.getUsroleCode());
					UserRoles createdParam = userRoleRepository.save(app);
					resp.setPayload(createdParam);
					resp.setRequestStatus(true);
					resp.setMessage("UserRole Edit Successfull");

				} else {
					resp.setMessage("UserRole is Inactive");
					resp.setRequestStatus(true);
				}
			} catch (Exception e) {
				resp.setMessage("Server Error. Please try again later.");
				System.err.println(e.toString());
				resp.setRequestStatus(true);
			}

		}

		return resp;
	}

	public RestResponseObject listRoles(UserRoles usr, Pageable page) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);
		System.out.println("User role being sent"+usr.getUsroleUsrCode().toString());
		Page<UserRoles> cust = userRoleRepository.findSpecific(usr.getUsroleUsrCode(), page);
		
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

	public RestResponseObject approve(RestRequestObject<UserRoles[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving User Roles");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			for (UserRoles r : req.getObject()) {
				UserRoles app = userRoleRepository.findByUsroleCode(r.getUsroleCode());

				if (app == null) {
					resp.setMessage("User Role not found");
					resp.setRequestStatus(true);
				} else {

					if (app.getUsroleStatus() == BigInteger.valueOf(2)) {

						app.setUsroleMdate(Calendar.getInstance().getTime());
						app.setUsroleStatus(BigInteger.valueOf(1));
						app.setUsroleAuthoriser(r.getUsroleAuthoriser());
						app.setUsroleMdate(Calendar.getInstance().getTime());
						resp.setMessage("User Roles Approval Successfull");
						UserRoles approvedRole = userRoleRepository.save(app);
						resp.setPayload(approvedRole);
						resp.setRequestStatus(true);

					} else {
						resp.setMessage("System Param is not set for approval");
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

	public RestResponseObject reject(RestRequestObject<UserRoles[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving User Roles");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			for (UserRoles r : req.getObject()) {
				UserRoles app = userRoleRepository.findByUsroleUsrCode(r.getUsroleUsrCode());

				if (app == null) {
					resp.setMessage("User Role not found");
					resp.setRequestStatus(true);
				} else {
					app.setUsroleMdate(Calendar.getInstance().getTime());
					app.setUsroleStatus(BigInteger.valueOf(1));
					app.setUsroleAuthoriser(r.getUsroleAuthoriser());
					app.setUsroleMdate(Calendar.getInstance().getTime());
					resp.setMessage("User Roles Rejection Successfull");
					UserRoles approvedRole = userRoleRepository.save(app);
					resp.setPayload(approvedRole);
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

}

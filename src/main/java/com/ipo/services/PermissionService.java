package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Permissions;
import com.ipo.repositories.PermissionRepository;

@Service
public class PermissionService {
	@Autowired
	private PermissionRepository pemRepo;

	public RestResponseObject create(Permissions pem) {
		Permissions bth = new Permissions();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating permission");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			bth.setPermCdate(Calendar.getInstance().getTime());
			bth.setPermDate(Calendar.getInstance().getTime());
			bth.setPermStatus(BigInteger.valueOf(2));
			bth.setPermInputter(pem.getPermInputter());
			bth.setPermName(pem.getPermName());
			bth.setPermDesc(pem.getPermDesc());
			bth.setPermRoleCode(pem.getPermRoleCode());

			Permissions createdpem = pemRepo.save(bth);
			resp.setMessage("Success");
			resp.setPayload(createdpem);
			resp.setRequestStatus(true);
		} catch (Exception er) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(er.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseObject edit(Permissions pem) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Permission");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Permissions bth = pemRepo.findByPermCode(pem.getPermCode());

		try {

			if (bth == null) {
				resp.setMessage("Permissions not found");
				resp.setRequestStatus(true);
			} else {
				// check batch status
				if (bth.getPermStatus() == BigInteger.valueOf(2)) {

					bth.setPermDate(Calendar.getInstance().getTime());
					bth.setPermDesc(pem.getPermDesc());
					bth.setPermInputter(pem.getPermInputter());
					bth.setPermStatus(BigInteger.valueOf(2));
					bth.setPermName(pem.getPermName());
					bth.setPermRoleCode(pem.getPermRoleCode());
					resp.setMessage("Permission Edit Successfull");
					Permissions createdbroker = pemRepo.save(bth);
					resp.setPayload(createdbroker);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Permission is Inactive");
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

	public RestResponseObject approve(RestRequestObject<Permissions[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Permissions");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Permissions r : req.getObject()) {
				Permissions bth = pemRepo.findByPermCode(r.getPermCode());

				if (bth == null) {
					resp.setMessage("Permissions not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (bth.getPermStatus() == BigInteger.valueOf(2)) {

						{
							bth.setPermMdate(Calendar.getInstance().getTime());
							bth.setPermAuthoriser(r.getPermAuthoriser());
							bth.setPermDate(Calendar.getInstance().getTime());
							bth.setPermStatus(BigInteger.valueOf(1));
							resp.setMessage("Permissions Approval Successfull");
							Permissions createdpem = pemRepo.save(bth);
							resp.setPayload(createdpem);
							resp.setRequestStatus(true);
						}

					} else {
						resp.setMessage("Permission is not set for approval");
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

	public RestResponseObject reject(RestRequestObject<Permissions[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Permissions");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Permissions r : req.getObject()) {
				Permissions bth = pemRepo.findByPermCode(r.getPermCode());

				if (bth == null) {
					resp.setMessage("Permissions not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (bth.getPermStatus() == BigInteger.valueOf(2)) {

						{
							bth.setPermMdate(Calendar.getInstance().getTime());
							bth.setPermAuthoriser(r.getPermAuthoriser());
							bth.setPermDate(Calendar.getInstance().getTime());
							bth.setPermStatus(BigInteger.valueOf(3));
							resp.setMessage("Permissions Rejection Successfull");
							Permissions createdpem = pemRepo.save(bth);
							resp.setPayload(createdpem);
							resp.setRequestStatus(true);
						}

					} else {
						resp.setMessage("Permission is not set for rejection");
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

	public RestResponseObject listall(Permissions pem, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			resp.setPayload(pemRepo.findAll(pageable));
			resp.setRequestStatus(true);
			resp.setMessage("Success");

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			resp.setRequestStatus(true);
			System.err.println(e.toString());
		}
		return resp;
	}

}

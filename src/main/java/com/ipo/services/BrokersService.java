package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Brokers;
import com.ipo.repositories.BrokersRepository;

@Service
public class BrokersService {

	@Autowired
	private BrokersRepository brokersRepository;

	public RestResponseObject listall(Brokers broker, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			resp.setPayload(brokersRepository.findAll(pageable));
			resp.setRequestStatus(true);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());;
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject list(Brokers broker) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			resp.setPayload(brokersRepository.findAll());
			resp.setRequestStatus(true);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject search(Brokers broker, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Page<Brokers> brk = brokersRepository.findByBrkName(broker.getBrkName(), (pageable));

		try {

			if (brk == null) {
				resp.setMessage("Broker not found");
				resp.setRequestStatus(true);
			} else {
				if (!brk.hasContent()) {
					resp.setPayload(brk);
					resp.setRequestStatus(true);
					resp.setMessage("Broker not found");
				} else {
					resp.setPayload(brk);
					resp.setRequestStatus(true);
					resp.setMessage("Success");
				}

			}

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject create(Brokers req) {
		Brokers brk = new Brokers();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating broker");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		System.out.println(("Printed BRK_INPUTTER" + req));
		try {
			brk.setBrkName(req.getBrkName());
			brk.setBrkAddress(req.getBrkAddress());
			brk.setBrkTown(req.getBrkTown());
			brk.setBrkPhone(req.getBrkPhone());
			brk.setBrkEmail(req.getBrkEmail());
			brk.setBrkStatus(BigInteger.valueOf(2));
			brk.setBrkID(req.getBrkID());
			brk.setBrkInputter(req.getBrkInputter());
			brk.setBrkCdate(Calendar.getInstance().getTime());
			brk.setBrkDate(Calendar.getInstance().getTime());
			Brokers createdbroker = brokersRepository.save(brk);
			resp.setMessage("Success");
			resp.setPayload(createdbroker);
			resp.setRequestStatus(true);
		} catch (Exception er) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(er.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject edit(Brokers req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating broker");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Brokers brk = brokersRepository.findBybrkCode(req.getBrkCode());

		try {

			if (brk == null) {
				resp.setMessage("User not found");
				resp.setRequestStatus(true);
			} else {
				// check if broker is active
				if (brk.getBrkStatus() == BigInteger.valueOf(1) | brk.getBrkStatus() == BigInteger.valueOf(2)) {
					brk.setBrkName(req.getBrkName());
					brk.setBrkStatus(BigInteger.valueOf(2));
					brk.setBrkAddress(req.getBrkAddress());
					brk.setBrkTown(req.getBrkTown());
					brk.setBrkPhone(req.getBrkPhone());
					brk.setBrkEmail(req.getBrkEmail());
					brk.setBrkStatus(req.getBrkStatus());
					brk.setBrkMdate(Calendar.getInstance().getTime());
					brk.setBrkDate(Calendar.getInstance().getTime());
					brk.setBrkAuthoriser(req.getBrkInputter());
					resp.setMessage("Broker Edit Successfull");
					Brokers createdbroker = brokersRepository.save(brk);
					resp.setPayload(createdbroker);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Broker is Inactive");
				}

			}
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}

		return resp;
	}

	public RestResponseObject approve(RestRequestObject<Brokers[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (Brokers r : req.getObject()) {
			System.out.println("Length of Object" + req.getObject().length);

			Brokers brk = brokersRepository.findBybrkCode(r.getBrkCode());
			if (brk == null) {
				resp.setMessage("Broker not found");
				resp.setRequestStatus(true);
			} else {
				if (brk.getBrkStatus() == BigInteger.valueOf(2)) {
					try {
						brk.setBrkStatus((BigInteger.valueOf(1)));
						brk.setBrkAuthoriser((r.getBrkInputter()));
						brk.setBrkMdate(Calendar.getInstance().getTime());
						resp.setMessage("Broker Approval Successfull");
						resp.setRequestStatus(true);
						brokersRepository.save(brk);

					} catch (Exception e) {

						resp.setMessage("Server Error. Please try again later.");
						System.err.println(e.toString());
						resp.setRequestStatus(true);
					}
				} else {
					resp.setMessage("Broker has not been marked for approval");
				}
			}
		}

		return resp;

	}

	public RestResponseObject reject(RestRequestObject<Brokers[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (Brokers r : req.getObject()) {

			Brokers brk = brokersRepository.findBybrkCode(r.getBrkCode());
			if (brk == null) {
				resp.setMessage("Broker not found");
				resp.setRequestStatus(true);
			} else {
				if (brk.getBrkStatus() == BigInteger.valueOf(2)) {
					try {
						brk.setBrkStatus((BigInteger.valueOf(3)));
						brk.setBrkAuthoriser((r.getBrkInputter()));
						brk.setBrkMdate(Calendar.getInstance().getTime());
						resp.setMessage("Broker Rejection Successfull");
						resp.setRequestStatus(true);
						brokersRepository.save(brk);

					} catch (Exception e) {
						resp.setMessage("Server Error. Please try again later.");
						System.err.println(e.toString());
						resp.setRequestStatus(true);
					}
				} else {
					resp.setMessage("Broker has not been marked for approval");
				}
			}
		}

		return resp;

	}
	
	public RestResponseObject reportBrokers(Brokers brk, Pageable page) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);
		Page<Brokers> bat = (Page<Brokers>) brokersRepository.findForReports(brk.getBrkCode(),page);
		try {

			if (bat == null) {
				resp.setRequestStatus(true);
				resp.setPayload(bat);
				resp.setMessage("Batch not found");
			} else {

				resp.setRequestStatus(true);
				resp.setPayload(bat);
				resp.setMessage("Success");

			}

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}

		return resp;
	}

}

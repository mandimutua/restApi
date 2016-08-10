package com.ipo.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.JsonNode;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Users;
import com.ipo.utils.Lg;
import com.ipo.elements.ResetPasswordObject;
import com.ipo.elements.RestRequestObject;
import com.ipo.repositories.UsersRepository;
import com.ipo.utils.LoginLdapUtl;
import com.ipo.utils.LoginResponse;
import com.ipo.utils.TokenClass;
import com.ipo.utils.UserLogin;

import com.ipo.utils.Mailer;

@Service
public class UsersService {
	
	String userEmail;
	String password;
	String userName;

	@Autowired
	private UsersRepository usersRepository;

	public LoginResponse userLogin(UserLogin request) {
		LoginResponse resp = new LoginResponse();
		resp.setLoginMessage("System error");
		resp.setLoginStatus(false);
		Users usr = usersRepository.findByusrEmailIgnoreCase(request.getUsrEmail().trim());
		System.out.println("Username================================" + request.getUsrEmail());
		if (usr == null) {
			resp.setLoginMessage("Invalid login credentials");
			resp.setLoginStatus(false);
			resp.setSessID("");
			resp.setNames("");
		} else {
			if (usr.getSessionExpiry() == null) {
				
				// String password;
				try {
					password = new String(Base64.decodeBase64(usr.getUsrPass()), "UTF-8");

					String pswd = request.getPassword();

					if (password.equalsIgnoreCase(pswd)) {

						resp.setLoginMessage("Successful login credentials");
						resp.setLoginStatus(true);
						resp.setUserID(usr.getUsrCode());
						resp.setUsr_status(usr.getUsrStatus());

						LoginLdapUtl utl = new LoginLdapUtl();
						
							TokenClass tokenObj = utl.generateToken(usr);
							resp.setSessID(tokenObj.getToken());
							usr.setUsrAuthSalt(tokenObj.getAuthSalt());
							resp.setUsr_email(request.getUsrEmail().trim());
							Calendar now = Calendar.getInstance();
							now.add(Calendar.MINUTE, 30);
							usr.setSessionExpiry(now);
							resp.setLoginMessage("Success");
							usr.setUsrLastLogin(Calendar.getInstance().getTime());
							resp.setNames((usr.getUsrName().toUpperCase()));
							usersRepository.save(usr);
					
					} else {
						resp.setLoginMessage("Invalid login credentials");
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					// TODO: handle exception
				}
				}
				else{
				resp.setLoginMessage("User already logged in");
				resp.setLoginStatus(false);
			}
		}
		return resp;
	}

	@SuppressWarnings("rawtypes")
	public LoginResponse logout(RestRequestObject req) {
		LoginResponse resp = new LoginResponse();
		try {
			String[] tks = StringUtils.split(req.getToken(), ".");
			String username = this.decodeAndParse(tks[1]).get("username").asText();
			// Map<String, Object> decodedPayload = new
			// JWTVerifier(user.getSalt()).verify(req.getToken());
			Users usr = usersRepository.findByusrNameIgnoreCase(username);
			System.out.println(usr.toString());
			usr.setUsrAuthSalt(null);
			usr.setSessionExpiry(null);
			usersRepository.save(usr);
			resp.setLoginMessage("Logout Successful");
			resp.setLoginStatus(true);

		} catch (Exception ex) {
			ex.printStackTrace();
			resp.setLoginStatus(false);
			resp.setLoginMessage("Error on logout");
		}
		return resp;
	}

	public JsonNode decodeAndParse(String b64String) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = new String(Base64.decodeBase64(b64String), "UTF-8");
		JsonNode jwtHeader = (JsonNode) mapper.readValue(jsonString, JsonNode.class);
		return jwtHeader;
	}

	@SuppressWarnings("unused")
	public RestResponseObject authorize(String token, String action) {
		RestResponseObject obj = new RestResponseObject();
		obj.setRequestStatus(false);
		try {
			String[] tks = StringUtils.split(token, ".");
			String username = this.decodeAndParse(tks[1]).get("username").asText();
			Users user = usersRepository.findByusrNameIgnoreCase(username);
			Map<String, Object> decodedPayload = new JWTVerifier(user.getUsrAuthSalt()).verify(token);
			// check session expiry time
			if (Calendar.getInstance().after(user.getSessionExpiry())) {
				obj.setMessage("Your session timed out");
				Lg.storeLog("Session timed out for:" + username);
			} else {
				Calendar expTime = Calendar.getInstance();
				expTime.add(Calendar.MINUTE, 30);
				user.setSessionExpiry(expTime);
				Users usr = usersRepository.save(user);
				obj.setPayload(usr);
				obj.setMessage("Ok");
				obj.setRequestStatus(true);
				Lg.storeLog("------------------" + user.getUsrName() + " is very ok------------------");
			}

		} catch (SignatureException signatureException) {
			Lg.storeLog(signatureException.getLocalizedMessage());
			obj.setMessage("Invalid signature");
		} catch (IllegalStateException illegalStateException) {
			obj.setMessage("Invalid Token!");
			Lg.storeLog(illegalStateException.getLocalizedMessage());
		} catch (IOException e) {
			obj.setMessage("Invalid token");
			Lg.storeLog(e.getLocalizedMessage());
		} catch (InvalidKeyException e) {
			obj.setMessage("Invalid token");
			Lg.storeLog(e.getLocalizedMessage());
		} catch (JWTVerifyException e) {
			obj.setMessage("Invalid token");
			Lg.storeLog(e.getLocalizedMessage());

		} catch (NoSuchAlgorithmException e) {
			obj.setMessage("Invalid token");
			Lg.storeLog(e.getLocalizedMessage());
		}
		return obj;
	}

	public RestResponseObject create(Users req) {
		Users user = new Users();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating user");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		try {
			user.setUsrName(req.getUsrName().trim());
			user.setUsrEmail(req.getUsrEmail().trim());
			userEmail = req.getUsrEmail().trim();
			password = RandomStringUtils.randomAlphanumeric(8);
			userName = req.getUsrName().trim();
			user.setUsrPass(new String(Base64.encodeBase64(password.getBytes()), "UTF-8")); // Default																	// Pass
			user.setUsrBrkCode(req.getUsrBrkCode());
			user.setUsrStatus(BigInteger.valueOf(2)); // First Time created
			user.setUsrCdate(Calendar.getInstance().getTime());
			user.setUsrInputter(req.getUsrInputter());
			user.setUsrDate(Calendar.getInstance().getTime());
			Users createdUser = usersRepository.save(user);
			resp.setMessage("Success");
			resp.setPayload(createdUser);
			resp.setRequestStatus(true);
			sendMail();
		} catch (Exception er) {
			er.printStackTrace();
		}
		return resp;
	}

	private void sendMail() {
		// Generate Password
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String url = request.getScheme() + "://" + request.getServerName() + "/IPO";
		String subject = "IPO Admin Credentials";
		String content = "<h1>Greetings, </h1><p>Welcome To RIGHTS ISSUE Portal. These are your login credentials: "
				+ "<br><strong>Email: </strong>" + userEmail+ "<br/><strong>Password: </strong>" + password
				+ "<br/>Please Visit " + url + " to get started.</p>";
				
		final Mailer mailer = new Mailer(userEmail, subject, content);
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Sending mail to " + userEmail);
				mailer.send();
			}
		});

		
	}

	public ResetPasswordObject reset(ResetPasswordObject req) {
		
		ResetPasswordObject resp = new ResetPasswordObject();
		resp.setMessage("Error resetting password");
		//resp.setPayload(null);
		//resp.setRequestStatus(false);
		Users usr = usersRepository.findByusrEmailIgnoreCase(req.getUsrEmail().trim());
		System.out.println("UserCode================================" + req.getUsrEmail());
		if (usr == null) {
			resp.setMessage("Error user does't exist");
			resp.setStatus(false);

		} else {
			if(usr.getUsrStatus()==BigInteger.valueOf(4)|usr.getUsrStatus()==BigInteger.valueOf(1))
			{
				System.out.println("Status======"+usr.getUsrStatus());
			try {
			usr.setUsrPass(new String(Base64.encodeBase64(req.getUsr_password().getBytes()), "UTF-8"));
			usr.setUsrLastPassChange(Calendar.getInstance().getTime());
			usr.setUsrStatus(BigInteger.valueOf(1));
			usr.setUsrAuthSalt(null);
			password = req.getUsr_password();
			userEmail =req.getUsrEmail();
			sendMail();
			usersRepository.save(usr);
			resp.setMessage("Password Reset Successful");
			resp.setStatus(true);
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else
			{
			resp.setMessage("Password Reset Failure");
			resp.setStatus(false);
			}

		}
		
		return resp;
	}
	
	public RestResponseObject listUsers(Users req) {
		
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
	     HttpStatus status = HttpStatus.NOT_FOUND;
	        try {
	            List<Users> bizNos = (List<Users>) usersRepository.findAll();
	            if (bizNos.size() > 0) {
	                resp.setMessage("Users Found");
	                resp.setPayload(bizNos);
	                status = HttpStatus.OK;
	            }
	        } catch (Exception e) {
	          
	        }
	        return resp;
	}
}
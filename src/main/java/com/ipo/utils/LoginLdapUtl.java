package com.ipo.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTSigner;
import com.ipo.entities.Users;
import com.ipo.repositories.UsersRepository;
import com.ipo.utils.TokenClass;
import com.ipo.utils.UserLogin;

@Service
public class LoginLdapUtl {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	@SuppressWarnings("unused")
	@Autowired
	private UsersRepository userRepository;

	@SuppressWarnings("unchecked")
	public DirContext ldapUser(Users usr, UserLogin request) {
		// Set up the environment for creating the initial context
		@SuppressWarnings("rawtypes")
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://172.16.4.139:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "HERCULES\\" + request.getUserName());
		env.put(Context.SECURITY_CREDENTIALS, request.getPassword());
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ctx;
	}

	public TokenClass generateToken(Users user) {
		TokenClass tk = new TokenClass();
		@SuppressWarnings("unused")
		String myToken = "";
		// creating salt and update it on user profile
		tk.setAuthSalt(UUID.randomUUID().toString());

		try {
			// Initialize
			JWTSigner jwtSigner = new JWTSigner(tk.getAuthSalt());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", user.getUsrName());

			// set options
			JWTSigner.Options options = new JWTSigner.Options();
			options.setJwtId(true);
			// options.setExpirySeconds()

			// get token
			tk.setToken(jwtSigner.sign(map, options));

		} catch (Exception ex) {
			log.error(ex.getLocalizedMessage());
		}
		return tk;
	}
}

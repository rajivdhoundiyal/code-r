package com.codeproof.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codeproof.model.User;
import com.codeproof.spec.UserBusinessService;

@Service("userAuthenticationService")
public class UserAuthenticationService extends AbstractUserDetailsAuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);

	@Autowired
	private UserBusinessService userBusinessService;

	/*@Autowired
	private PasswordEncoder passwordEncoder;*/

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken userPasswordAuthToken)
			throws AuthenticationException {
		System.out.println("Oh Did I ever reached herer...");

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		System.out.println("Oh Did I ever reached herer...");
		/*String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			logger.warn("Username {}: no password provided", username);
			throw new BadCredentialsException("Please enter password");
		}

		User user = userBusinessService.findByUserName(username);
		if (user == null) {
			logger.warn("Username {} password {}: user not found", username, password);
			throw new UsernameNotFoundException("Invalid Login");
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			logger.warn("Username {} password {}: invalid password", username, password);
			throw new BadCredentialsException("Invalid Login");
		}

		
		 * if
		 * (!(UserAccountStatus.STATUS_APPROVED.name().equals(user.getStatus()
		 * ))) { logger.warn("Username {}: not approved", username); throw new
		 * BadCredentialsException("User has not been approved"); }
		 

		if (!user.getEnabled()) {
			logger.warn("Username {}: disabled", username);
			throw new BadCredentialsException("User disabled");
		}

		final List<GrantedAuthority> auths;
		if (!user.getRoles().isEmpty()) {
			auths = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRolesCSV());
		} else {
			auths = AuthorityUtils.NO_AUTHORITIES;
		}

		return new org.springframework.security.core.userdetails.User(username, password, user.getEnabled(), // enabled
				true, // account not expired
				true, // credentials not expired
				true, // account not locked
				auths);*/
		return null;
	}
}

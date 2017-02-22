package com.epam.tam.model;

import com.epam.tam.util.Property;
//signleton example
public class LoginAccount {
	private static final String email = Property.HCP_LOGIN;
	private static final String password = Property.HCP_PASS;
	
	private LoginAccount() {}
	
	public static LoginAccount getInstance() {
		return LoginHolder.INSTANCE;
	}
	
	private static class LoginHolder {
		private static final LoginAccount INSTANCE = new LoginAccount();
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
}

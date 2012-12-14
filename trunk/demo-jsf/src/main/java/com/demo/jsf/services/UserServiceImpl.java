package com.demo.jsf.services;

import java.io.Serializable;

public class UserServiceImpl implements UserService, Serializable {
	
	private static final long serialVersionUID = 1L;

	public String getUser(long Id) {
		return "test service";
	}

}
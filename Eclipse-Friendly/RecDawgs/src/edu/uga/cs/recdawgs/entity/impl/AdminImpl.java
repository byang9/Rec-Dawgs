package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;

public class AdminImpl extends Persistent implements Administrator {

	public AdminImpl() {
		super(null, null, null, null, null);
	}
	
	public AdminImpl(String firstName, String lastName, String userName, String password, String emailAddress) {
		super(firstName, lastName, userName, password, emailAddress);
	}
	
	
}
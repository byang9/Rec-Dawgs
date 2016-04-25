package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;

public class AdminImpl extends Persistent implements Administrator {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String emailAddress;
    private boolean isStudent;
	
	public AdminImpl(String firstName, String lastName, String userName, String password, String emailAddress) {
		super(-1);
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
        this.isStudent = false;
	}
	
	public AdminImpl() {
		super(-1);
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.password = null;
		this.emailAddress = null;
        this.isStudent = false;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
    
    @Override
	public boolean getIsStudent() {
		return isStudent;
	}
	
	@Override
	public void setIsStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	
}
package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;

public class UserImpl extends Persistent implements User{
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String emailAddress;
    private boolean isStudent;

		
	public UserImpl( String firstName,
					 String lastName,
					 String userName,
					 String password,
					 String emailAddress,
                     boolean isStudent
					)
	{
		super(-1);
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
        this.isStudent = isStudent;
	}

  	public String getFirstName(){
  		return this.firstName;
  	};
    
    /** Set the user's first name.
     * @param firstName the new first name of this user
     */
    public void setFirstName( String firstName ){
    	this.firstName = firstName;
    };
    
    /** Return the user's last name.
     * @return the user's last name
     */
    public String getLastName(){
    	return this.lastName;
    };
    
    /** Set the user's first name.
     * @param lastName the new last name of this user
     */
    public void setLastName( String lastName ){
    	this.lastName = lastName;
    };
    
    /** Return the user's user name (login name).
     * @return the user's user name (login name)
     */
    public String getUserName(){
    	return this.userName;
    };
    
    /** Set the user's user name (login name).
     * @param userName the new user (login name)
     */
    public void setUserName( String userName ){
    	this.userName = userName;
    };
    
    /** Return the user's password.
     * @return the user's password
     */
    public String getPassword(){
    	return this.password;
    };
    
    /** Set the user's password.
     * @param password the new password
     */
    public void setPassword( String password ){
    	this.password = password;
    };
    
    /** Return the user's email address.
     * @return the user's email address
     */
    public String getEmailAddress(){
    	return this.emailAddress;
    };
    
    /** Set the user's email address.
     * @param emailAddress the new email address
     */
    public void setEmailAddress( String emailAddress ){
    	this.emailAddress = emailAddress;
    };
    
    /** Return the user's email address.
     * @return the user's email address
     */
    public boolean getIsStudent(){
    	return this.isStudent;
    };
    
    /** Set the user's email address.
     * @param emailAddress the new email address
     */
    public void setIsStudent( boolean isStudent ){
    	this.isStudent = isStudent;
    };

     public String toString()
    {
        return "User[" + getId() + "] " + getUserName() + " " + getFirstName() + " " + getLastName() + " " 
                + getEmailAddress();
    }

    public boolean equals( Object otherUser )
    {
        if( otherUser == null )
            return false;
        if( otherUser instanceof User ) 
            return getUserName().equals( ((User)otherUser).getUserName() );
        return false;        
    }



}
package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;


public class StudentImpl extends Persistent implements Student {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String emailAddress;
    private boolean isStudent;
	private String studentId;
	private String major;
	private String address;

    public StudentImpl(String firstName, String lastName, String userName, String password, String emailAddress, String studentId, String major, String address) {
        super(-1);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.isStudent = true;
        this.studentId = studentId;
        this.major = major;
        this.address = address;
    }
    
    public StudentImpl() {
        super(-1);
        this.firstName = null;
        this.lastName = null;
        this.userName = null;
        this.password = null;
        this.emailAddress = null;
        this.isStudent = true;
        this.studentId = null;
        this.major = null;
        this.address = null;
    }
    
    /** Return the student id for this student.
     * @return the String representing the id of the student
     */
    public String getStudentId(){
        return this.studentId;
    }
    
    /** Set the new student id for this student.
     * @param studentId the new student id of this student
     */
    public void setStudentId( String studentId ){
        this.studentId = studentId;
    }
    
    /** Return the major of this student.
     * @return the major of this student
     */
    public String getMajor(){
        return this.major;
    }
    
    /** Set the new major of this student.
     * @param major the new major of this student
     */
    public void setMajor( String major ){
        this.major = major;
    }
    
    /** Return the address of this student.
     * @return the address of this student
     */
    public String getAddress(){
        return this.address;
    }
    
    /** Set the new address for this student.
     * @param address the new address of this student
     */
    public void setAddress( String address ){
        this.address = address;
    }

    public String toString()
    {
        return "Student[" + getStudentId() + "] " + getMajor() + " " + getAddress();
    }
    
    public boolean equals( Object otherStudent )
    {
        if( otherStudent == null )
            return false;
        if( otherStudent instanceof Student ) // name is a unique attribute
            return getUserName().equals( ((Student)otherStudent).getUserName() );
        return false;        
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
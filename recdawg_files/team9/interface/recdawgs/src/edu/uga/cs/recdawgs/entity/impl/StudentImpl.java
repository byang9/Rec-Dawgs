package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs


public class StudentImpl extends Person{

    String studentId;
    String major;
    String address

    public StudentImpl(String studentId, String major, String address){
        this.studentId = studentId;
        this.major = major;
        this.address = address;
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
        return "Student[" + getStudentId() + "] " + getMajor() + " " + getAddress() 
    }
    
    public boolean equals( Object otherStudent )
    {
        if( otherStudent == null )
            return false;
        if( otherStudent instanceof Student ) // name is a unique attribute
            return getName().equals( ((Student)otherStudent).getName() );
        return false;        
    }

}
package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class StudentIterator implements Iterator<Student>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public StudentIterator( ResultSet rs, ObjectLayer objectLayer) throws RDException{
    	this.rs = rs;
    	this.objectLayer = objectLayer;
    	try{
    	    this.more = rs.next();
    	}//try
        catch( Exception e ) {  // just in case...                                                                                                      
            throw new RDException( "StudentIterator: Cannot create Person iterator; root cause: " + e );
        }

    }//constructor

    public boolean hasNext(){
	   return more;
    }//hasNext

    public Student next(){
         //firstname, lastname, username, password, email, isStudent, studentID, major, address
        long id;
        String firstName;
        String lastName;
        String userName;
        String password;
        String email;
        String isStudent;
        String studentId;
        String major;
        String address;

        if ( more ){

            try{

                id = rs.getLong(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                userName = rs.getString(4);
                password = rs.getString(5);
                email = rs.getString(6);
                isStudent = rs.getString(7);
                studentId = rs.getString(8);
                major = rs.getString(9);
                address = rs.getString(10);

                System.out.println("Current Data: (" + id + ", " + firstName + ", " + lastName + ", " + password + ", " + email + ", " + isStudent + ", " + studentId + ", " + major + ", " + address + ")");

                more = rs.next();

            }
            catch(Exception e){
                throw new NoSuchElementException( "StudentIterator: No next Person object; root cause: " + e );
            }

            Student user;
			try {
				user = objectLayer.createStudent( firstName, lastName, userName, password, email, studentId, major, address);
				user.setId( id );

				return user;
			} catch (RDException e) {
				// TODO Auto-generated catch block
                System.out.println("Something is causing the student to not be created: (" + id + ", " + firstName + ", " + lastName + ", " + password + ", " + email + ", " + isStudent + ", " + studentId + ", " + major + ", " + address + ")");
				e.printStackTrace();
			}
			
        }
        return null;
    }//next

    public void remove()
    {
        throw new UnsupportedOperationException();
    }//remove


}
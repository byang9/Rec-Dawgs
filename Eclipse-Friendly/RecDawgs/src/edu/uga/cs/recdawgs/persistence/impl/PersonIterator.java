package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSUchElementException;

package edu.uga.cs.recdawgs.RDException;

package edu.uga.cs.recdawgs.entity.Person;
package edu.uga.cs.recdawgs.object.ObjectLayer;


public class PersonIterator implements Iterator<Person>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public PersonIterator( ResultSet rs, ObjectLayer objectLayer) throws RDException{
    	this.rs = rs;
    	this.objectLayer = objectLayer;
    	try{
    	    this.more = rs.next();
    	}//try
        catch( Exception e ) {  // just in case...                                                                                                      
            throw new RecDawgsException( "PersonIterator: Cannot create Person iterator; root cause: " + e );
        }

    }//constructor

    public boolean hasNext(){
	   return more;
    }//hasNext

    public Person next(){
         //firstname, lastname, username, password, email, isStudent, studentID, address, phone
        long id;
        String firstName;
        String lastName;
        String userName;
        String password;
        String email;
        boolean isStudent;
        long studentId;
        String address;
        String phone;

        if ( more ){

            try{

                id = rs.getLong(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                userName = rs.getString(4);
                password = rs.getString(5);
                email = rs.getString(6);
                isStudent = rs.getBoolean(7);
                studentId = rs.getLong(8);
                address = rs.getString(9);
                phone = rs.getString(10);

                more = rs.next();

            }
            catch(Exception e){
                throw new NoSuchElementException( "PersonIterator: No next Person object; root cause: " + e );
            }

            Person person = objectLayer.createPerson( firstName, lastName, userName, password, email, isStudent, studentId, address, phone);
            person.setId( id );

            return person;
        }
        else{
            throw new NoSuchElementException( "PersonIterator: No next Person object; root cause: " + e );
        }
    }//next

    public void remove()
    {
        throw new UnsupportedOperationException();
    }//remove


}










package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class AdministratorIterator implements Iterator<Administrator>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public AdministratorIterator( ResultSet rs, ObjectLayer objectLayer) throws RDException{
    	this.rs = rs;
    	this.objectLayer = objectLayer;
    	try{
    	    this.more = rs.next();
    	}//try
        catch( Exception e ) {  // just in case...                                                                                                      
            throw new RDException( "PersonIterator: Cannot create Person iterator; root cause: " + e );
        }

    }//constructor

    public boolean hasNext(){
	   return more;
    }//hasNext

    public Administrator next(){
         //firstname, lastname, username, password, email, isStudent, studentID, address, phone
        long id;
        String firstName;
        String lastName;
        String userName;
        String password;
        String email;
        boolean isStudent;

        if ( more ){

            try{

                id = rs.getLong(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                userName = rs.getString(4);
                password = rs.getString(5);
                email = rs.getString(6);
                isStudent = rs.getBoolean(7);

                more = rs.next();

            }
            catch(Exception e){
                throw new NoSuchElementException( "PersonIterator: No next Person object; root cause: " + e );
            }

            Administrator user;
			try {
                if (!isStudent) {
				    user = objectLayer.createAdministrator( firstName, lastName, userName, password, email);
				    user.setId( id );

    				return user;
	           } else {
                    next();
               }
    		} catch (RDException e) {
				// TODO Auto-generated catch block
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
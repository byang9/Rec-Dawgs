// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:	PersonIteratorImpl
//
// K.J. Kochut
//
//
//

package edu.uga.clubs.persistence.impl;



import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;


public class PersonIterator 
    implements Iterator<Person>
{
    private ResultSet   rs = null;
    private ObjectLayer objectLayer = null;
    private boolean     more = false;

    // these two will be used to create a new object
    //
    public PersonIterator( ResultSet rs, ObjectLayer objectLayer )
            throws ClubsException
    { 
        this.rs = rs;
        this.objectLayer = objectLayer;
        try {
            more = rs.next();
        }
        catch( Exception e ) {	// just in case...
            throw new ClubsException( "PersonIterator: Cannot create Person iterator; root cause: " + e );
        }
    }

    public boolean hasNext() 
    { 
        return more; 
    }

    public Person next() 
    {
        long   id;
        String userName;
        String password;
        String email;
        String firstName;
        String lastName;
        String address;
        String phone;

        if( more ) {

            try {
                id = rs.getLong( 1 );
                userName = rs.getString( 2 );
                password = rs.getString( 3 );
                email = rs.getString( 4 );
                firstName = rs.getString( 5 );
                lastName = rs.getString( 6 );
                address = rs.getString( 7 );
                phone = rs.getString( 8 );

                more = rs.next();
            }
            catch( Exception e ) {	// just in case...
                throw new NoSuchElementException( "PersonIterator: No next Person object; root cause: " + e );
            }
            
            Person person = objectLayer.createPerson( userName, password, email, firstName, lastName, address, phone );
            person.setId( id );
            
            return person;
        }
        else {
            throw new NoSuchElementException( "PersonIterator: No next Person object" );
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

};

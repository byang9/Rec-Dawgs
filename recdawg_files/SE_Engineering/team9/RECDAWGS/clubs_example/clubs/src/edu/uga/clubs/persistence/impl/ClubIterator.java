// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:	ClubIteratorImpl
//
// K.J. Kochut
//
//
//

package edu.uga.clubs.persistence.impl;




import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;


public class ClubIterator
    implements Iterator<Club>
{
    private ResultSet    rs = null;
    private ObjectLayer  objectLayer = null;
    private boolean      more = false;

    // these two will be used to create a new object
    //
    public ClubIterator( ResultSet rs, ObjectLayer objectModel )
            throws ClubsException
    { 
        this.rs = rs;
        this.objectLayer = objectModel;
        try {
            more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            throw new ClubsException( "ClubIterator: Cannot create Club iterator; root cause: " + e );
        }
    }

    public boolean hasNext() 
    { 
        return more; 
    }

    public Club next() 
    {
        long   id;
        String name;
        String address;
        Date   establishedOn;
        Club   club = null;
        Person founder = null;
        long   fid;
        String username;
        String userpass;
        String email;
        String firstname;
        String lastname;
        String faddress;
        String phone;

        if( more ) {

            try {
                id = rs.getLong( 1 );
                name = rs.getString( 2 );
                address = rs.getString( 3 );
                establishedOn = rs.getDate( 4 );
                fid = rs.getLong( 5 );
                username = rs.getString( 6 );
                userpass = rs.getString( 7 );
                email = rs.getString( 8 );
                firstname = rs.getString( 9 );
                lastname = rs.getString( 10 );
                faddress = rs.getString( 11 );
                phone = rs.getString( 12 );

                more = rs.next();
            }
            catch( Exception e ) {      // just in case...
                throw new NoSuchElementException( "ClubIterator: No next Club object; root cause: " + e );
            }
            founder = objectLayer.createPerson( username, userpass, email, firstname, lastname, faddress, phone );
            founder.setId( fid );
            try {
                club = objectLayer.createClub( name, address, establishedOn, founder );
                club.setId( id );
                //club.setFounderId( founderId );
            }
            catch( ClubsException ce ) {
                // safe to ignore: we explicitly set the persistent id of the founder Person object above!
            }
            
            return club;
        }
        else {
            throw new NoSuchElementException( "ClubIterator: No next Club object" );
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

};

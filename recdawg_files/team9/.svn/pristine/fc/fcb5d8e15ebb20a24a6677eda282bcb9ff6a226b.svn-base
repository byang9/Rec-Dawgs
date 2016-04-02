//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.clubs.logic.impl;


import java.util.Date;
import java.util.Iterator;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;



public class CreateClubCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateClubCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createClub( String club_name, String club_addr, long founderId )
            throws ClubsException
    { 
        Date 		    estab = null;
        Club 		    club  = null;
        Club                modelClub = null;
        Iterator<Club>      clubIter = null;
        Person              founder = null;
        Person              modelPerson = null;
        Iterator<Person>    personIter = null;

        // check if a club with this name already exists (name is unique)
        modelClub = objectLayer.createClub();
        modelClub.setName( club_name );
        clubIter = objectLayer.findClub( modelClub );
        if( clubIter.hasNext() )
            throw new ClubsException( "A club with this name already exists: " + club_name );

        // retrieve the founder person
        modelPerson = objectLayer.createPerson();
        modelPerson.setId( founderId );
        personIter = objectLayer.findPerson( modelPerson );
        while( personIter.hasNext() ) {
            founder = personIter.next();
        }

        // check if the person actually exists
        if( founder == null )
            throw new ClubsException( "A person with this id does not exist: " + founderId );

        // create the club
        estab = new Date();		// mark it as created now
        club = objectLayer.createClub( club_name, club_addr, estab, founder );
        objectLayer.storeClub( club );

        return club.getId();
    }
}


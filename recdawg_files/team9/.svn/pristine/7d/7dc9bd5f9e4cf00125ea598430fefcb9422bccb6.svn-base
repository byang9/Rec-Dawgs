//
// A control class to implement the 'List club membership' use case
//
//


package edu.uga.clubs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;



public class FindClubMembersCtrl 
{
    private ObjectLayer objectLayer = null;
    
    public FindClubMembersCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public List<Person> findClubMembers( String clubName )
            throws ClubsException
    {
        Club                club = null;
        Club                modelClub = null;
        Iterator<Club>      clubIter = null;
        List<Person>        persons  = null;

        persons = new LinkedList<Person>();

        // find the club object
        modelClub = objectLayer.createClub();
        modelClub.setName( clubName );
        clubIter = objectLayer.findClub( modelClub );
        while( clubIter.hasNext() ) {
            club = clubIter.next();
        }
        if( club == null )
            throw new ClubsException( "A club with this name does not exist: " + clubName );

        Membership modelMembership = objectLayer.createMembership();
        modelMembership.setClub( club );
        Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
        while( membershipIter != null && membershipIter.hasNext() ) {
            Membership m = membershipIter.next();
            persons.add( m.getPerson() );
        }

        return persons;
    }
  
}

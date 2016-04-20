//
// A control class to implement the 'Join a club' use case
//
//


package edu.uga.clubs.logic.impl;




import java.util.Date;
import java.util.Iterator;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;



public class JoinClubCtrl {
    
    private ObjectLayer objectLayer = null;

    public JoinClubCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public long joinClub( long personId, String clubName )
            throws ClubsException
    {
        Person               person = null;
        Person               modelPerson = null;
        Iterator<Person>     personIter = null;
        Club                 club = null;
        Club                 modelClub = null;
        Iterator<Club>       clubIter = null;
        Membership           membership = null;
        Membership           modelMembership = null;
        Iterator<Membership> membershipIter = null;

        modelClub = objectLayer.createClub();
        modelClub.setName( clubName );
        clubIter = objectLayer.findClub( modelClub );
        while( clubIter.hasNext() ) {
            club = clubIter.next();
            System.out.println( "CtrlJoinClub.joinClub: found club: " + club );
        }
        if( club == null )
            throw new ClubsException( "Failed to locate a Club called: " + clubName );

        modelPerson = objectLayer.createPerson();
        modelPerson.setId( personId );
        personIter = objectLayer.findPerson( modelPerson );
        while( personIter.hasNext() ) {
            person = personIter.next();
            System.out.println( "CtrlJoinClub.joinClub: found person: " + person );
        }
        if( person == null )
            throw new ClubsException( "Failed to locate a Person with id: " + personId );

        modelMembership = objectLayer.createMembership();
        modelMembership.setPerson( person );
        modelMembership.setClub( club );
        membershipIter = objectLayer.findMembership( modelMembership );
        if( membershipIter.hasNext() )
            throw new ClubsException( "This person is already a member of this club" );

        membership = objectLayer.createMembership( person, club, new Date() );
        objectLayer.storeMembership( membership );

        return membership.getId();
    }
}

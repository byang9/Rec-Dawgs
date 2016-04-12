// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:	IsMemberOfImpl
//
// K.J. Kochut
//
//
//

package edu.uga.clubs.entity.impl;

import java.util.Date;

import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.persistence.impl.Persistent;


public class MembershipImpl
    extends Persistent
    implements Membership
{
    // Membership attributes
    private Person   person;
    private Club     club;
    private Date     joinedOn;

    public MembershipImpl( Person person, Club club, Date joinedOn ) 
    { 
        super( -1 );
        this.person = person;
        this.club = club;
        this.joinedOn = joinedOn;
    }

    /**
     * @return the person
     */
    public Person getPerson()
    {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person)
    {
        this.person = person;
    }

    /**
     * @return the club
     */
    public Club getClub()
    {
        return club;
    }

    /**
     * @param club the club to set
     */
    public void setClub(Club club)
    {
        this.club = club;
    }

    /**
     * @return the joinedOn
     */
    public Date getJoinedOn()
    {
        return joinedOn;
    }

    /**
     * @param joinedOn the joinedOn to set
     */
    public void setJoinedOn(Date joinedOn)
    {
        this.joinedOn = joinedOn;
    }
    
    public String toString()
    {
        return "Membership[" + getId() + "] " + getPerson() + " is member of " + getClub() + " since " + getJoinedOn();
    }

    public boolean equals( Object otherMembership )
    {
        if( otherMembership == null )
            return false;
        if( otherMembership instanceof Membership ) 
            return getPerson().equals( ((Membership)otherMembership).getPerson() )
                    && getClub().equals( ((Membership)otherMembership).getClub() );
        return false;        
    }
};



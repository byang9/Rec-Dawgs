package edu.uga.clubs.persistence.impl;

import java.sql.Connection;
import java.util.Iterator;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;
import edu.uga.clubs.persistence.PersistenceLayer;

public class PersistenceLayerImpl 
    implements PersistenceLayer
{
    private PersonManager personManager = null;
    private ClubManager clubManager = null;
    private MembershipManager membershipManager = null;
    
    public PersistenceLayerImpl( Connection conn, ObjectLayer objectLayer )
    {
        personManager = new PersonManager( conn, objectLayer );
        clubManager = new ClubManager( conn, objectLayer );
        membershipManager = new MembershipManager( conn, objectLayer );
        System.out.println( "PersistenceLayerImpl.PersistenceLayerImpl(conn,objectLayer): initialized" );
    }
    
    public void saveClub(Club club) throws ClubsException
    {
        clubManager.save( club );
    }

    public Iterator<Club> restoreClub(Club club) throws ClubsException
    {
        return clubManager.restore( club );
    }

    public void deleteClub(Club club) throws ClubsException
    {
        clubManager.delete( club );
    }

    public void savePerson(Person person) throws ClubsException
    {
        personManager.save( person );
    }

    public Iterator<Person> restorePerson(Person person) 
            throws ClubsException
    {
        return personManager.restore( person );
    }

    public void deletePerson(Person person) throws ClubsException
    {
        personManager.delete( person );
    }

    public void saveMembership(Membership membership) throws ClubsException
    {
        membershipManager.save( membership );
    }

    public Iterator<Membership> restoreMembership(Membership membership)
            throws ClubsException
    {
        return membershipManager.restore( membership );
    }

    public void deleteMembership(Membership membership) throws ClubsException
    {
        membershipManager.delete( membership );
    }

    public Person restoreFounderFoundedClub(Club club) throws ClubsException
    {
        return clubManager.restoreEstablishedBy( club );
    }

    public Iterator<Club> restoreFounderFoundedClub(Person person)
            throws ClubsException
    {
        return personManager.restoreEstablishedBy( person );
    }

    public void saveFounderFoundedClub(Club club, Person person)
            throws ClubsException
    {
        if( person == null )
            throw new ClubsException( "The club's founder is null" );
        if( !person.isPersistent() )
            throw new ClubsException( "The club's founder is not persistent" );
        // create a new proxy with the new founder
        club.setFounder( person );
        //club = objectModel.createClub( club.getName(), club.getAddress(), club.getEstablishedOn(), person );
        //club.setId( club.getId() );
        clubManager.save( club );
    }

}

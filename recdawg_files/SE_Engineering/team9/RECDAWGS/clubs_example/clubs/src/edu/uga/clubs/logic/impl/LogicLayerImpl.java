/**
 * 
 */
package edu.uga.clubs.logic.impl;

import java.sql.Connection;
import java.util.List;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.logic.LogicLayer;
import edu.uga.clubs.object.ObjectLayer;
import edu.uga.clubs.object.impl.ObjectLayerImpl;
import edu.uga.clubs.persistence.PersistenceLayer;
import edu.uga.clubs.persistence.impl.PersistenceLayerImpl;
import edu.uga.clubs.session.Session;
import edu.uga.clubs.session.SessionManager;


/**
 * @author Krys J. Kochut
 *
 */
public class LogicLayerImpl 
    implements LogicLayer
{
    private ObjectLayer objectLayer = null;

    public LogicLayerImpl( Connection conn )
    {
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
        objectLayer.setPersistence( persistenceLayer );
        System.out.println( "LogicLayerImpl.LogicLayerImpl(conn): initialized" );
    }
    
    public LogicLayerImpl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
        System.out.println( "LogicLayerImpl.LogicLayerImpl(objectLayer): initialized" );
    }

    public List<Club> findAllClubs() 
            throws ClubsException
    {
        FindAllClubsCtrl ctrlFindAllClubs = new FindAllClubsCtrl( objectLayer );
        return ctrlFindAllClubs.findAllClubs();
    }

    public List<Person> findAllPersons() 
            throws ClubsException
    {
        FindAllPersonsCtrl ctrlFindAllPersons = new FindAllPersonsCtrl( objectLayer );
        return ctrlFindAllPersons.findAllPersons();
    }

    public long joinClub(Person person, Club club) throws ClubsException
    {
        return 0;
    }

    public long joinClub(long personId, String clubName) throws ClubsException
    {
        JoinClubCtrl ctrlJoinClub = new JoinClubCtrl( objectLayer );
        return ctrlJoinClub.joinClub( personId, clubName );
    }

    public long createClub(String clubName, String address, long founderId)
            throws ClubsException
    {
        CreateClubCtrl ctrlCreateClub = new CreateClubCtrl( objectLayer );
        return ctrlCreateClub.createClub( clubName, address, founderId );
    }

    public long createPerson(String userName, String password, String email,
            String firstName, String lastName, String address, String phone)
            throws ClubsException
    {
        CreatePersonCtrl ctrlCreatePerson = new CreatePersonCtrl( objectLayer );
        return ctrlCreatePerson.createPerson( userName, password, email, firstName,
                                              lastName, address, phone );
    }

    public List<Person> findClubMembers(String clubName) throws ClubsException
    {
        FindClubMembersCtrl ctrlFindClubMembers = new FindClubMembersCtrl( objectLayer );
        return ctrlFindClubMembers.findClubMembers( clubName );
    }

    public void logout( String ssid ) throws ClubsException
    {
        SessionManager.logout( ssid );
    }

    public String login( Session session, String userName, String password ) 
            throws ClubsException
    {
        LoginCtrl ctrlVerifyPerson = new LoginCtrl( objectLayer );
        return ctrlVerifyPerson.login( session, userName, password );
    }
}

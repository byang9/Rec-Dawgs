package edu.uga.clubs.logic;


import java.util.List;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.session.Session;


public interface LogicLayer
{
    public List<Club>         findAllClubs() throws ClubsException;
    public List<Person>       findAllPersons() throws ClubsException;
    public long               joinClub( Person person, Club club ) throws ClubsException;
    public long               joinClub( long personId, String clubName ) throws ClubsException;
    public long               createClub( String clubName, String address, long founderId ) throws ClubsException;
    public long               createPerson( String userName, String password, String email, String firstName, 
                                            String lastName, String address, String phone ) throws ClubsException;
    public List<Person>       findClubMembers( String clubName ) throws ClubsException;
    public void               logout( String ssid ) throws ClubsException;
    public String             login( Session session, String userName, String password ) throws ClubsException;
}

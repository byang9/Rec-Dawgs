package edu.uga.cs.recdawgs.persistence.impl;




import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.League.LeagueException;
import edu.uga.League.entity.League;
import edu.uga.League.entity.Person;
import edu.uga.League.object.ObjectLayer;


public class LeagueIterator implements Iterator<League> {
    private ResultSet    rs = null;
    private ObjectLayer  objectLayer = null;
    private boolean      more = false;

    // these two will be used to create a new object
    public LeagueIterator(ResultSet rs, ObjectLayer objectModel) throws LeagueException {
        this.rs = rs;
        this.objectLayer = objectModel;
        try {
            more = rs.next();
        }
        catch (Exception e) {  // just in case...
            throw new LeagueException( "LeagueIterator: Cannot create League iterator; root cause: " + e );
        }
    }

    public boolean hasNext() { 
        return more; 
    }

    public League next() {
        long   id;
        String name;
        long winnerID;
        boolean isIndoor;
        long minTeams;
        long maxTeams;
        long minTeamMembers;
        long maxTeamMembers;
        String matchRules;
        String leagueRules;
        League league = null;

        if (more) {

            try {
                id = rs.getLong(1);
                name = rs.getString(2);
                winnerID = rs.getLong(3);
                isIndoor = rs.getBoolean(4);
                minTeams = rs.getLong(5);
                maxTeams = rs.getLong(6);
                minTeamMembers = rs.getLong(7);
                maxTeamMembers = rs.getLong(8);
                matchRules = rs.getString(9);
                leagueRules = rs.getString(10);

                more = rs.next();
            }
            catch (Exception e) {      // just in case...
                throw new NoSuchElementException( "LeagueIterator: No next League object; root cause: " + e );
            }
            founder = objectLayer.createPerson( username, userpass, email, firstname, lastname, faddress, phone );
            founder.setId( fid );
            try {
                League = objectLayer.createLeague( name, address, establishedOn, founder );
                League.setId( id );
                //League.setFounderId( founderId );
            }
            catch (LeaguesException ce) {
                // safe to ignore: we explicitly set the persistent id of the founder Person object above!
            }
            
            return League;
        }
        else {
            throw new NoSuchElementException("LeagueIterator: No next League object");
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

};

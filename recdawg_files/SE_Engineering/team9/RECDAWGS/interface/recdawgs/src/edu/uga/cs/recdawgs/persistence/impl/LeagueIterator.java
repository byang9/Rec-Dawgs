package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.object.ObjectLayer;

/**
 * LeagueIterator is the class iterates between Leagues
 * It acts as a searching tool for Leagues.
 * It iterates the League proxy objects.
 *
 * @author Logan Jahnke
 */
public class LeagueIterator implements Iterator<League> {
    private ResultSet    rs = null;
    private ObjectLayer  objectLayer = null;
    private boolean      more = false;

    // these two will be used to create a new object
    public LeagueIterator(ResultSet rs, ObjectLayer objectModel) throws RDException {
        this.rs = rs;
        this.objectLayer = objectModel;
        try {
            more = rs.next();
        }
        catch (Exception e) {  // just in case...
            throw new RDException("LeagueIterator: Cannot create League iterator; root cause: " + e);
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
            
            try {
                league = objectLayer.createLeague(name, winnerID, isIndoor, minTeams, maxTeams, minTeamMembers, maxTeamMembers, matchRules, leagueRules);
                league.setId(id);
            }
            catch (RDException ce) {
                throw new RDException("This shouldn't ever happen: LeagueIterator: " + ce);
            }
            
            return league;
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

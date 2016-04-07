package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;


import edu.uga.League.object.ObjectLayer;
import edu.uga.League.LeagueException;
import edu.uga.League.entity.Round;


public class RoundIterator implements Iterator<Round> {
    private ResultSet    rs = null;
    private ObjectLayer  objectLayer = null;
    private boolean      more = false;

    // these two will be used to create a new object
    public RoundIterator(ResultSet rs, ObjectLayer objectModel) throws RDException {
        this.rs = rs;
        this.objectLayer = objectModel;
        try {
            more = rs.next();
        }
        catch (Exception e) {  // just in case...
            throw new RDException( "RoundIterator: Cannot create Round iterator; root cause: " + e );
        }
    }

    public boolean hasNext() { 
        return more; 
    }

    public Round next() {
        long        id;
        Match[]     matches;
        Date        datePlayed;
        SportsVenue venue;
        League      sportLeague
        

        if (more) {

            try {
                id = rs.getLong(1);
                matches = rs.getArray(2);
                datePlayed = rs.getDate(3);
                venue = rs.getString(4);
                sportLeague = rs.getString(5);

                more = rs.next();
            }
            catch (Exception e) {      // just in case...
                throw new NoSuchElementException( "RoundIterator: No next Round object; root cause: " + e );
            }
            
            round = objectLayer.createRound(matches,datePlayed,venue,sportLeague);
            round.setId(id);
            try {
                //League.setFounderId( founderId );
            }
            catch (RDException ce) {
                // safe to ignore: we explicitly set the persistent id of the founder Person object above!
            }
            
            return round;
        }
        else {
            throw new NoSuchElementException("RoundIterator: No next Round object");
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

};

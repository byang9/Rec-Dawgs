package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSUchElementException;

package edu.uga.cs.recdawgs.RDException;

package edu.uga.cs.recdawgs.entity.Team;
package edu.uga.cs.recdawgs.object.ObjectLayer;


public class TeamIterator implements Iterator<Team>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public TeamIterator( ResultSet rs, ObjectLayer objectLayer) throws RDException{
        this.rs = rs;
        this.objectLayer = objectLayer;
        try{
            this.more = rs.next();
        }
        catch( Exception e ) {                   
             throw new RDException( "TeamIterator: Cannot create Team iterator; root cause: " + e );
        }

    }// constructor

    public boolean hasNext(){
        return more;
    }//hasNext

    public Team next(){
        //name, leagueId, established, captainId)
        long    id;
        String teamName;
        String leagueId;
        String established;
        long   captainId;

        if (more){
            try {
                    id = rs.getLong(1);
                    teamName = rs.getString(2);
                    leagueId = rs.getString(3);
                    established = rs.getString(4);
                    captainId = rs.getLong(5);

                    more = rs.next();
            }
            catch( Exception e){
                throw new NoSuchElementException( "TeamIterator: No next Team object; root cause: " + e );
            }

            Team team = objectLayer.createTeam( teamName, leagueId, established, captainId);
            team.setId( id );

            return team;
        }
        else{
            throw new NoSuchElementException("TeamIterator: No next Team object")
        }

    }//next

    public void remove(){
        throw new UnsupportedOperationException();
    }//remove
};


package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;

import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;


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

        if (more){
            try {
                    id = rs.getLong(1);
                    teamName = rs.getString(2);

                    more = rs.next();
            }
            catch( Exception e){
                throw new NoSuchElementException( "TeamIterator: No next Team object; root cause: " + e );
            }

            Team team;
			try {
				team = objectLayer.createTeam(teamName);
				team.setId( id );

	            return team;
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
            
        }
        else{
            throw new NoSuchElementException("TeamIterator: No next Team object");
        }

    }//next

    public void remove(){
        throw new UnsupportedOperationException();
    }//remove
};


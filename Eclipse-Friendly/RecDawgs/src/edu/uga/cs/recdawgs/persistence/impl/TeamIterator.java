package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
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
            System.out.println("How about here?");
        }
        catch( Exception e ) {   
             throw new RDException( "TeamIterator: Cannot create Team iterator; root cause: " + e );
        }

    }// constructor

    public boolean hasNext(){
        return more;
    }//hasNext

    public Team next(){
    	
        //name, leagueId, captainId)
        long    id;
        String teamName;
        League league;
        Student captain;
        
//        "select t.id, t.name, t.leagueId, t.captainId, " +
//		"l.id, l.name, l.winnerID, l.isIndoor, l.minTeams, " +
//        "l.maxTeams, l.minTeamMembers, l.maxTeamMembers, l.matchRules," +
//        "l.leagueRules, p.id, p.firstname, p.lastname, p.username, p.password,"
//        + "p.email, p.isStudent, p.studentID, p.address from team t, league l, person p"
//        + " where l.id=t.leagueId and t.captainId=p.id";

        if (more){
            try {
                    id = rs.getLong(1);
                    teamName = rs.getString(2);
                    /*league = objectLayer.createLeague(rs.getString(5), rs.getString(13), rs.getString(12), 
                    		rs.getBoolean(7), (int)rs.getLong(8), (int)rs.getLong(9), (int)rs.getLong(10), (int)rs.getLong(11));
                    captain = objectLayer.createStudent(rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18),
                    		rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22));
                    */
                    more = rs.next();
            }
            catch( Exception e){
                throw new NoSuchElementException( "TeamIterator: No next Team object; root cause: " + e );
            }

            Team team;
			try {
				//System.out.println("In the loop");
				team = objectLayer.createTeam(teamName);
				team.setId( id );
				//team.setParticipatesInLeague(league);
				//team.setCaptain(captain);

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


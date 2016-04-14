package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;

import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class TeamManager {
	
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public TeamManager(Connection conn, ObjectLayer objectLayer){
	this.conn = conn;
	this.objectLayer = objectLayer;
    }//constructor
                                                                                                                           
    public void save(Team team) throws RDException{
        String insertTeamSql = "insert into team ( name, leagueid, captainid ) values ( ?, ?, ? )";              
        String updateTeamSql = "update team  set name = ?, leagueId = ?, captainId = ?";          
        PreparedStatement stmt;
        int inscnt;
        long teamId;

        try{

            if(!team.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement (insertTeamSql);
            else
                stmt = (PreparedStatement) conn.prepareStatement (updateTeamSql);

            if(team.getName() != null)
                stmt.setString(2, team.getName());
            else
                throw new RDException("TeamManager.save: can't save a Team: teamName undefined");

            if(team.getParticipatesInLeague() != null)
                stmt.setLong(3, team.getParticipatesInLeague().getId());
            else
                throw new RDException("TeamManager.save: can't save a Team: teamName undefined");

            if(team.getCaptain() != null)
                stmt.setLong(4, team.getCaptain().getId());
            else
                throw new RDException("TeamManager.save: can't save a Team: captainId undefined");

            //if the team is persistent, set the Id.
            if (team.isPersistent())
                stmt.setLong(1, team.getId());

            inscnt = stmt.executeUpdate();

            if (!team.isPersistent()){
                if (inscnt >= 1){
                    String sql = "select last_insert_id()";
                    if (stmt.execute(sql)){

                        ResultSet r = stmt.getResultSet();



                        while (r.next()){


                            teamId = r.getLong( 1 );
                            if (teamId > 0)
                                team.setId(teamId);
                        }
                    }
                }
                else
                    throw new RDException("TeamManager.save: failed to save a Team");
            }
            else{
                if (inscnt < 1)
                    throw new RDException("TeamManager.save: failed to save a Team");
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "TeamManager.save: failed to save a Team: " + e );
        }
    }//save

    public Iterator<Team> restore(Team team) throws RDException{
        String selectTeamSql = "select id, name, leagueId, established, captainId";
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

        if (team != null){
            if (team.getId() >= 0)
                query.append( " where id = " + team.getId()); //team id is unique, so it is sufficient to get team
           
            else if (team.getName() != null) //team name is unique, so it is sufficient to get team
                query.append( " where team name = '" + team.getName() + "'");
           
            else{
                if (team.getParticipatesInLeague() != null)
                    condition.append( " leagueId = '" + team.getParticipatesInLeague().getId() + "'");

                if (team.getCaptain() != null)
                    condition.append( " captainId = '" + team.getCaptain().getId() + "'");


                if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }

            }

        }

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                return new TeamIterator(r, objectLayer);
            }

        }
        catch(Exception e){
            throw new RDException( "TeamManager.restore: Could not restore persistent Team object; Root cause: " + e);
        }
        throw new RDException("TeamManager.restore: Could not restore persistent Team object");

    }//restore

    public void delete(Team team){
        String               deleteTeamSql = "delete from team where id = ?";              
        PreparedStatement    stmt = null;
        
        // form the query based on the given Team object instance
        if( !team.isPersistent() ) // is the Team object persistent?  If not, nothing to actually delete
            return;
        
        try {
            
            //DELETE t1, t2 FROM t1, t2 WHERE t1.id = t2.id;
            //DELETE FROM t1, t2 USING t1, t2 WHERE t1.id = t2.id;
            stmt = (PreparedStatement) conn.prepareStatement( deleteTeamSql );
            
            stmt.setLong( 1, team.getId() );
            
            stmt.executeUpdate();
         
        }
        catch( SQLException e ) {
            System.out.println("Error: " + e);
        }

    }//delete


}


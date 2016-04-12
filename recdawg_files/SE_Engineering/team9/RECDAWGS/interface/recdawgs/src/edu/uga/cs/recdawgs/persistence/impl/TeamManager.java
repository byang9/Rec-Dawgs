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



public class TeamManager(){
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public TeamManager(Connection conn, ObjectLayer objectLayer){
	this.conn = conn;
	this.objectLayer = objectLayer;
    }//constructor
                                                                                                                           
    public void save(Team team) throws RDException{
        String               insertTeamSql = 
        "insert into team ( name, leagueId, established, captainId) 
        values ( ?, ?, ?, ? )";              
        String               updateTeamSql = 
        "update team  set name = ?, leagueId = ?, established = ?, captainId = ?";              
        PreparedStatement    stmt;
        int                  inscnt;
        long                 teamId;

        try{

            if(!team.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement (insertPersonSql);
            else
                stmt = (PreparedStatement) conn.prepareStatement (updatePersonSql);

            if(team.getTeamName() != null)
                stmt.setString(1, team.getTeamName());
            else
                throw new RDException("TeamManager.save: can't save a Team: teamName undefined");

            if(team.getLeagueId( != null))
                stmt.setString(2, team.getLeagueId());
            else
                throw new RDException("TeamManager.save: can't save a Team: teamName undefined");

            if(team.getEstablishedOn() != null ) {
                java.util.Date jDate = club.getEstablishedOn();
                java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
                stmt.setDate( 3,  sDate );
            }
            else
                throw new RDException("TeamManager.save: can't save a Team: the date of establishment is undefined");

            if(team.getCaptainId() != null)
                stmt.setString(4, team.getCaptainId());
            else
                throw new RDException("TeamManager.save: can't save a Team: captainId undefined");

            //if the team is persistent, set the Id.
            if (team.isPersistent())
                stmt.setLong(5, team.getId());

            inscnt = smt.executeUpdate();

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
                    throw new RDException("TeamManager.save: failed to save a Team")
            }
            else{
                if (inscnt < 1)
                    throw new RDException("TeamManager.save: failed to save a Team")
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new ClubsException( "TeamManager.save: failed to save a Team: " + e );
        }
    }//save

    public Iterator<Team> restore(Team team) throws RDException{
        String selectTeamSql = "select id, name, leagueId, established, captainId";
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

        if (modelTeam != null){
            if (modelTeam.getId() >= 0)
                query.append( " where id = " + modelTeam.getId()); //team id is unique, so it is sufficient to get team
           
            else if (modelTeam.getTeamName() != null) //team name is unique, so it is sufficient to get team
                query.append( " where team name = '" + modelTeam.getTeamName() + "'");
           
            else{
                if (modelTeam.getLeagueId != null)
                    condition.append( " leagueId = '" + modelTeam.getLeagueId() + "'");

                if (modelTeam.getEstablishedOn() != null)
                    condition.append( " established = '" + modelTeam.getEstablishedOn()+ "'");

                if (modelTeam.captainId() != null)
                    condition.append( " captainId = '" + modelTeam.getCaptainId()+ "'");


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
        throw new RDException("TeamManager.restore: Could not restore persistent Team object")

    }//restore

    public void delete(Team team){
        String               deleteTeamSql = "delete from team where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        // form the query based on the given Team object instance
        if( !team.isPersistent() ) // is the Team object persistent?  If not, nothing to actually delete
            return;
        
        try {
            
            //DELETE t1, t2 FROM t1, t2 WHERE t1.id = t2.id;
            //DELETE FROM t1, t2 USING t1, t2 WHERE t1.id = t2.id;
            stmt = (PreparedStatement) conn.prepareStatement( deleteTeamSql );
            
            stmt.setLong( 1, team.getId() );
            
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 0 ) {
                throw new RDException( "PersonManager.delete: failed to delete this Person" );
            }
        }
        catch( SQLException e ) {
            throw new RDException( "PersonManager.delete: failed to delete this Person: " + e.getMessage() );
        }

    }//delete


}


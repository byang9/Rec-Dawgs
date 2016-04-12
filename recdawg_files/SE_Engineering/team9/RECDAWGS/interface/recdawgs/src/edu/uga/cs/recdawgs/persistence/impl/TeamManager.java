package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

package edu.uga.cs.recdawgs.RDException;

package edu.uga.cs.recdawgs.entity.Team;
package edu.uga.cs.recdawgs.object.ObjectLayer;



public class TeamManager(){
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public TeamManager(Connection conn, ObjectLayer objectLayer){
	this.conn = conn;
	this.objectLayer = objectLayer;
    }

    //TODO throws Exception                                                                                                                             
    public void save(Team team){
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
    }

    public Iterator<Team> restore(Team team) throws RDException{
        String selectTeamSql = "select id, username, userpass, email, firstname, lastname, address, phone from person";
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

    }

    public void delete(Team team){

    }


}


package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
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
        String insertTeamSql = "insert into team ( name, leagueId, captainId ) values ( ?, ?, ? )";              
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
    
    public void save(Student student, Team team) throws RDException{
        String insertTeamSql = "insert into team ( name, leagueId, captainId ) values ( ?, ?, ? )";              
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
                stmt.setLong(4, student.getId());
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

    public Iterator<Team> restore(Student student) throws RDException{
        String selectTeamSql = "select id, username from person";
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

        if (student != null){
            if (student.getId() >= 0)
                query.append(" where id = " + student.getId()); //team id is unique, so it is sufficient to get student
            else if (student.getUserName() != null)
                query.append(" where username = " + student.getUserName()); //team id is unique, so it is sufficient to get student
        }

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                if (stmt.execute("select name, leagueid, established, captainid from team where captainid = " + r.getLong(1))) {
                	ResultSet r2 = stmt.getResultSet();
                	return new TeamIterator(r2, objectLayer);
                }
            }

        }
        catch(Exception e){
            throw new RDException( "TeamManager.restore: Could not restore persistent Team object; Root cause: " + e);
        }
        throw new RDException("TeamManager.restore: Could not restore persistent Team object");

    }//restore


    //restoreTeamParticipatesInLeague
    public Iterator<Team> restore(League league) throws RDException{
        String selectTeamSql = "select * team";
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

        if (league != null){
            if (league.getId() >= 0)
                query.append(" where leagueid = " + league.getId()); //team id is unique, so it is sufficient to get student
        }

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                if (stmt.execute("select name, leagueid, captainid from team where leagueid = " + r.getLong(1))) {
                    ResultSet r2 = stmt.getResultSet();
                    return new TeamIterator(r2, objectLayer);
                }
            }

        }
        catch(Exception e){
            throw new RDException( "TeamManager.restore: Could not restore persistent Team object; Root cause: " + e);
        }
        throw new RDException("TeamManager.restore: Could not restore persistent Team object");

    }//restore


    
    public Student restoreCaptain(Team team) throws RDException{
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
                if (stmt.execute("select id, firstname, lastname, username, password, email, studentID, major, address from person where id = " + r.getLong(1))) {
                	ResultSet r2 = stmt.getResultSet();
                	return objectLayer.createStudent(r2.getString(2), r2.getString(3), r2.getString(4), r2.getString(5), r2.getString(6), r2.getString(8), r2.getString(9), r2.getString(10));
                }
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
    
    public void delete(Student student, Team team){
        String               deleteTeamSql = "update team set captainid=null where id = " + team.getId();              
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

    public void delete(Team team, League league){
        String               deleteTeamLeagueSql = "update team set leagueid = null where id = " + team.getId();              
        PreparedStatement    stmt = null;
        
        // form the query based on the given Team object instance
        if( !team.isPersistent() ) // is the Team object persistent?  If not, nothing to actually delete
            return;
        
        try {
            
            //DELETE t1, t2 FROM t1, t2 WHERE t1.id = t2.id;
            //DELETE FROM t1, t2 USING t1, t2 WHERE t1.id = t2.id;
            stmt = (PreparedStatement) conn.prepareStatement( deleteTeamLeagueSql );
            
            stmt.setLong( 1, team.getId() );
            
            stmt.executeUpdate();
         
        }
        catch( SQLException e ) {
            System.out.println("Error: " + e);
        }

    }//delete


}


package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class ObjectFromID {

	private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public ObjectFromID(Connection conn, ObjectLayer objectLayer){
    	this.conn = conn;
    	this.objectLayer = objectLayer;
    }//constructor
    
    public Team getTeamFromID(long id) {
    	String selectTeamSql = "select id, name, leagueId, established, captainId from team where id = " + id;
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                return objectLayer.createTeam(r.getString(1));
            }

        }
        catch(Exception e){
        	
        }
        return null;
    }
    
    public Team getMatchFromID(long id) {
    	String selectTeamSql = "select id, name, leagueId, established, captainId from team where id = " + id;
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                Team team = objectLayer.createTeam(r.getString(2));
                team.setCaptain(getStudentFromID(r.getLong(5)));
                team.setParticipatesInLeague(getLeagueFromID(r.getLong(3)));
                return team;
            }

        }
        catch(Exception e){
        	
        }
        return null;
    }
	
    public Student getStudentFromID(long id) {
    	String selectTeamSql = "select id, firstname, lastname, username, password, email, studentID, major, address from student where id = " + id;
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );

        query.append ( selectTeamSql );

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                Student student = objectLayer.createStudent(r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getString(6), r.getString(8), r.getString(9), r.getString(10));
                student.setId(r.getLong(1));
                return student;
            }

        }
        catch(Exception e){
        	
        }
        return null;
    }
    
    public League getLeagueFromID(long id) {
    	String selectTeamSql = "select id, name, winnerid, isIndoor, minTeams, maxTeams, minTeamMembers, maxTeamMembers, matchRules, leagueRules from league where id = " + id;
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );

        query.append ( selectTeamSql );

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                League league = objectLayer.createLeague(r.getString(2), r.getString(10), r.getString(9), r.getBoolean(4), (int)r.getLong(5), (int)r.getLong(6), (int)r.getLong(7), (int)r.getLong(8));
                league.setId(r.getLong(1));
                if (r.getLong(3) >= 0)
                	league.setWinnerOfLeague(getTeamFromID(r.getLong(3)));
                return league;
            }

        }
        catch(Exception e){
        	
        }
        return null;
    }
    
}

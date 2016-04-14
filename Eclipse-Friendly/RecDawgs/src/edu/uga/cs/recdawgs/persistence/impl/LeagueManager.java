package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.object.ObjectLayer;

/**
 * LeagueManager is the class that stores/edits/deletes/restores Leagues from the persistent database.
 *
 * @author Logan Jahnke
 */
class LeagueManager
{
    private ObjectLayer objectLayer = null;
    private Connection conn = null;
    
    public LeagueManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(League league) throws RDException {
        String insertLeagueSql = "insert into league ( name, winnerId, isIndoor, minTeams, maxTeams, minTeamMembers, maxTeamMembers, matchRules, leagueRules ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        String updateLeagueSql = "update league set name = ?, winnerId = ?, isIndoor = ?, minTeams = ?, maxTeams = ?, minTeamMembers = ?, maxTeamMembers = ?, matchRules = ?, leagueRules = ? where id = ?";
        PreparedStatement stmt = null;
        int inscnt;
        long leagueID;
                 
        try {
            if (!league.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement(insertLeagueSql);
            else
                stmt = (PreparedStatement) conn.prepareStatement(updateLeagueSql);

            if (league.isPersistent())
                stmt.setLong(1, league.getId());
            
            if (league.getName() != null) // name is unique unique and non null
                stmt.setString(2, league.getName());
            else 
                throw new RDException("LeagueManager.save: can't save a League: name undefined");

            if (league.getWinnerOfLeague() != null) {
                stmt.setLong(3, league.getWinnerOfLeague().getId());
            }

            stmt.setBoolean(4, league.getIsIndoor());
            stmt.setLong(5, league.getMinTeams());
            stmt.setLong(6, league.getMinTeams());
            stmt.setLong(7, league.getMinMembers());
            stmt.setLong(8, league.getMaxMembers());
            stmt.setString(9, league.getMatchRules());
            stmt.setString(10, league.getLeagueRules());

            inscnt = stmt.executeUpdate();

            if (!league.isPersistent()) {
                if (inscnt >= 1) {
                    String sql = "select last_insert_id()";
                    if (stmt.execute(sql)) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while (r.next()) {

                            // retrieve the last insert auto_increment value
                            leagueID = r.getLong(1);
                            if (leagueID > 0)
                                league.setId(leagueID); // set this person's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RDException("LeagueManager.save: failed to save a League");
            }
            else {
                if (inscnt < 1)
                    throw new RDException("LeagueManager.save: failed to save a League"); 
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RDException("LeagueManager.save: failed to save a League: " + e);
        }
    }


    public void saveTeamWinnerOfLeague(Team team, League league) throws RDException {
        String insertLeagueSql = "insert into league ( name, winnerId, isIndoor, minTeams, maxTeams, minTeamMembers, maxTeamMembers, matchRules, leagueRules ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        String updateLeagueSql = "update league set name = ?, winnerId = ?, isIndoor = ?, minTeams = ?, maxTeams = ?, minTeamMembers = ?, maxTeamMembers = ?, matchRules = ?, leagueRules = ? where id = ?";
        PreparedStatement stmt = null;
        int inscnt;
        long leagueID;
                 
        try {
            if (!league.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement(insertLeagueSql);
            else
                stmt = (PreparedStatement) conn.prepareStatement(updateLeagueSql);

            if (league.isPersistent())
                stmt.setLong(1, league.getId());
            
            if (league.getName() != null) // name is unique unique and non null
                stmt.setString(2, league.getName());
            else 
                throw new RDException("LeagueManager.save: can't save a League: name undefined");

            stmt.setLong(3, league.setWinnerOfLeague(team));
            stmt.setBoolean(4, league.getIsIndoor());
            stmt.setLong(5, league.getMinTeams());
            stmt.setLong(6, league.getMinTeams());
            stmt.setLong(7, league.getMinMembers());
            stmt.setLong(8, league.getMaxMembers());
            stmt.setString(9, league.getMatchRules());
            stmt.setString(10, league.getLeagueRules());

            inscnt = stmt.executeUpdate();

            if (!league.isPersistent()) {
                if (inscnt >= 1) {
                    String sql = "select last_insert_id()";
                    if (stmt.execute(sql)) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while (r.next()) {

                            // retrieve the last insert auto_increment value
                            leagueID = r.getLong(1);
                            if (leagueID > 0)
                                league.setId(leagueID); // set this person's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RDException("LeagueManager.save: failed to save a winner of League");
            }
            else {
                if (inscnt < 1)
                    throw new RDException("LeagueManager.save: failed to save a winner of League"); 
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RDException("LeagueManager.save: failed to save a winner of League: " + e);
        }
    }



    public Iterator<League> restore(League league) throws RDException {
        String       selectLeagueSql = "select l.id, l.name, l.winnerID, l.isIndoor, l.minTeams, " +
                                      "l.maxTeams, l.minTeamMembers, l.maxTeamMembers, l.matchRules" +
                                      "l.leagueRules from league l";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);

        condition.setLength(0);
        
        // form the query based on the given League object instance
        query.append(selectLeagueSql);
        
        if (league != null) {
            if (league.getId() >= 0) // id is unique, so it is sufficient to get a league
                query.append( " and id = " + league.getId());
            else if (league.getName() != null) // leagueName is unique, so it is sufficient to get a league
                query.append(" and name = '" + league.getName() + "'");
            else {

                if (league.getWinnerOfLeague() != null)
                    condition.append( " and winnerID = '" + league.getWinnerOfLeague().getId() + "'");   

                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" isIndoor = '" + league.getIsIndoor() + "'");
                
                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" minTeams = '" + league.getMinTeams() + "'");
            
                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" maxTeams = '" + league.getMaxTeams() + "'");
            
                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" minTeamMembers = '" + league.getMinTeams() + "'");
            
                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" maxTeamMembers = '" + league.getMaxMembers() + "'");
            
                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" matchRules = '" + league.getMatchRules() + "'");
            
                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" leagueRules = '" + league.getLeagueRules() + "'");
            }
        }
        
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            if (stmt.execute(query.toString())) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new LeagueIterator(r, objectLayer);
            }
        }
        catch (Exception e) {      // just in case...
            throw new RDException( "LeagueManager.restore: Could not restore persistent League object; Root cause: " + e );
        }

        throw new RDException( "LeagueManager.restore: Could not restore persistent League object" );
    }



    //restoreTeamParticipatesInLeague
    public League restore(Team team) throws RDException{
        String selectTeamSql = "Select l.id, l.name, l.winnerId, l.isIndoor, l.minTeams, l.maxTeams, l.minTeamMembers,
                            l.maxTeamMembers, l.matchRules, l.leagueRules 
                            from league l, team t";
        Statement stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );

        query.append ( selectTeamSql );

        if (team != null){
            if (team.getLeagueId() >= 0)
                query.append( " where leagueId = " + team.getLeagueId()); //team id is unique, so it is sufficient to get team
                    
        }

        try {
            stmt = conn.createStatement();

            if (stmt.execute(query.toString())){
                ResultSet r = stmt.getResultSet();
                if (stmt.execute("select name, leagueRules, matchRules, isIndoor, minTeams, maxTeams, minPlayers, maxPlayers where id = " + r.getLong(1))) {
                    //createLeague( String name, String leagueRules, String matchRules, 
                    //boolean isIndoor, int minTeams, int maxTeams, int minPlayers, int maxPlayers )

                    ResultSet r2 = stmt.getResultSet();
                    return objectLayer.createLeague(r2.getString(2), r2.getString(3), r2.getString(4), r2.getBoolean(5), r2.getInteger(6), r2.getInteger(8), r2.getInteger(9), r2.getInteger(10));
                }
            }

        }
        catch(Exception e){
            throw new RDException( "LeagueManager.restore: Could not restore persistent League object; Root cause: " + e);
        }
        throw new RDException("LeagueManager.restore: Could not restore persistent League object");

    }//restore




    public void delete(League league) throws RDException {
        String               deleteLeagueSql = "delete from league where id = ?";     
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !league.isPersistent() ) // is the league object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement(deleteLeagueSql);         
            stmt.setLong(1, league.getId());
            inscnt = stmt.executeUpdate();
            if(inscnt == 1) {
                return;
            }
            else
                throw new RDException("LeagueManager.delete: failed to delete a League");
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new RDException( "LeagueManager.delete: failed to delete a League: " + e );        }
    }
}

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.Person;
import edu.uga.cs.recdawgs.object.ObjectLayer;

/**
 * LeagueManager is the class that stores/edits/deletes/restores Leagues from the peresistent database.
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
        String insertLeagueSql = "insert into league ( name, winnerID, isIndoor, minTeams, maxTeams, minTeamMembers, maxTeamMembers, matchRules, leagueRules ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        String updateLeagueSql = "update league set name = ?, winnerID = ?, isIndoor = ?, minTeams = ?, maxTeams = ?, minTeamMembers = ?, maxTeamMembers = ?, matchRules = ?, leagueRules = ? where id = ?";
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
                stmt.setLong(3, league.getWinnerOfLeague().getID());
            }

            if (league.getIsIndoor() != null)
                stmt.setBoolean(4, league.getIsIndoor());
            else
                throw new RDException("LeagueManager.save: can't save a League: isIndoor is not set");
            
            if (league.getMinTeams() != null)
                stmt.setLong(5, league.getMinTeams());
            else
                throw new RDException("LeagueManager.save: can't save a League: minTeams is not set");
            
            if (league.getMaxTeams() != null)
                stmt.setLong(6, league.getMinTeams());
            else
                throw new RDException("LeagueManager.save: can't save a League: maxTeams is not set");
            
            if (league.getMinMembers()) != null)
                stmt.setLong(7, league.getMinMembers());
            else
                throw new RDException("LeagueManager.save: can't save a League: minMembers is not set");
            
            if (league.getMaxMembers()) != null)
                stmt.setLong(8, league.getMaxMembers());
            else
                throw new RDException("LeagueManager.save: can't save a League: maxMembers is not set");
            
            if (league.getMatchRules()) != null)
                stmt.setString(9, league.getMatchRules());
            else
                throw new RDException("LeagueManager.save: can't save a League: matchRules is not set");
            
            if (league.getLeagueRules()) != null)
                stmt.setString(10, league.getLeagueRules());
            else
                throw new RDException("LeagueManager.save: can't save a League: leagueRules is not set");

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
                            leagueId = r.getLong(1);
                            if (leagueId > 0)
                                league.setId(leagueId); // set this person's db id (proxy object)
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

    public Iterator<League> restore(League league) throws RDException {
        String       selectLeagueSql = "select l.id, l.name, l.winnerID, l.isIndoor, l.minTeams, " +
                                      "l.maxTeams, l.minTeamMembers, l.maxTeamMembers, l.matchRules" +
                                      "l.leagueRules, p.id, p.name, t.id, t.name";
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
                    condition.append( " and winnerID = '" + league.getWinnerOfLeague().getID() + "'");   

                if (league.getIsIndoor() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" isIndoor = '" + league.getIsIndoor() + "'");
                }
                
                if (league.getMinTeams() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" minTeams = '" + league.getMinTeams() + "'");
                }
                
                if (league.getMaxTeams() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" maxTeams = '" + league.getMaxTeams() + "'");
                }
                
                if (league.getMinMembers() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" minTeamMembers = '" + league.getMinTeams() + "'");
                }
                
                if (league.getMaxMembers() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" maxTeamMembers = '" + league.getMaxMembers() + "'");
                }
                
                if (league.getMatchRules() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" matchRules = '" + league.getMatchRules() + "'");
                }
                
                if (league.getLeagueRules() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" leagueRules = '" + league.getLeagueRules() + "'");
                }
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

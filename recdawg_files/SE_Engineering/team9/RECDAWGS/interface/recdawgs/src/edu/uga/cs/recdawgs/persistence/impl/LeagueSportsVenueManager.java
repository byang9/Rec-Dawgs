package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

/**
 * LeagueSportsVenue is the class that stores/edits/deletes/restores the 
 * League and SportsVenue many-to-many relationship from the peresistent database.
 *
 * @author Logan Jahnke
 */
class LeagueSportsVenueManager {
    
    private ObjectLayer objectLayer = null;
    private Connection conn = null;
    
    public LeagueSportsVenueManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(League l, SportsVenue sv) throws RDException {
        String insertRelationSql = "insert into hasVenue ( leagueid, venueid ) values ( ?, ? )";
        String updateRelationSql = "update hasVenue set leagueid = ?, venueid = ? where id = ?";
        PreparedStatement stmt = null;
        int inscnt;
        long relationID;
                 
        try {
            if (!l.isPersistent() || !sv.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement(insertRelationSql);
            else
                stmt = (PreparedStatement) conn.prepareStatement(updateRelationSql);

            if (l.isPersistent())
                stmt.setLong(2, l.getId());
            
            if (sv.isPersistent())
                stmt.setString(3, sv.getId());

            inscnt = stmt.executeUpdate();
            
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RDException("LeagueSportsVenueManager.save: failed to save a Relationship: " + e);
        }
    }

    public Iterator<League> restoreWithSportsVenue(SportsVenue v) throws RDException {
        String       selectSql = "select v.id, v.name, v.address, v.isIndoor";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);

        condition.setLength(0);
        
        // form the query based on the given League object instance
        query.append(selectSql);
        
        if (v != null) {
            if (v.getId() >= 0) // id is unique, so it is sufficient to get a league
                query.append( " and id = " + v.getId());
            else if (v.getName() != null) // vName is unique, so it is sufficient to get a league
                query.append(" and name = '" + v.getName() + "'");
            else {

                if (v.address() != null)
                    condition.append( " and address = '" + v.getAddress() + "'");   

                if (v.getIsIndoor() != null) {
                    if (condition.length() > 0)
                        condition.append(" and");
                    condition.append(" isIndoor = '" + v.getIsIndoor() + "'");
                }
            }
        }
        
        try {

            stmt = conn.createStatement();

            // retrieve the persistent SVIterator object
            if (stmt.execute(query.toString())) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                VenueIterator svI = new VenueIterator(r, objectLayer);
            }
        }
        catch (Exception e) {      // just in case...
            throw new RDException( "LeagueSportsVenueManager.restore: Could not restore persistent League object; Root cause: " + e );
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
            throw new RDException( "LeagueSportsVenueManager.restore: Could not restore persistent League object; Root cause: " + e );
        }

        throw new RDException( "LeagueSportsVenueManager.restore: Could not restore persistent League object" );
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
                throw new RDException("LeagueSportsVenueManager.delete: failed to delete a League");
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new RDException( "LeagueSportsVenueManager.delete: failed to delete a League: " + e );        }
    }
}

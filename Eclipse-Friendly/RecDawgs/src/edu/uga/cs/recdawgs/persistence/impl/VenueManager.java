package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

/**
 * VenueManager is the class that stores/edits/deletes/restores Venues from the peresistent database.
 *
 * @author Logan Jahnke
 */
class VenueManager
{
    private ObjectLayer objectLayer = null;
    private Connection conn = null;
    
    public VenueManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(SportsVenue venue) throws RDException {
        String insertVenueSql = "insert into venue ( name, address, isIndoor, id ) values ( ?, ?, ?, ? )";
        String updateVenueSql = "update venue set name = ?, address = ?, isIndoor = ?, id = ?";
        PreparedStatement stmt = null;
        int inscnt;
        long venueID;
                 
        try {
            if (!venue.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement(insertVenueSql);
            else
                stmt = (PreparedStatement) conn.prepareStatement(updateVenueSql);

            //if (venue.isPersistent())
                stmt.setLong(4, venue.getId());
            
            if (venue.getName() != null) // name is unique unique and non null
                stmt.setString(1, venue.getName());
            else 
                throw new RDException("VenueManager.save: can't save a Venue: name undefined");

            if (venue.getAddress() != null)
                stmt.setString(2, venue.getAddress());
            else
                throw new RDException("VenueManager.save: can't save a Venue: address undefined");

            stmt.setBoolean(3, venue.getIsIndoor());

            inscnt = stmt.executeUpdate();

            if (venue.isPersistent()) {
                if (inscnt >= 1) {
                    String sql = "select last_insert_id()";
                    if (stmt.execute(sql)) { // statement returned a result

                        // retrieve the result
                        ResultSet r = stmt.getResultSet();

                        // we will use only the first row!
                        //
                        while (r.next()) {

                            // retrieve the last insert auto_increment value
                            venueID = r.getLong(1);
                            if (venueID > 0)
                                venue.setId(venueID); // set this person's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RDException("VenueManager.save: failed to save a Venue");
            }
            else {
                if (inscnt < 1)
                    throw new RDException("VenueManager.save: failed to save a Venue"); 
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RDException("VenueManager.save: failed to save a Venue: " + e);
        }
    }

    public Iterator<SportsVenue> restore(SportsVenue venue) throws RDException {
        String       selectVenueSql = "select v.id, v.name, v.address, v.isIndoor from venue v";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);

        condition.setLength(0);
        
        // form the query based on the given Venue object instance
        query.append(selectVenueSql);
        
        if (venue != null) {
            if (venue.getId() >= 0) // id is unique, so it is sufficient to get a Venue
                query.append( " and id = " + venue.getId());
            else if (venue.getName() != null) // VenueName is unique, so it is sufficient to get a Venue
                query.append(" and name = '" + venue.getName() + "'");
            else {

                if (venue.getAddress() != null)
                    condition.append( " and address = '" + venue.getAddress() + "'");   

                if (condition.length() > 0)
                    condition.append(" and");
                condition.append(" isIndoor = '" + venue.getIsIndoor() + "'");
            }
        }
        
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            if (stmt.execute(query.toString())) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new VenueIterator(r, objectLayer);
            }
        }
        catch (Exception e) {      // just in case...
            throw new RDException( "VenueManager.restore: Could not restore persistent Venue object; Root cause: " + e );
        }

        throw new RDException( "VenueManager.restore: Could not restore persistent Venue object" );
    }
    

    public void delete(SportsVenue venue) throws RDException {
        String               deleteVenueSql = "delete from venue where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if(!venue.isPersistent()) // is the Venue object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement(deleteVenueSql);         
            stmt.setLong(1, venue.getId());
            inscnt = stmt.executeUpdate();
            if(inscnt == 1) {
                return;
            }
            else
                throw new RDException("VenueManager.delete: failed to delete a Venue");
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new RDException( "VenueManager.delete: failed to delete a Venue: " + e );        }
    }
}

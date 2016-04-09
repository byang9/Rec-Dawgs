package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.object.ObjectLayer;


public class HasVenueManager() {
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public HasVenueManager(Connection conn, ObjectLayer objectLayer) {
    	this.conn = conn;
    	this.objectLayer = objectLayer;
    }
                                                                                                                         
    public void save(HasVenue hasVenue) throws RDException {
        String insertHasVenueSql = "insert into venue ( leagueid, venueid ) values ( ?, ? )";
        String updateHasVenueSql = "update venue set leagueid = ?, venueid = ?";
        PreparedStatement  stmt = null;
        int inscnt;
        long hasVenueID;


	
    }

    public Iterator<HasVenue> restore(HasVenue hasVenue) throws RDException {

    }

    public void delete(HasVenue hasVenue) throws RDException {
         
    }


}
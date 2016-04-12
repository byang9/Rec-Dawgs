package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uga.Venue.RDException;
import edu.uga.Venue.entity.Venue;
import edu.uga.Venue.object.ObjectLayer;

/**
 * VenueIterator is the class iterates between Venues
 * It acts as a searching tool for Venues.
 * It iterates the Venue proxy objects.
 *
 * @author Logan Jahnke
 */
public class VenueIterator implements Iterator<Venue> {
    private ResultSet    rs = null;
    private ObjectLayer  objectLayer = null;
    private boolean      more = false;

    // these two will be used to create a new object
    public VenueIterator(ResultSet rs, ObjectLayer objectModel) throws RDException {
        this.rs = rs;
        this.objectLayer = objectModel;
        try {
            more = rs.next();
        }
        catch (Exception e) {  // just in case...
            throw new RDException("VenueIterator: Cannot create Venue iterator; root cause: " + e);
        }
    }

    public boolean hasNext() { 
        return more; 
    }

    public Venue next() {
        long   id;
        String name;
        String address;
        boolean isIndoor;
        Venue venue = null;

        if (more) {

            try {
                id = rs.getLong(1);
                name = rs.getString(2);
                address = rs.getString(3);
                isIndoor = rs.getBoolean(4);

                more = rs.next();
            }
            catch (Exception e) {      // just in case...
                throw new NoSuchElementException( "VenueIterator: No next Venue object; root cause: " + e );
            }
            
            try {
                venue = objectLayer.createVenue(name, address, isIndoor);
                venue.setId(id);
            }
            catch (RDException ce) {
                throw new RDException("This shouldn't ever happen: VenueIterator: " + ce);
            }
            
            return Venue;
        }
        else {
            throw new NoSuchElementException("VenueIterator: No next Venue object");
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

};

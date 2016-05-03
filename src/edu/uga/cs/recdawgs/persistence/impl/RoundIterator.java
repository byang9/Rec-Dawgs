package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;


import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Round;


public class RoundIterator implements Iterator<Round> {
    private ResultSet    rs = null;
    private ObjectLayer  objectLayer = null;
    private boolean      more = false;

    // these two will be used to create a new object
    public RoundIterator(ResultSet rs, ObjectLayer objectModel) throws RDException {
        this.rs = rs;
        this.objectLayer = objectModel;
        try {
            more = rs.next();
        }
        catch (Exception e) {  // just in case...
            throw new RDException( "RoundIterator: Cannot create Round iterator; root cause: " + e );
        }
    }

    public boolean hasNext() { 
        return more; 
    }

    public Round next() {
        long        roundNo;
        

        if (more) {

            try {
                roundNo = rs.getLong(2);

                more = rs.next();
            }
            catch (Exception e) {      // just in case...
                throw new NoSuchElementException( "RoundIterator: No next Round object; root cause: " + e );
            }
            
            Round round;
			try {
				round = objectLayer.createRound((int)roundNo);
				return round;
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return null;
        }
        else {
            throw new NoSuchElementException("RoundIterator: No next Round object");
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

};

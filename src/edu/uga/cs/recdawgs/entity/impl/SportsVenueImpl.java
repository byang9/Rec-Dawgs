package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.persistence.impl.Persistent;
import edu.uga.cs.recdawgs.entity.SportsVenue;

public class SportsVenueImpl extends Persistent implements SportsVenue {
    
    private String name;
    private boolean isIndoor;
    private String address;
    
    public SportsVenueImpl(String name, String address, boolean isIndoor) {
		this.name = name;
		this.isIndoor = isIndoor;
		this.address = address;
	}
    
    public SportsVenueImpl() {
		this.name = null;
		this.isIndoor = false;
		this.address = null;
	}


	/** Return the name of this sports venue.
     * @return the name of this sports venue
     */
    public String getName() {
        return name;
    }
    
    
    /** Set the new address for this sports venue.
     * @param name the new address for this sports venue
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /** Return the indoor status of this sports venue.
     * @return the indoor status of this sports venue
     */
    public boolean getIsIndoor() {
        return isIndoor;
    }
    
    
    /** Set the new indoor status for this sports venue.
     * @param isIndoor the new indoor status for this sports venue
     */
    public void setIsIndoor(boolean isIndoor) {
        this.isIndoor = isIndoor;
    }
    
    
    /** Return the address of this sports venue.
     * @return the address of this sports venue
     */
    public String getAddress() {
        return address;
    }
    
    
    /** Set the new address for this sports venue
     * @param address the new address of this sports venue
     */
    public void setAddress(String address) {
        this.address = address;
    }


}

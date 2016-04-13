package edu.uga.cs.recdawgs.entity;

import edu.uga.cs.recdawgs.persistence.Persistable;

/** This class represents a sports venue for playing matches.
 * A sports venue has a name, address, and is classified as an indoor venue or not.
 * The name must be unique across all sports venues.
 *
 */
public interface SportsVenue 
    extends Persistable
{
    /** Return the name of this sports venue.
     * @return the name of this sports venue
     */
    public String getName();
    
    /** Set the new address for this sports venue.
     * @param name the new address for this sports venue
     */
    public void setName( String name );
    
    /** Return the indoor status of this sports venue.
     * @return the indoor status of this sports venue
     */
    public boolean getIsIndoor();
    
    /** Set the new indoor status for this sports venue.
     * @param isIndoor the new indoor status for this sports venue
     */
    public void setIsIndoor( boolean isIndoor );
    
    /** Return the address of this sports venue.
     * @return the address of this sports venue
     */
    public String getAddress();
    
    /** Set the new address for this sports venue
     * @param address the new address of this sports venue
     */
    public void setAddress( String address );
}

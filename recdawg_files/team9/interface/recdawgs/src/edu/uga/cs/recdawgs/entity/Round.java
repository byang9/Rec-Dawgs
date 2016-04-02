package edu.uga.cs.recdawgs.entity;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.persistence.Persistable;

/** This class represents a round of matches played in some sports league.  Each round of
 * matches is numbered (the round number must be positive).
 *
 */
public interface Round 
    extends Persistable
{
    /** Return this round's number.
     * @return the number of this round of matches.
     */
    public int getNumber();

    /** Set the new number for this round of matches.
     * @param number the new number of this round of matches
     * @throws RDException in case number is not positive
    */
    public void setNumber( int number ) throws RDException;
}

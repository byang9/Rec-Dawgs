package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;
import edu.uga.cs.recdawgs.entity.Round;

/** This class represents a round of matches played in some sports league.  Each round of
 * matches is numbered (the round number must be positive).
 *
 */
public class RoundImpl extends Persistent implements Round {
    
    private int roundNumber;
    private League roundLeague;
    
    public RoundImpl(int roundNumber) {
    	this.roundNumber = roundNumber;
    }
    
    public RoundImpl() {
    	this.roundNumber = 0;
    }
    
    /** Return this round's number.
     * @return the number of this round of matches.
     */
    public int getNumber(){
    	return roundNumber;
    }

    /** Set the new number for this round of matches.
     * @param number the new number of this round of matches
     * @throws RDException in case number is not positive
    */
    public void setNumber( int number ) throws RDException{
        if (number >=0){
            roundNumber = number;
        }else{
            throw new RDException("Round: number is not positive");
        }
    }
    
    /** Return the league in which this round of matches is played.
     * @return the league in which this round of matches is played
     */
    public League getLeague(){
    	return roundLeague;
    }

    /** Set the League in which this round of matches is played.
     * @param league the league for this round of matches
     */
    public void setLeague( League league ){
        roundLeague = league;
    }
}

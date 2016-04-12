package edu.uga.cs.recdawgs.entity;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.persistence.Persistable;

/** This class represents a round of matches played in some sports league.  Each round of
 * matches is numbered (the round number must be positive).
 *
 */
public Round 
    extends Persistable
{
    
    private int roundNumber=-1;
    private String roundLeague="";
    /** Return this round's number.
     * @return the number of this round of matches.
     */
    public int getNumber(){
        if(number != -1){
            return roundNumber;
        }
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
        if(roundLeague.length != 0){
            return roundLeague;
        }
    }

    /** Set the League in which this round of matches is played.
     * @param league the league for this round of matches
     */
    public void setLeague( League league ){
        roundLeague = league;
    }
}

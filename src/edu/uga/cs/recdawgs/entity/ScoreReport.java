package edu.uga.cs.recdawgs.entity;

import java.util.Date;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.persistence.Persistable;

/** This class represents a score report for a match between two teams in a sports league.
 * One score report is submitted by each of the two team captains.  If both score reports are
 * the same, the score report becomes official and is recorded in the corresponding Match.
 *
 */
public interface ScoreReport 
    extends Persistable
{
    /** Return the points scored by the home team.
     * @return the points scored by the home team
     */
    public int getHomePoint();

    /** Set the points scored by the home team
     * @param homePoints the points scored by the home team
     * @throws RDException in case homePoints is negative
     */
    public void setHomePoint( int homePoints ) throws RDException;

    /** Return the points scored by the away team.
     * @return the points scored by the away team
     */
    public int getAwayPoints();

    /** Set the points scored by the away team
     * @param awayPoints the points scored by the away team
     * @throws RDException in case awayPoints is negative
     */
    public void setAwayPoints( int awayPoints ) throws RDException;

    /** Return the date of the match.
     * @return the date of the match
     */
    public Date getDate();

    /** Set the new date of the match
     * @param date the new date of the match
     */
    public void setDate( Date date );

    /** Return the match involved in the report.
     * @return the match involved in the report
     */
    public Match getMatch();
    
    /** Set the match involved in the report.
     * @param match the match involved in the report
     * @throws RDException in case the match is null
     */
    public void setMatch( Match match ) throws RDException;
    
    /** Return the student involved in the report.
     * @return the student involved in the report
     */
    public Student getStudent();
    
    /** Set the student involved in the report.
     * @param student the student involved in the report
     * @throws RDException in case the student is null or not the captain of the team involved in the match
     */
    public void setStudent( Student student ) throws RDException;
    
}

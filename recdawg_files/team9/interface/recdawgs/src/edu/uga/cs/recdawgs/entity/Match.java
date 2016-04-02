package edu.uga.cs.recdawgs.entity;

import java.util.Date;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.persistence.Persistable;

/** This class represents a match between two teams participating in the same sports league.
 * The match is played at a sports venue.
 * The match, once completed, should have a results, which is indicated by the points scored by
 * the home team and the away team (the points must not be negative).
 *
 */
public interface Match 
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
     * @throws RDException in case the date is in the past
     */
    public void setDate( Date date ) throws RDException;

    /** Return the indication if this match has been completed.
     * @return the indication if this match has been completed
     */
    public boolean getIsCompleted();

    /** Set the indication if this match has been completed.
     * @param isCompleted the indication if this match has been completed
     */
    public void setIsCompleted( boolean isCompleted );
    
    /** Return the home team of this match.
     * @return the home team of this match
     */
    public Team getHomeTeam();

    /** Set the home team of this match.
     * @param homeTeam the home team of this match
     * @throws RDException in case the homeTeam is null or not participating in the same league as the away team
     */
    public void setHomeTeam( Team homeTeam ) throws RDException;

    /** Return the away team of this match.
     * @return the away team of this match
     */
    public Team getAwayTeam();

    /** Set the away team of this match.
     * @param awayTeam the away team of this match
     * @throws RDException in case the awayTeam is null or not participating in the same league as the home team
     */
    public void setAwayTeam( Team awayTeam ) throws RDException;
    
    /** Return the sports venue of this match.
     * @return the sports venue of this match
     */
    public SportsVenue getSportsVenue();

    /** Set the sports venue of this match.
     * @param sportsVenue the sports venue of this match
     */
    public void setSportsVenue( SportsVenue sportsVenue);

}

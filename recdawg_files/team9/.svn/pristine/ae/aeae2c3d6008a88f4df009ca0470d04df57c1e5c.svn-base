package edu.uga.cs.recdawgs.persistence;

import java.util.Iterator;


import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;




/**
 * This is the interface to the Persistence Layer subsystem of the RecDawgs system.  This layer 
 * provides operations for storing, restoring, and deleting of entity class objects and links 
 * existing among them.
 * <p>
 * Each entity class <code><i>Class</i></code> has three types of operations: 
 * <ul>
 *  <li><code>restore<i>Class</i></code>, which restores entity objects satisfying a search criteria specified by a 
 *        <code>model<i>Class</i></code> entity,
 *  <li><code>store<i>Class</i></code>, which stores a new, or updates an existing entity object, and 
 *  <li><code>delete<i>Class</i></code>, which removes an existing entity object from the persistent data store. 
 * </ul>
 * <p>
 * Each association between two entity classes <code><i>ClassA</i></code> and <code><i>ClassB</i></code> has 
 * three types of operations: 
 * <ul>
 * <li><code>store<i>ClassAClassB</i></code>, which is used to store a link between two instances of <code><i>ClassA</i></code> 
 *     and <code><i>ClassB</i></code> in the persistent data store,</li>
 * <li><code>restore<i>ClassAClassB</i></code>, two overloaded versions are used to restore the link from 
 *     <code><i>ClassA</i></code> to <code><i>ClassB</i></code> and from
 *     <code><i>ClassB</i></code> to <code><i>ClassA</i></code>, and</li>
 * <li><code>delete<i>ClassAClassB</i></code>, which is used to remove the link connecting two object instances 
 *      from the persistent data store.</li> 
 * </ul>
 * <p>
 * In case there are two associations connecting the same two entity classes, the names of the association-related operations include
 * the name of the association between the classes.  Furthermore, depending on the multiplicity of the association endpoint,
 * the return value is either <code><i>ClassA</i></code> (<code><i>ClassB</i></code>), if the multiplicity is <i>one</i> 
 * or <i>optional</i>, or an <code>Iterator&lt;<i>ClassA</i>&gt;</code> (<code>Iterator&lt;<i>ClassB</i>&gt;</code>), if 
 * the multiplicity is <i>many</i>. 
 */
public interface PersistenceLayer
{
    
    /** 
     * Restore all Administrator objects that match attributes of the model Administrator.
     * @param modelAdministrator the model Administrator; if null is provided, all Administrator objects will be returned
     * @return an Iterator of the located Administrator objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Administrator> restoreAdministrator( Administrator modelAdministrator ) throws RDException;
    
    /** 
     * Store a given Administrator object in the persistent data store.  
     * If the Administrator object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param administrator the Administrator to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeAdministrator( Administrator administrator ) throws RDException;
    
    /** 
     * Delete a given Administrator object from the persistent data store.
     * @param administrator the Administrator to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteAdministrator( Administrator administrator ) throws RDException; 
    
    /** 
     * Restore all Student objects that match attributes of the model Student.
     * @param modelStudent the model Student; if null is provided, all Student objects will be returned
     * @return an Iterator of the located Student objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Student> restoreStudent( Student modelStudent ) throws RDException;
    
    /** 
     * Store a given Student object in the persistent data store.
     * If the Student object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param student the Student to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeStudent( Student student ) throws RDException;
    
    /** 
     * Delete a given Student object from the persistent data store.
     * @param student the Student to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteStudent( Student student ) throws RDException;      

    /** 
     * Restore all Match objects that match attributes of the model Match.
     * @param modelMatch the model Match; if null is provided, all Match objects will be returned
     * @return an Iterator of the located Match objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Match> restoreMatch( Match modelMatch ) throws RDException;
    
    /** 
     * Store a given Match object in the persistent data store.
     * If the Match object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param match the Match to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeMatch( Match match ) throws RDException;
    
    /** 
     * Delete a given Match object from the persistent data store.
     * @param match the Match to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteMatch( Match match ) throws RDException;      
    
    /** 
     * Restore all SportsVenue objects that match sportsVenue attributes of the model SportsVenue.
     * @param modelSportsVenue the model SportsVenue; if null is provided, all SportsVenue objects will be returned
     * @return an Iterator of the located SportsVenue objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<SportsVenue> restoreSportsVenue( SportsVenue modelSportsVenue ) throws RDException;
    
    /** 
     * Store a given SportsVenue object in the persistent data store.
     * If the SportsVenue object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param sportsVenue the SportsVenue to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeSportsVenue( SportsVenue sportsVenue ) throws RDException;
    
    /** 
     * Delete a given SportsVenue object from the persistent data store.
     * @param sportsVenue the SportsVenue to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteSportsVenue( SportsVenue sportsVenue ) throws RDException;      

    /** 
     * Restore all Team objects that match team attributes of the model Team.
     * @param modelTeam the model Team; if null is provided, all Team objects will be returned
     * @return an Iterator of the located Team objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Team> restoreTeam( Team modelTeam ) throws RDException;
    
    /** 
     * Store a given Team object in the persistent data store.
     * If the Team object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param team the Team to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeTeam( Team team ) throws RDException;
    
    /** 
     * Delete a given Team object from the persistent data store.
     * @param team the Team to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteTeam( Team team ) throws RDException;      

    /** 
     * Restore all League objects that match league attributes of the model League.
     * @param modelLeague the model League; if null is provided, all League objects will be returned
     * @return an Iterator of the located League objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<League> restoreLeague( League modelLeague ) throws RDException;
    
    /** 
     * Store a given League object in the persistent data store.
     * If the League object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param league the League to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeLeague( League league ) throws RDException;
    
    /** 
     * Delete a given League object from the persistent data store.
     * @param league the League to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteLeague( League league ) throws RDException;      

    /** 
     * Restore all ScoreReport objects that match modelScoreReport attributes of the model ScoreReport.
     * @param modelScoreReport the model ScoreReport; if null is provided, all ScoreReport objects will be returned
     * @return an Iterator of the located ScoreReport objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<ScoreReport> restoreScoreReport( ScoreReport modelScoreReport ) throws RDException;
    
    /** 
     * Store a given ScoreReport object in the persistent data store.
     * If the ScoreReport object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param scoreReport the ScoreReport to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeScoreReport( ScoreReport scoreReport ) throws RDException;
    
    /** 
     * Delete a given ScoreReport object from the persistent data store.
     * @param scoreReport the ScoreReport to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteScoreReport( ScoreReport scoreReport ) throws RDException;      

    /** 
     * Restore all Round objects that match modelRound attributes of the model Round.
     * @param modelRound the model Round; if null is provided, all Round objects will be returned
     * @return an Iterator of the located Round objects
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Round> restoreRound( Round modelRound ) throws RDException;
    
    /** 
     * Store a given Round object in the persistent data store.
     * If the Round object to be stored is already persistent, the persistent 
     * object in the data store is updated.
     * @param round the Round to be stored
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeRound( Round round ) throws RDException;
    
    /** 
     * Delete a given Round object from the persistent data store.
     * @param round the Round to be deleted
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteRound( Round round ) throws RDException;      





















    // Associations
    //

    // Student--isCaptainOf-->Team;   multiplicity: 1 - *
    //
    /** 
     * Store a link between a Student and a Team captained by the Student.
     * @param student the Student to be linked
     * @param team the Team to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeStudentCaptainOfTeam( Student student, Team team ) throws RDException;

    /** 
     * Return the Student who is the captain a given Team.
     * @param team the Team
     * @return the Student who is the captain of the Team
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Student restoreStudentCaptainOfTeam( Team team ) throws RDException;

    /** 
     * Return Teams captained by a given Student.
     * @param student the Student
     * @return an Iterator with all Teams captained by the Student
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Team> restoreStudentCaptainOfTeam( Student student ) throws RDException;

    /** 
     * Delete a link between a Student and a Team captained by the Student.
     * @param student the Student
     * @param team the Team
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteStudentCaptainOfTeam( Student student, Team team ) throws RDException;

    // Student--isMemberOf-->Team;   multiplicity: 1..* - *
    //
    /** 
     * Store a link between a Student and a Team of which the Student is a member.
     * @param student the Student to be linked
     * @param team the Team to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeStudentMemberOfTeam( Student student, Team team ) throws RDException;

    /** 
     * Return Students who are members of a given Team.
     * @param team the Team
     * @return an Iterator with all Students who are members of the team
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Student> restoreStudentMemberOfTeam( Team team ) throws RDException;

    /** 
     * Return Teams of which a given Student is a member.
     * @param student the Student
     * @return an Iterator with all Teams in which the Student is a member
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Team> restoreStudentMemberOfTeam( Student student ) throws RDException;

    /** 
     * Delete a link between a Student and a Team of which the Student is a member.
     * @param student the Student
     * @param team the Team
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteStudentMemberOfTeam( Student student, Team team ) throws RDException;

    // Team--isHomeTeam-->Match;   multiplicity: 1 - *
    //
    /** 
     * Store a link between a Team and a Match in which the team is the home team.
     * @param team the Team to be linked
     * @param match the Match to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeTeamHomeTeamMatch( Team team, Match match ) throws RDException;

    /** 
     * Return the Team which is the home team in a given Match.
     * @param match the Match
     * @return the Team which is the home team in the Match
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Team restoreTeamHomeTeamMatch( Match match ) throws RDException;

    /** 
     * Return Matches in which a given Team is the home team.
     * @param team the Team
     * @return an Iterator with all Matches in which the Team is the home team
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Match> restoreTeamHomeTeamMatch( Team team ) throws RDException;

    /** 
     * Delete a link between a Team and a Match in which the team is the home team.
     * @param team the Team
     * @param match the Match
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteTeamHomeTeamMatch( Team team, Match match ) throws RDException;

    // Team--isAwayTeam-->Match;   multiplicity: 1 - *
    //
    /** 
     * Store a link between a Team and a Match in which the team is the away team.
     * @param team the Team to be linked
     * @param match the Match to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeTeamAwayTeamMatch( Team team, Match match ) throws RDException;

    /** 
     * Return the Team which is the away team in a given Match.
     * @param match the Match
     * @return the Team which is the away team in the Match
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Team restoreTeamAwayTeamMatch( Match match ) throws RDException;

    /** 
     * Return Matches in which a given Team is the away team.
     * @param team the Team
     * @return an Iterator with all Matches in which the Team is the away team
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Match> restoreTeamAwayTeamMatch( Team team ) throws RDException;

    /** 
     * Delete a link between a Team and a Match in which the team is the away team.
     * @param team the Team
     * @param match the Match
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteTeamAwayTeamMatch( Team team, Match match ) throws RDException;

    // Team--participatesIn-->League;   multiplicity: * - 1
    //
    /** 
     * Store a link between a Team and a League in which the team participates.
     * @param team the Team to be linked
     * @param league the League to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeTeamParticipatesInLeague( Team team, League league ) throws RDException;

    /** 
     * Return the Teams which participate in a given League.
     * @param league the League
     * @return an Iterator with all Teams which participate in the League
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Team> restoreTeamParticipatesInLeague( League league ) throws RDException;

    /** 
     * Return the League in which a given Team participates.
     * @param team the Team
     * @return the League in which the Team participates
     * @throws RDException in case an error occurred during the restore operation 
     */
    public League restoreTeamParticipatesInLeague( Team team ) throws RDException;

    /** 
     * Delete a link between a Team and a League in which the team participates.
     * @param team the Team
     * @param league the League
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteTeamParticipatesInLeague( Team team, League league ) throws RDException;

    // Team--isWinnerOf-->League;   multiplicity: 0..1 - 0..1
    //
    /** 
     * Store a link between a Team and a League for which the team is the winner.
     * @param team the Team to be linked
     * @param league the League to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeTeamWinnerOfLeague( Team team, League league ) throws RDException;

    /** 
     * Return the Team which is the winner of a given League.
     * @param league the League
     * @return a Team which is the winner of the League
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Team restoreTeamWinnerOfLeague( League league ) throws RDException;

    /** 
     * Return the League won by a given Team.
     * @param team the Team
     * @return the League in won by the Team
     * @throws RDException in case an error occurred during the restore operation 
     */
    public League restoreTeamWinnerOfLeague( Team team ) throws RDException;

    /** 
     * Delete a link between a Team and a League won by the Team.
     * @param team the Team
     * @param league the League
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteTeamWinnerOfLeague( Team team, League league ) throws RDException;

    // League--has-->SportsVenue;   multiplicity: * - *
    //
    /** 
     * Store a link between a League and a SportsVenue used by the league.
     * @param league the League to be linked
     * @param sportsVenue the SportsVenue to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException;

    /** 
     * Return the Leagues using a given SportsVenue.
     * @param sportsVenue the SportsVenue
     * @return an Iterator of all Leagues using the SportsVenue
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<League> restoreLeagueSportsVenue( SportsVenue sportsVenue ) throws RDException;

    /** 
     * Return SportsVenues used by a given League.
     * @param league the League
     * @return an Iterator with all SportsVenues used by the League
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<SportsVenue> restoreLeagueSportsVenue( League league ) throws RDException;

    /** 
     * Delete a link between a League and a SportsVenue used by the league.
     * @param league the League
     * @param sportsVenue the SportsVenue
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException;

    // League--includes-->Round;   multiplicity: 1 - *
    //
    /** 
     * Store a link between a League and a Round of matches in the league.
     * @param league the League to be linked
     * @param round the Round to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeLeagueRound( League league, Round round ) throws RDException;

    /** 
     * Return the League in which a given Round of matches is played.
     * @param round the Round
     * @return a League in with the rounds of matches is played
     * @throws RDException in case an error occurred during the restore operation 
     */    
    // Not needed, since the association is not navigable in this direction
    // public League restoreLeagueRound( Round round ) throws RDException;

    /** 
     * Return Rounds of matches for a given League.
     * @param league the League
     * @return an Iterator with all Rounds of matches in the League
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Round> restoreLeagueRound( League league ) throws RDException;

    /** 
     * Delete a link between a League and a Round of matches in the league.
     * @param league the League
     * @param round the Round
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteLeagueRound( League league, Round round ) throws RDException;

    // Round--includes-->Match;   multiplicity: 1 - 1..*
    //
    /** 
     * Store a link between a Round and a Match to be played in the Round.
     * @param round the Round to be linked
     * @param match the Match to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeRoundMatch( Round round, Match match ) throws RDException;

    /** 
     * Return Matches played in a given Round.
     * @param round the Round
     * @return an Iterator with all matches played in the round
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Match> restoreRoundMatch( Round round ) throws RDException;

    /** 
     * Return the round in which a given Match is played.
     * @param match the Match
     * @return a Round in which the match is played
     * @throws RDException in case an error occurred during the restore operation 
     */
    // Not needed, since the association is not navigable in this direction
    // public Round restoreRoundMatch( Match match ) throws RDException;

    /** 
     * Delete a link between a Round and a Match to be played in the Round.
     * @param round the Round
     * @param match the Match
     * @throws RDException in case an error occurred during the store operation 
     */
    public void deleteRoundMatch( Round round, Match match ) throws RDException;

    // Match--isPlayedAt-->SportsVenue;   multiplicity: * - 1
    //
    /** 
     * Store a link between a Match and a SportsVenue used in the match.
     * @param match the Match to be linked
     * @param sportsVenue the SportsVenue to be linked
     * @throws RDException in case an error occurred during the store operation 
     */
    public void storeMatchSportsVenue( Match match, SportsVenue sportsVenue ) throws RDException;

    /** 
     * Return the Matches played at a given SportsVenue.
     * @param sportsVenue the SportsVenue
     * @return an Iterator of all Matches played at the SportsVenue
     * @throws RDException in case an error occurred during the restore operation 
     */
    public Iterator<Match> restoreMatchSportsVenue( SportsVenue sportsVenue ) throws RDException;

    /** 
     * Return SportsVenue where a given Match was played.
     * @param match the Match
     * @return SportsVenue where the Match was played
     * @throws RDException in case an error occurred during the restore operation 
     */
    public SportsVenue restoreMatchSportsVenue( Match match ) throws RDException;

    /** 
     * Delete a link between a Match and a SportsVenue used in the match.
     * @param match the Match
     * @param sportsVenue the SportsVenue
     * @throws RDException in case an error occurred during the delete operation 
     */
    public void deleteMatchSportsVenue( Match match, SportsVenue sportsVenue ) throws RDException;
}

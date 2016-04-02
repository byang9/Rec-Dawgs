package edu.uga.cs.recdawgs.object;

import java.util.Date;
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
 * This is the interface to the Object Layer subsystem of the RecDawgs system. The interface provides operations
 * realizing the life-cycle of both <i>entity objects</i> and <i>links</i> interconnecting them.
 * <p>
 * For each entity <code><i>Class</i></code>, there are four operations: <code>create<i>Class</i></code>, 
 * <code>find<i>Class</i></code>, <code>store<i>Class</i></code>, and <code>delete<i>Class</i></code>.
 * <p>
 * The <code>create<i>Class</i></code> operations are factory method-type operations to create 
 * new entity instances. There are two versions of this operation: one with arguments, which give 
 * the initial attribute values, and one without (argument-less). 
 * The one with arguments is used to create a new object instance which has not been persisted yet.  
 * Such an instance must be stored in the persistent data store before the use case terminates.
 * The version with no arguments creates an uninitialized object and should be used as the
 * first step when restoring the persistent object from the persistent data store (suitable
 * setter methods should be used to set the attribute values, as retrieved from the persistent
 * data store. 
 * <p>
 * Furthermore, the <code>create<i>Class</i></code> operations for classes participating in associations with 
 * other classes, and where the multiplicity on the other end of the association is one, have parameters
 * to provide the linked objects on the other end of the association.  Note, that a class participating in
 * an association with a multiplicity of one with another class cannot exist without a link to one other
 * object on the other end.  Therefore, such a link must be established at the time an object is created.
 * <p>
 * The <code>find<i>Class</i></code> operations locate objects satisfying given search criteria and return an Iterator
 * with the located object instances.  Each of these operations accepts a single <code>model<i>Class</i></code> argument.
 * If the argument provided on a call to one of these methods is null, it indicates that all objects
 * of a given class should be returned (the search criteria are empty, and so all objects satisfy the
 * search criteria).  On the other hand, a given <code>model<i>Class</i></code> argument may have all, or just some 
 * of the class attributes provided.  In this case, the <code>find<i>Class</i></code> operation will return only the
 * matching objects, i.e., the objects with the exact same values for all of the provided
 * attribute values in the model object.  More specifically: 
 * <ul>
 *   <li>if the <code>model<i>Class</i></code> argument is null, all objects of the given class are returned;</li>
 *   <li>if the persistent identifier attribute is provided, only one object is returned;</li>
 *   <li>if some (or even all) of the attributes are provided, only objects with the same attribute values are returned.</li>
 * </ul>
 * <p>
 * The <code>store<i>Class</i></code> operations store the argument object instance in the persistent data store.  If an object
 * being stored is already persistent (<code>isPersistent()</code> returns <code>true</code>), the object is updated.  
 * Otherwise, the object is stored in the persistent data store for the first time and its persistent object identifier is set.
 * <p>
 * The <code>delete<i>Class</i></code> operations remove the argument object instance.  While the argument (proxy) object may
 * still exist in the JVM that created the proxy, but the persistent object will be deleted and the subsequent
 * findClas operation call with the same persistent identifier should return an empty Iterator.
 * <p>
 * For each binary association connecting two entity classes <code><i>ClassA</i></code> and <code><i>ClassB</i></code>, 
 * there are four operations: 
 * <ul>
 * <li><code>store<i>ClassAClassB</i></code>, which is used to create a link between two instances of <code><i>ClassA</i></code> 
 *     and <code><i>ClassB</i></code>,</li>
 * <li><code>restore<i>ClassAClassB</i></code>, two overloaded versions are used to traverse the link from 
 *     <code><i>ClassA</i></code> to <code><i>ClassB</i></code> and from
 *     <code><i>ClassB</i></code> to <code><i>ClassA</i></code>, and</li>
 * <li><code>delete<i>ClassAClassB</i></code>, which is used to remove the link connecting two object instances.</li>
 * </ul>
 * <p>
 * In case there are two associations connecting the same two entity classes, the names of the association-related operations include
 * the name of the association between the classes.  Furthermore, depending on the multiplicity of the association endpoint,
 * the return value is either <code><i>ClassA</i></code> (<code><i>ClassB</i></code>), if the multiplicity is one or optional,
 * or an <code>Iterator&lt;<i>ClassA</i>&gt;</code> (<code>Iterator&lt;<i>ClassB</i>&gt;</code>), if the multiplicity is many.
 * <p>Note, that for any association endpoint with a multiplicity of one or optional, the entity class on the opposite end of
 * the association has get/set methods get or set the (single) linked object instance. 
 */
public interface ObjectLayer
{
    /**
     * Create a new Administrator object, given the set of initial attribute values.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user name (login name)
     * @param password the password
     * @param emailAddress the email address
     * @return a new Administrator object instance with the given attribute values
     * @throws RDException in case firstName, lastName, or userName is null
     */
    public Administrator createAdministrator( String firstName, String lastName, String userName, 
                                              String password, String emailAddress ) throws RDException;

    /**
     * Create a new Administrator object with undefined attribute values.
     * @return a new Administrator object instance
     */
    public Administrator createAdministrator(); 
    
    /**
     * Return an iterator of Administrator objects satisfying the search criteria given in the modelAdministrator object.
     * @param modelAdministrator a model Administrator object specifying the search criteria
     * @return an Iterator of the located Administrator objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Administrator> findAdministrator( Administrator modelAdministrator ) throws RDException;
    
    /**
     * Store a given Administrator object in persistent data store.
     * @param administrator the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeAdministrator( Administrator administrator ) throws RDException;
    
    /**
     * Delete this Administrator object.
     * @param administrator the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteAdministrator( Administrator administrator ) throws RDException; 
    
    /**
     * Create a new Student object, given the set of initial attribute values.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user (login) name
     * @param password the password
     * @param emailAddress the email address
     * @param studentId the student identifier
     * @param major the student's major
     * @param address the student's address
     * @return a new Student object instance with the given attribute values
     * @throws RDException in case firstName, lastName, userName, or studentId is null
     */
    public Student createStudent( String firstName, String lastName, String userName, String password, 
            String emailAddress, String studentId, String major, String address ) throws RDException;

    /**
     * Create a new Student object with undefined attribute values.
     * @return a new Student object instance
     */
    public Student createStudent(); 
    
    /**
     * Return an iterator of Student objects satisfying the search criteria given in the modelStudent object.
     * @param modelStudent a model Student object specifying the search criteria
     * @return an Iterator of the located Student objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Student> findStudent( Student modelStudent ) throws RDException;
    
    /**
     * Store a given Student object in persistent data store.
     * @param student the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeStudent( Student student ) throws RDException;
    
    /**
     * Delete this Student object.
     * @param student the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteStudent( Student student ) throws RDException;      

    /**
     * Create a new League object, given the set of initial attribute values.
     * @param name the name of the league
     * @param leagueRules the rules of participating in the league
     * @param matchRules the match rules for matches played in the league
     * @param isIndoor is the league an indoor league?
     * @param minTeams the minimum number of teams in the league
     * @param maxTeams the maximum number of teams in the league
     * @param minPlayers the minimum number of players in teams in the league
     * @param maxPlayers the maximum number of players in teams in the league
     * @return a new League instance with the given attribute values
     * @throws RDException in case name is null or any of the team/player numbers is not positive or the given maximum is less than the corresponding minimum
     */
    public League createLeague( String name, String leagueRules, String matchRules,
            boolean isIndoor, int minTeams, int maxTeams, int minPlayers, int maxPlayers ) throws RDException;

    /**
     * Create a new League object with undefined attribute values.
     * @return a new League object instance
     */
    public League createLeague();

    /**
     * Return an iterator of League objects satisfying the search criteria given in the modelLeague object.
     * @param modelLeague a model League object specifying the search criteria
     * @return an Iterator of the located League objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<League> findLeague( League modelLeague ) throws RDException;
    
    /**
     * Store a given League object in persistent data store.
     * @param league the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeLeague( League league ) throws RDException;
    
    /**
     * Delete this League object.
     * @param league the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteLeague( League league ) throws RDException;    

    /**
     * Create a new Team object, given the set of initial attribute values.
     * @param name the name of the team
     * @return a new Team object instance with the given attribute values
     * @throws RDException in case name is null
     */
    public Team createTeam( String name ) throws RDException;

    /**
     * Create a new Team object with undefined attribute values.
     * @return a new Team object instance with the given attribute value
     */
    public Team createTeam();

    /**
     * Return an iterator of Team objects satisfying the search criteria given in the modelTeam object.
     * @param modelTeam a model Team object specifying the search criteria
     * @return an Iterator of the located Team objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Team> findTeam( Team modelTeam ) throws RDException;
    
    /**
     * Store a given Team object in persistent data store.
     * @param team the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeTeam( Team team ) throws RDException;
    
    /**
     * Delete this Team object.
     * @param team the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteTeam( Team team ) throws RDException;    
 
    /**
     * Create a new SportsVenue object, given the set of initial attribute values.
     * @param name the name of the sports venue
     * @param address the address of the sports venue
     * @param isIndoor is the sports venue an indoor venue?
     * @return a new SportsVenue object instance with the given attribute values
     * @throws RDException in case name is null
     */
    public SportsVenue createSportsVenue( String name, String address, boolean isIndoor ) throws RDException;

    /**
     * Create a new SportsVenue object with undefined attribute values.
     * @return a new SportsVenue object instance
     */
    public SportsVenue createSportsVenue();

    /**
     * Return an iterator of SportsVenue objects satisfying the search criteria given in the modelSportsVenue object.
     * @param modelSportsVenue a model SportsVenue object specifying the search criteria
     * @return an Iterator of the located SportsVenue objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<SportsVenue> findSportsVenue( SportsVenue modelSportsVenue ) throws RDException;
    
    /**
     * Store a given SportsVenue object in persistent data store.
     * @param sportsVenue the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeSportsVenue( SportsVenue sportsVenue ) throws RDException;
    
    /**
     * Delete this SportsVenue object.
     * @param sportsVenue the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteSportsVenue( SportsVenue sportsVenue ) throws RDException;    

    /**
     * Create a new Match object, given the set of initial attribute values.
     * @param homePoints the points won by the home team (must be non-negative)
     * @param awayPoints the points won by the away team (must be non-negative)
     * @param date the date of the match
     * @param isCompleted has the match been completed?
     * @param homeTeam the team which is the home team in this match
     * @param awayTeam the team which is the away team in this match
     * @return a new Match object instance with the given attribute values
     * @throws RDException in case any of the point arguments is negative or either of the teams is null or if the given teams are not in the same league
     */
    public Match createMatch( int homePoints, int awayPoints, Date date, boolean isCompleted,
            Team homeTeam, Team awayTeam ) throws RDException;

    /**
     * Create a new Match object with undefined attribute values.
     * @return a new Match object instance
     */
    public Match createMatch();

    /**
     * Return an iterator of Match objects satisfying the search criteria given in the modelMatch object.
     * @param modelMatch a model Match object specifying the search criteria
     * @return an Iterator of the located Match objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Match> findMatch( Match modelMatch ) throws RDException;
    
    /**
     * Store a given Match object in persistent data store.
     * @param match the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeMatch( Match match ) throws RDException;
    
    /**
     * Delete this Match object.
     * @param match the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteMatch( Match match ) throws RDException;    

    /**
     * Create a new Round object.
     * @param number the number of this round of matches
     * @return a new Round object instance
     * @throws RDException in case the number is not positive
     */
    public Round createRound( int number ) throws RDException;

    /**
     * Create a new Round object with undefined attribute values.
     * @return a new Round object instance
     */
    public Round createRound();
    
    /**
     * Return an iterator of Round objects satisfying the search criteria given in the modelRound object.
     * @param modelRound a model Round object specifying the search criteria
     * @return an Iterator of the located Round objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<Round> findRound( Round modelRound ) throws RDException;
    
    /**
     * Store a given Round object in persistent data store.
     * @param round the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeRound( Round round ) throws RDException;
    
    /**
     * Delete this Round object.
     * @param round the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteRound( Round round ) throws RDException;    

    /**
     * Create a new ScoreReport object, given the set of initial attribute values.
     * @param homePoints the points won by the home team (must be non-negative)
     * @param awayPoints the points won by the away team (must be non-negative)
     * @param date the date of the match
     * @param student the Student submitting the match score report
     * @param match the Match for which the score is reported
     * @return a new ScoreReport object instance with the given attribute values 
     * @throws RDException in case any of the point arguments is negative, either student or match is null, or if the student is not the captain of one of the teams in the match
     */
    public ScoreReport createScoreReport( int homePoints, int awayPoints, Date date,
            Student student, Match match) throws RDException;

    /**
     * Create a new ScoreReport object with undefined attribute values.
     * @return a new ScoreReport object instance
     */
    public ScoreReport createScoreReport();

    /**
     * Return an iterator of ScoreReport objects satisfying the search criteria given in the modelScoreReport object.
     * @param modelScoreReport a model ScoreReport object specifying the search criteria
     * @return an Iterator of the located ScoreReport objects
     * @throws RDException in case there is a problem with the retrieval of the requested objects
     */
    public Iterator<ScoreReport> findScoreReport( ScoreReport modelScoreReport ) throws RDException;
    
    /**
     * Store a given ScoreReport object in persistent data store.
     * @param scoreReport the object to be persisted
     * @throws RDException in case there was an error while persisting the object
     */
    public void storeScoreReport( ScoreReport scoreReport ) throws RDException;
    
    /**
     * Delete this ScoreReport object.
     * @param scoreReport the object to be deleted.
     * @throws RDException in case there is a problem with the deletion of the object
     */
    public void deleteScoreReport( ScoreReport scoreReport ) throws RDException;    

    // Operations for handling associations
    //
    
    // Student--isCaptainOf-->Team;   multiplicity: 1 - *
    //
    /**
     * Create a new link between a Student captain and a Team.
     * @param student the student who is the captain of the team
     * @param team the team
     * @throws RDException in case either the student and/or the team is null
     */
    public void createStudentCaptainOfTeam( Student student, Team team ) throws RDException;
    
    /**
     * Return the student who is the captain of the team (traverse the link isCaptainOf from Team to Student).
     * @param team the team
     * @return the student who is the team's captain
     * @throws RDException in case either the team is null or another error occurs
     */
    public Student restoreStudentCaptainOfTeam( Team team ) throws RDException;
    
    /**
     * Return the Teams captained by a given student (traverse the link isCaptainOf from Student to Team).
     * @param student the student
     * @return the iterator of Teams captained by the student
     * @throws RDException in case either the student is null or another error occurs
     */
    public Iterator<Team> restoreStudentCaptainOfTeam( Student student ) throws RDException;
    
    /**
     * Delete a link between a student and a team (sever the link isCaptainOf from Team to Student).
     * @param student the student
     * @param team the team
     * @throws RDException in case either the student or team is null or another error occurs
     */
    public void deleteStudentCaptainOfTeam( Student student, Team team ) throws RDException;

    // Student--isMemberOf-->Team;   multiplicity: 1..* - *
    //
    /**
     * Create a new link between a Student who is becoming a member and a Team.
     * @param student the student who is a member of the team
     * @param team the team
     * @throws RDException in case either the student and/or the team is null
     */
    public void createStudentMemberOfTeam( Student student, Team team ) throws RDException;
    
    /**
     * Return the students who are members of the team (traverse the link isMemberOf from Team to Student).
     * @param team the team
     * @return the iterator of Students who are members of the team
     * @throws RDException in case either the team is null or another error occurs
     */
    public Iterator<Student> restoreStudentMemberOfTeam( Team team ) throws RDException;
    
    /**
     * Return the Teams of which a given student is a member (traverse the link isMemberOf from Student to Team).
     * @param student the student
     * @return the iterator of Teams of which the student is a member
     * @throws RDException in case either the student is null or another error occurs
     */
    public Iterator<Team> restoreStudentMemberOfTeam( Student student ) throws RDException;
    
    /**
     * Delete a link between a student and a team (sever the link isMemberOf from Team to Student).
     * @param student the student
     * @param team the team
     * @throws RDException in case either the student or team is null or another error occurs
     */
    public void deleteStudentMemberOfTeam( Student student, Team team ) throws RDException;

    // Team--isHomeTeam-->Match;   multiplicity: 1 - *
    //
    /**
     * Create a new link between a Team which is a home team in a Match.
     * @param team the home team 
     * @param match the match
     * @throws RDException in case either the team and/or the match is null
     */
    public void createTeamHomeTeamMatch( Team team, Match match ) throws RDException;
    
    /**
     * Return the home team of the match (traverse the link isHomeTeam from Match to Team).
     * @param match the match
     * @return the Team which is the home team in the match
     * @throws RDException in case either the match is null or another error occurs
     */
    public Team restoreTeamHomeTeamMatch( Match match ) throws RDException;
    
    /**
     * Return the Matches in which a given team is a home team (traverse the link isHomeTeam from Team to Match).
     * @param team the team
     * @return the iterator of Matches in which the team is the home team
     * @throws RDException in case either the team is null or another error occurs
     */
    public Iterator<Match> restoreTeamHomeTeamMatch( Team team ) throws RDException;
    
    /**
     * Delete a link between a home team and a match (sever the link isHomeTeam from Match to Team).
     * @param team the team
     * @param match the match
     * @throws RDException in case either the team or match is null or another error occurs
     */
    public void deleteTeamHomeTeamMatch( Team team, Match match ) throws RDException;

    // Team--isAwayTeam-->Match;   multiplicity: 1 - *
    //
    /**
     * Create a new link between a Team which is a away team in a Match.
     * @param team the away team 
     * @param match the match
     * @throws RDException in case either the team and/or the match is null
     */
    public void createTeamAwayTeamMatch( Team team, Match match ) throws RDException;
    
    /**
     * Return the away team of the match (traverse the link isAwayTeam from Match to Team).
     * @param match the match
     * @return the Team which is the away team in the match
     * @throws RDException in case either the match is null or another error occurs
     */
    public Team restoreTeamAwayTeamMatch( Match match ) throws RDException;
    
    /**
     * Return the Matches in which a given team is a away team (traverse the link isAwayTeam from Team to Match).
     * @param team the team
     * @return the iterator of Matches in which the team is the away team
     * @throws RDException in case either the team is null or another error occurs
     */
    public Iterator<Match> restoreTeamAwayTeamMatch( Team team ) throws RDException;
    
    /**
     * Delete a link between a away team and a match (sever the link isAwayTeam from Match to Team).
     * @param team the team
     * @param match the match
     * @throws RDException in case either the team or match is null or another error occurs
     */
    public void deleteTeamAwayTeamMatch( Team team, Match match ) throws RDException;

    // Team--participatesIn-->League;   multiplicity: * - 1
    //
    /**
     * Create a new link between a Team and a League in which it competes.
     * @param team the team 
     * @param league the league
     * @throws RDException in case either the team and/or the league is null
     */
    public void createTeamParticipatesInLeague( Team team, League league ) throws RDException;
    
    /**
     * Return the League in which a given team competes (traverse the link participatesIn from Team to League).
     * @param team the team
     * @return the League in which the team competes
     * @throws RDException in case either the team is null or another error occurs
     */
    public League restoreTeamParticipatesInLeague( Team team ) throws RDException;
    
    /**
     * Return the Teams competing in the league (traverse the link participatesIn from League to Team).
     * @param league the league
     * @return an Iterator of Teams competing in the league
     * @throws RDException in case either the league is null or another error occurs
     */
    public Iterator<Team> restoreTeamParticipatesInLeague( League league ) throws RDException;
    
    /**
     * Delete a link between a Team and a League (sever the link isParticipatesIn from League to Team).
     * @param team the team
     * @param league the league
     * @throws RDException in case either the team or league is null or another error occurs
     */
    public void deleteTeamParticipatesInLeague( Team team, League league ) throws RDException;

    // Team--isWinnerOf-->League;   multiplicity: 0..1 - 0..1
    //
    /**
     * Create a new link between a Team and a League in which the Team is the winner.
     * @param team the team 
     * @param league the league
     * @throws RDException in case either the team and/or the league is null
     */
    public void createTeamWinnerOfLeague( Team team, League league ) throws RDException;
    
    /**
     * Return the League in which a given team is the winner (traverse the link isWinnerOf from Team to League).
     * @param team the team
     * @return the League in which the team is the winner
     * @throws RDException in case either the team is null or another error occurs
     */
    public League restoreTeamWinnerOfLeague( Team team ) throws RDException;
    
    /**
     * Return the Team which won the league (traverse the link isWinnerOf from League to Team).
     * @param league the league
     * @return the winning Team of the league
     * @throws RDException in case either the league is null or another error occurs
     */
    public Team restoreTeamWinnerOfLeague( League league ) throws RDException;
    
    /**
     * Delete a link between a Team and a League (sever the link isWinnerOf from League to Team).
     * @param team the team
     * @param league the league
     * @throws RDException in case either the team or league is null or another error occurs
     */
    public void deleteTeamWinnerOfLeague( Team team, League league ) throws RDException;


    // League--has-->SportsVenue;   multiplicity: * - *
    //
    /**
     * Create a new link between a League and a SportsVenue in which the League is the winner.
     * @param league the League 
     * @param sportsVenue the SportsVenue
     * @throws RDException in case either the league and/or the sportsVenue is null
     */
    public void createLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException;
    
    /**
     * Return the SportsVenues used by a given League is the winner (traverse the link has from League to SportsVenue).
     * @param league the League
     * @return an Iterator of SportsVenues used by the league
     * @throws RDException in case either the league is null or another error occurs
     */
    public Iterator<SportsVenue> restoreLeagueSportsVenue( League league ) throws RDException;
    
    /**
     * Return the Leagues using the sportsVenue (traverse the link has from SportsVenue to League).
     * @param sportsVenue the SportsVenue
     * @return an Iterator of Leagues using the sportsVenue
     * @throws RDException in case either the sportsVenue is null or another error occurs
     */
    public League restoreLeagueSportsVenue( SportsVenue sportsVenue ) throws RDException;
    
    /**
     * Delete a link between a League and a SportsVenue (sever the link has from SportsVenue to League).
     * @param league the League
     * @param sportsVenue the SportsVenue
     * @throws RDException in case either the league or sportsVenue is null or another error occurs
     */
    public void deleteLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException;

    // League--includes-->Round;   multiplicity: 1 - *
    //
    /**
     * Create a new link between a League and a Round of matches in the League.
     * @param league the League 
     * @param round the Round
     * @throws RDException in case either the league and/or the round is null
     */
    public void createLeagueRound( League league, Round round ) throws RDException;
    
    /**
     * Return the Rounds of matches in a given League (traverse the link includes from League to Round).
     * @param league the League
     * @return an Iterator of Rounds of matches in the league
     * @throws RDException in case either the league is null or another error occurs
     */
    public Iterator<Round> restoreLeagueRound( League league ) throws RDException;
    
    /**
     * Return the Leagues with the round of matches (traverse the includes from Round to League).
     * @param round the Round
     * @return the League with the given round of matches
     * @throws RDException in case either the round is null or another error occurs
     */
    // Not needed since the assocation is not traversed in this direction
    // public League restoreLeagueRound( Round round ) throws RDException;
    
    /**
     * Delete a link between a League and a Round of matches (sever the link includes from Round to League).
     * @param league the League
     * @param round the Round
     * @throws RDException in case either the league or round is null or another error occurs
     */
    public void deleteLeagueRound( League league, Round round ) throws RDException;



    // Round--includes-->Match;   multiplicity: 1 - 1..*
    //
    /**
     * Create a new link between a Round and a Match played in it.
     * @param round the Round
     * @param match the Match
     * @throws RDException in case either the round and/or the match is null
     */
    public void createRoundMatch( Round round, Match match ) throws RDException;
    
    /**
     * Return the Matches played in a given Round (traverse the link includes from Round to Match).
     * @param round the Round
     * @return an Iterator of Matches played in the round
     * @throws RDException in case either the round is null or another error occurs
     */
    public Iterator<Match> restoreRoundMatch( Round round ) throws RDException;
    
    /**
     * Return the Round in which the match is played (traverse the includes from Match to Round).
     * @param match the Match
     * @return the Round in which the given match is played
     * @throws RDException in case either the match is null or another error occurs
     */
    // Not needed since the assocation is not traversed in this direction
    // public Round restoreRoundMatch( Match match ) throws RDException;
    
    /**
     * Delete a link between a Round and a Match (sever the link includes from Match to Round).
     * @param round the Round
     * @param match the Match
     * @throws RDException in case either the round or match is null or another error occurs
     */
    public void deleteRoundMatch( Round round, Match match ) throws RDException;


    // Match--isPlayedAt-->SportsVenue;   multiplicity: * - 0..1
    //
    /**
     * Create a new link between a Match and a SportsVenue where the Match is played.
     * @param match the Match 
     * @param sportsVenue the SportsVenue
     * @throws RDException in case either the match and/or the sportsVenue is null
     */
    public void createMatchSportsVenue( Match match, SportsVenue sportsVenue ) throws RDException;
    
    /**
     * Return the SportsVenue where a given Match is played (traverse the link isPlayedAt from Match to SportsVenue).
     * @param match the Match
     * @return the SportsVenue where the given match is played
     * @throws RDException in case either the match is null or another error occurs
     */
    public SportsVenue restoreMatchSportsVenue( Match match ) throws RDException;
    
    /**
     * Return the Matches played at a SportsVenue (traverse the link isPlayedAt from SportsVenue to Match).
     * @param sportsVenue the SportsVenue
     * @return an Iterator of Matches played at the sportsVenue
     * @throws RDException in case either the sportsVenue is null or another error occurs
     */
    public Iterator<Match> restoreMatchSportsVenue( SportsVenue sportsVenue ) throws RDException;
    
    /**
     * Delete a link between a Match and a SportsVenue (sever the link isPlayedAt from SportsVenue to Match).
     * @param match the Match
     * @param sportsVenue the SportsVenue
     * @throws RDException in case either the match or sportsVenue is null or another error occurs
     */
    public void deleteMatchSportsVenue( Match match, SportsVenue sportsVenue ) throws RDException;

}

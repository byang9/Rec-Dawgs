import java.sql.Connection;
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
//import entities
//import object layet
//import persistence layer


public class PersistenceLayerImpl implements PersistenceLayer{
    private VenueManager venueManager = null;
    private hasVenueManager hasVenueManager = null;
    private TeamManager teamManager = null;
    private PersonManager personManager = null;
    private MembershipManager membershipManager = null;
    private LeagueManager leagueManager = null;
   
    public PersistenceLayerImpl(Connection conn, ObjectLayer objectLayer){
    	this.venueManager = new VenueManager(conn, objectLayer);
    	this.hasVenueManager = new hasVenueManager(conn, objectLayer);
    	this.personManager = new PersonManager(conn, objectLayer);
    	this.membershipManager = new MembershipManager(conn, objectLayer);
    	this.leagueManager = new LeagueManager(conn, objectLayer);
    	System.out.println("PersistenceLayerImpl initialized.");
    }

    public Iterator<Administrator> restoreAdministrator( Administrator modelAdministrator ) throws RDException{}
    public void storeAdministrator( Administrator administrator ) throws RDException{}
    public void deleteAdministrator( Administrator administrator ) throws RDException{}
    public Iterator<Student> restoreStudent( Student modelStudent ) throws RDException{}
    public void storeStudent( Student student ) throws RDException{}
    public void deleteStudent( Student student ) throws RDException{}
    public Iterator<Match> restoreMatch( Match modelMatch ) throws RDException{}
    public void storeMatch( Match match ) throws RDException{}
    public void deleteMatch( Match match ) throws RDException{}
    public Iterator<SportsVenue> restoreSportsVenue( SportsVenue modelSportsVenue ) throws RDException{}
    public void storeSportsVenue( SportsVenue sportsVenue ) throws RDException{}
    public void deleteSportsVenue( SportsVenue sportsVenue ) throws RDException{}
    public Iterator<Team> restoreTeam( Team modelTeam ) throws RDException{}
    public void storeTeam( Team team ) throws RDException{}
    public void deleteTeam( Team team ) throws RDException{}
    public Iterator<League> restoreLeague( League modelLeague ) throws RDException{}
    public void storeLeague( League league ) throws RDException{}
    public void deleteLeague( League league ) throws RDException{}
    public Iterator<ScoreReport> restoreScoreReport( ScoreReport modelScoreReport ) throws RDException;
    public void storeScoreReport( ScoreReport scoreReport ) throws RDException{}
    public void deleteScoreReport( ScoreReport scoreReport ) throws RDException{}      
    public Iterator<Round> restoreRound( Round modelRound ) throws RDException{}
    public void storeRound( Round round ) throws RDException{}
    public void deleteRound( Round round ) throws RDException{}    







    //Associations
    public void storeStudentCaptainOfTeam( Student student, Team team ) throws RDException{}
    public Student restoreStudentCaptainOfTeam( Team team ) throws RDException{}
    public Iterator<Team> restoreStudentCaptainOfTeam( Student student ) throws RDException{}
    public void deleteStudentCaptainOfTeam( Student student, Team team ) throws RDException{}
    public void storeStudentMemberOfTeam( Student student, Team team ) throws RDException{}
    public Iterator<Student> restoreStudentMemberOfTeam( Team team ) throws RDException{}
    public Iterator<Team> restoreStudentMemberOfTeam( Student student ) throws RDException{}
    public void deleteStudentMemberOfTeam( Student student, Team team ) throws RDException{}
    public void storeTeamHomeTeamMatch( Team team, Match match ) throws RDException{}
    public Team restoreTeamHomeTeamMatch( Match match ) throws RDException{}
    public Iterator<Match> restoreTeamHomeTeamMatch( Team team ) throws RDException{}
    public void deleteTeamHomeTeamMatch( Team team, Match match ) throws RDException{}
    public void storeTeamAwayTeamMatch( Team team, Match match ) throws RDException{}
    public Team restoreTeamAwayTeamMatch( Match match ) throws RDException{}
    public Iterator<Match> restoreTeamAwayTeamMatch( Team team ) throws RDException{}
    public void deleteTeamAwayTeamMatch( Team team, Match match ) throws RDException{}
    public void storeTeamParticipatesInLeague( Team team, League league ) throws RDException{}
    public Iterator<Team> restoreTeamParticipatesInLeague( League league ) throws RDException{}
    public League restoreTeamParticipatesInLeague( Team team ) throws RDException{}
    public void deleteTeamParticipatesInLeague( Team team, League league ) throws RDException{}
    public void storeTeamWinnerOfLeague( Team team, League league ) throws RDException{}
    public Team restoreTeamWinnerOfLeague( League league ) throws RDException{}
    public League restoreTeamWinnerOfLeague( Team team ) throws RDException{}
    public void deleteTeamWinnerOfLeague( Team team, League league ) throws RDException{}
    public void storeLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException{}
    public Iterator<League> restoreLeagueSportsVenue( SportsVenue sportsVenue ) throws RDException{}
    public Iterator<SportsVenue> restoreLeagueSportsVenue( League league ) throws RDException{}
    public void deleteLeagueSportsVenue( League league, SportsVenue sportsVenue ) throws RDException{}
    public void storeLeagueRound( League league, Round round ) throws RDException{}
    public Iterator<Round> restoreLeagueRound( League league ) throws RDException{}
    public void deleteLeagueRound( League league, Round round ) throws RDException{}
    public void storeRoundMatch( Round round, Match match ) throws RDException{}
    public Iterator<Match> restoreRoundMatch( Round round ) throws RDException{}
    public void deleteRoundMatch( Round round, Match match ) throws RDException{}
    public void storeMatchSportsVenue( Match match, SportsVenue sportsVenue ) throws RDException{}
    public Iterator<Match> restoreMatchSportsVenue( SportsVenue sportsVenue ) throws RDException{}
    public SportsVenue restoreMatchSportsVenue( Match match ) throws RDException{}
    public void deleteMatchSportsVenue( Match match, SportsVenue sportsVenue ) throws RDException{}
 }
package edu.uga.cs.recdawgs.persistence.impl;

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
import edu.uga.cs.recdawgs.object.ObjectLayer;
//import object layet
//import persistence layer
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;


public class PersistenceLayerImpl implements PersistenceLayer {
    
    // Object Managers
    private VenueManager venueManager = null;
    private TeamManager teamManager = null;
    private PersonManager personManager = null;
    private LeagueManager leagueManager = null;
    private MatchupManager matchManager = null;
    private ScoreReportManager scoreReportManager = null;
    private RoundManager roundManager = null;
    
    // Association Managers
    private LeagueSportsVenueManager lsvManager = null;
    private MembershipManager membershipManager = null;
   
    public PersistenceLayerImpl(Connection conn, ObjectLayer objectLayer){
    	this.venueManager = new VenueManager(conn, objectLayer);
    	this.lsvManager = new LeagueSportsVenueManager(conn, objectLayer);
        this.teamManager = new TeamManager(conn, objectLayer);
    	this.personManager = new PersonManager(conn, objectLayer);
    	this.membershipManager = new MembershipManager(conn, objectLayer);
    	this.leagueManager = new LeagueManager(conn, objectLayer);
        this.matchManager = new MatchupManager(conn, objectLayer);
        this.scoreReportManager = new ScoreReportManager(conn, objectLayer);
        this.roundManager = new RoundManager(conn, objectLayer);
    	System.out.println("PersistenceLayerImpl initialized.");
    }
    
    
    
    
    
    
    
    /* Objects in Persistence Layer */
    
    // Returns Administrator Iterator from given modelAdmin -- inside PersonManager
    public Iterator<Administrator> restoreAdministrator(Administrator modelAdministrator) throws RDException {
        return personManager.restore(modelAdministrator);
    }
    
    // Stores Admin -- inside PersonManager
    public void storeAdministrator(Administrator administrator) throws RDException {
        personManager.save(administrator);
    }
    
    // Deletes Admin -- inside PersonManager
    public void deleteAdministrator(Administrator administrator) throws RDException {
        personManager.delete(administrator);
    }
    
    // Returns Student Iterator from given modelStudent -- inside PersonManager
    public Iterator<Student> restoreStudent(Student modelStudent) throws RDException {
        return personManager.restore(modelStudent);
    }
    
    // Stores Student -- inside PersonManager
    public void storeStudent(Student student) throws RDException {
        personManager.save(student);
    }
    
    // Deletes Student -- inside PersonManager
    public void deleteStudent(Student student) throws RDException {
        personManager.delete(student);
    }
    
    // Returns Match Iterator from given modelMatch -- inside MatchupManager
    public Iterator<Match> restoreMatch(Match modelMatch) throws RDException {
        return matchManager.restore(modelMatch);
    }
    
    // Stores Match -- inside MatchupManager
    public void storeMatch(Match match) throws RDException {
        matchManager.save(match);
    }
    
    // Deletes Match -- inside MatchupManager
    public void deleteMatch(Match match) throws RDException {
        matchManager.delete(match);
    }
    
    // Returns SportsVenue Iterator from given modelSportsVenue -- inside VenueManager
    public Iterator<SportsVenue> restoreSportsVenue(SportsVenue modelSportsVenue) throws RDException {
        return venueManager.restore(modelSportsVenue);
    }
    
    // Stores SportsVenue -- inside VenueManager
    public void storeSportsVenue(SportsVenue sportsVenue) throws RDException {
        venueManager.save(sportsVenue);
    }
    
    // Deletes SportsVenue -- inside VenueManager
    public void deleteSportsVenue(SportsVenue sportsVenue) throws RDException {
        venueManager.delete(sportsVenue);
    }
    
    // Returns Team Iterator from given modelTeam -- inside TeamManager
    public Iterator<Team> restoreTeam(Team modelTeam) throws RDException {
        return teamManager.restore(modelTeam);
    }
    
    // Stores Team -- inside TeamManager
    public void storeTeam(Team team) throws RDException {
        teamManager.save(team);
    }
    
    // Deletes Team -- inside TeamManager
    public void deleteTeam(Team team) throws RDException {
        teamManager.delete(team);
    }
    
    // Returns League Iterator from given modelLeague -- inside LeagueManager
    public Iterator<League> restoreLeague(League modelLeague) throws RDException {
        return leagueManager.restore(modelLeague);
    }
    
    // Stores League -- inside LeagueManager
    public void storeLeague(League league) throws RDException {
        leagueManager.save(league);
    }
    
    // Deletes League -- inside LeagueManager
    public void deleteLeague(League league) throws RDException {
        leagueManager.delete(league);
    }
    
    // Returns ScoreReport Iterator from given modelScoreReport -- inside ScoreReportManager
    public Iterator<ScoreReport> restoreScoreReport(ScoreReport modelScoreReport) throws RDException {
        return scoreReportManager.restore(modelScoreReport);
    }
    
    // Stores ScoreReport -- inside ScoreReportManager
    public void storeScoreReport(ScoreReport scoreReport) throws RDException {
        scoreReportManager.save(scoreReport);
    }
    
    // Deletes ScoreReport -- inside ScoreReportManager
    public void deleteScoreReport(ScoreReport scoreReport) throws RDException {
        scoreReportManager.delete(scoreReport);
    }   
    
    // Returns Round Iterator from given modelRound -- inside RoundManager
    public Iterator<Round> restoreRound(Round modelRound) throws RDException {
        return roundManager.restore(modelRound);
    }
    
    // Store Round -- inside RoundManager
    public void storeRound(Round round) throws RDException {
        roundManager.save(round);
    }
    
    // Deletes Round -- inside RoundManager
    public void deleteRound(Round round) throws RDException {
        roundManager.delete(round);
    }   
    

    
    
    
    
    
    



    /* Associations in Persistence Layer */
    
    public void storeStudentCaptainOfTeam(Student student, Team team) throws RDException {    	
    	
    }
    
    public Student restoreStudentCaptainOfTeam(Team team) throws RDException {
        
    }
    
    public Iterator<Team> restoreStudentCaptainOfTeam(Student student) throws RDException {
        
    }
    
    public void deleteStudentCaptainOfTeam(Student student, Team team) throws RDException {
        
    }
    
    // Saves a student and team relationship
    public void storeStudentMemberOfTeam(Student student, Team team) throws RDException {
        membershipManager.save(student, team);
    }
    
    // Returns students that are part of the given team
    public Iterator<Student> restoreStudentMemberOfTeam(Team team) throws RDException {
        return membershipManager.restore(team);
    }
    
    // Returns teams that the given student is on
    public Iterator<Team> restoreStudentMemberOfTeam(Student student) throws RDException {
        return membershipManager.restore(student);
    }
    
    // Deletes a team to student relationship
    public void deleteStudentMemberOfTeam(Student student, Team team) throws RDException {
        membershipManager.delete(student, team);
    }
    
    public void storeTeamHomeTeamMatch(Team team, Match match) throws RDException {
        
    }
    
    // Returns teams that are from a given match
    public Team restoreTeamHomeTeamMatch(Match match) throws RDException {
        return matchManager.restoreHomeTeam(match);
    }
    
    // Returns matches from a given a home team
    public Iterator<Match> restoreTeamHomeTeamMatch(Team team) throws RDException {
        return matchManager.restoreHomeTeamMatch(team);
    }
    
    public void deleteTeamHomeTeamMatch(Team team, Match match) throws RDException {
        
    }
    
    public void storeTeamAwayTeamMatch(Team team, Match match) throws RDException {
        
    }
    
    public Team restoreTeamAwayTeamMatch(Match match) throws RDException {
        
    }
    
    // Returns the matches from a given away team
    public Iterator<Match> restoreTeamAwayTeamMatch(Team team) throws RDException {
        return matchManager.restoreAwayTeamMatch(team);
    }
    
    public void deleteTeamAwayTeamMatch(Team team, Match match) throws RDException {
        
    }
    
    public void storeTeamParticipatesInLeague(Team team, League league) throws RDException {
        
    }
    
    public Iterator<Team> restoreTeamParticipatesInLeague(League league) throws RDException {
        
    }
    
    public League restoreTeamParticipatesInLeague(Team team) throws RDException {
        
    }
    
    public void deleteTeamParticipatesInLeague(Team team, League league) throws RDException {
        
    }
    
    public void storeTeamWinnerOfLeague(Team team, League league) throws RDException {
        
    }
    
    public Team restoreTeamWinnerOfLeague(League league) throws RDException {
        
    }
    
    public League restoreTeamWinnerOfLeague(Team team) throws RDException {
        
    }
    
    public void deleteTeamWinnerOfLeague(Team team, League league) throws RDException {
        
    }
    
    // Stores League-SportsVenue many-to-many association -- inside LeagueSportsVenueManager
    public void storeLeagueSportsVenue(League league, SportsVenue sportsVenue) throws RDException {
        lsvManager.save(league, sportsVenue);
    }
    
    // Returns League Iterator from League-SportsVenue many-to-many association -- inside LeagueSportsVenueManager
    public Iterator<League> restoreLeagueSportsVenue(SportsVenue sportsVenue) throws RDException {
        return lsvManager.restoreWithSportsVenue(sportsVenue);
    }
    
    // Returns SportsVenue Iterator from League-SportsVenue many-to-many association -- inside LeagueSportsVenueManager
    public Iterator<SportsVenue> restoreLeagueSportsVenue(League league) throws RDException {
        return lsvManager.restoreWithLeague(league);
    }
    
    // Deletes League-SportsVenue many-to-many association -- inside LeagueSportsVenueManager
    public void deleteLeagueSportsVenue(League league, SportsVenue sportsVenue) throws RDException {
        lsvManager.delete(league, sportsVenue);
    }
    
    public void storeLeagueRound(League league, Round round) throws RDException {
        
    }
    
    public Iterator<Round> restoreLeagueRound(League league) throws RDException {
        
    }
    
    public void deleteLeagueRound(League league, Round round) throws RDException {
        
    }
    
    public void storeRoundMatch(Round round, Match match) throws RDException {
        
    }
    
    public Iterator<Match> restoreRoundMatch(Round round) throws RDException {
        
    }
    
    public void deleteRoundMatch(Round round, Match match) throws RDException {
        
    }
    
    public void storeMatchSportsVenue(Match match, SportsVenue sportsVenue) throws RDException {
        
    }
    
    public Iterator<Match> restoreMatchSportsVenue(SportsVenue sportsVenue) throws RDException {
        
    }
    
    public SportsVenue restoreMatchSportsVenue(Match match) throws RDException {
        
    }
    
    public void deleteMatchSportsVenue(Match match, SportsVenue sportsVenue) throws RDException {
    	
    }
    
 }
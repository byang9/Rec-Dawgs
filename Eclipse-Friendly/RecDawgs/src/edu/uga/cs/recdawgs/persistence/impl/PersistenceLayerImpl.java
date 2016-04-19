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
    private TeamMatchManager teamMatchManager = null;
   
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
        this.teamMatchManager = new TeamMatchManager(conn, objectLayer);
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
    
    // Saves a captain into a team
    public void storeStudentCaptainOfTeam(Student student, Team team) throws RDException {    	
    	teamManager.save(student, team);
    }
    
    // Returns a student who is the captain of the given team
    public Student restoreStudentCaptainOfTeam(Team team) throws RDException {
        return teamManager.restoreCaptain(team);
    }
    
    // Returns all teams that a certain student is captain of
    public Iterator<Team> restoreStudentCaptainOfTeam(Student student) throws RDException {
        return teamManager.restore(student);
    }
    
    // Deletes captain association from team
    public void deleteStudentCaptainOfTeam(Student student, Team team) throws RDException {
        teamManager.delete(student, team);
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
    
    // Stores the home team association to a match
    public void storeTeamHomeTeamMatch(Team team, Match match) throws RDException {
        teamMatchManager.saveHomeTeam(team, match);
    }
    
    // Returns the home teams that are from a given match
    public Team restoreTeamHomeTeamMatch(Match match) throws RDException {
        return teamMatchManager.restoreHomeTeam(match);
    }
    
    // Returns matches from a given a home team
    public Iterator<Match> restoreTeamHomeTeamMatch(Team team) throws RDException {
        return teamMatchManager.restoreHomeTeamMatch(team);
    }
    
    // Removes the home team association to a match
    public void deleteTeamHomeTeamMatch(Team team, Match match) throws RDException {
        teamMatchManager.deleteHomeTeam(team, match);
    }
    
    // Stores the away team association to a match
    public void storeTeamAwayTeamMatch(Team team, Match match) throws RDException {
    	teamMatchManager.saveAwayTeam(team, match);
    }
    
    // Returns the away team that are from a given match
    public Team restoreTeamAwayTeamMatch(Match match) throws RDException {
        return teamMatchManager.restoreAwayTeam(match);
    }
    
    // Returns the matches from a given away team
    public Iterator<Match> restoreTeamAwayTeamMatch(Team team) throws RDException {
        return teamMatchManager.restoreAwayTeamMatch(team);
    }
    
    // Removes the away team association to a match
    public void deleteTeamAwayTeamMatch(Team team, Match match) throws RDException {
        teamMatchManager.deleteAwayTeam(team, match);
    }
    
    // Saves a team and league association
    public void storeTeamParticipatesInLeague(Team team, League league) throws RDException {
        teamManager.save(team, league);
    }
    
    // Returns all teams in a specific league
    public Iterator<Team> restoreTeamParticipatesInLeague(League league) throws RDException {
        return teamManager.restore(league);
    }
    
    // Returns the league that the given team is part of
    public League restoreTeamParticipatesInLeague(Team team) throws RDException {
        return leagueManager.restore(team);
    }
    
    // Deletes the team and league association
    public void deleteTeamParticipatesInLeague(Team team, League league) throws RDException {
        teamManager.delete(team, league);
    }
    
    // Saves the winner of the league
    public void storeTeamWinnerOfLeague(Team team, League league) throws RDException {
        leagueManager.saveTeamWinnerOfLeague(team, league);
    }
    
    // Returns the winner of league
    public Team restoreTeamWinnerOfLeague(League league) throws RDException {
        return leagueManager.restoreWinner(league);
    }
    
    // Returns the league that the given team is the winner of
    public League restoreTeamWinnerOfLeague(Team team) throws RDException {
        return leagueManager.restoreWinner(team);
    }
    
    // Deletes the winner league association
    public void deleteTeamWinnerOfLeague(Team team, League league) throws RDException {
        leagueManager.delete(team, league);
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
    
    // Saves a league round association
    public void storeLeagueRound(League league, Round round) throws RDException {
        roundManager.save(league, round);
    }
    
    // Returns all rounds associated with league
    public Iterator<Round> restoreLeagueRound(League league) throws RDException {
        return roundManager.restore(league);
    }
    
    // Deletes league round association
    public void deleteLeagueRound(League league, Round round) throws RDException {
        roundManager.delete(league, round);
    }
    
    // Saves a match round association
    public void storeRoundMatch(Round round, Match match) throws RDException {
        
    }
    
    // Returns all matches that have to do with leagues
    public Iterator<Match> restoreRoundMatch(Round round) throws RDException {
        return null;
    }
    
    // Deletes round match association
    public void deleteRoundMatch(Round round, Match match) throws RDException {
        
    }
    
    // Stores a match sports venue association
    public void storeMatchSportsVenue(Match match, SportsVenue sportsVenue) throws RDException {
        
    }
    
    // Returns all matches that have to do with sports venue
    public Iterator<Match> restoreMatchSportsVenue(SportsVenue sportsVenue) throws RDException {
        return null;
    }
    
    // Returns sports venue of match
    public SportsVenue restoreMatchSportsVenue(Match match) throws RDException {
        return null;
    }
    
    // Deletes the match sports venue association
    public void deleteMatchSportsVenue(Match match, SportsVenue sportsVenue) throws RDException {
    	
    }
    
 }
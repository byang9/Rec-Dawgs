package edu.uga.cs.recdawgs.object.impl;

import java.util.Date;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.*;
import edu.uga.cs.recdawgs.entity.impl.*;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;




/**
 * The implementation of the ObjectLayer
 */
public class ObjectLayerImpl implements ObjectLayer {
    
    /* Start of Instance Variables */
    
    PersistenceLayer persistence = null;
    
    
    /* Start of Constructors */
    
    public ObjectLayerImpl() {
        this.persistence = null;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(): initialized" );
    }
    
    
    public ObjectLayerImpl(PersistenceLayer persistence) {
        this.persistence = persistence;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(persistence): initialized" );
    }
    
    
    /* Start of Methods */
    
    public void setPersistence(PersistenceLayer persistence) {
    	this.persistence = persistence;
    }
    
    // Creates admin with given attributes
    public Administrator createAdministrator(String firstName, String lastName, String userName, String password, String emailAddress) throws RDException {
        return new AdminImpl(firstName, lastName, userName, password, emailAddress);
    }

    
    // Creates blank admin
    public Administrator createAdministrator() {
        Administrator admin = new AdminImpl();
        admin.setId(-1);
        return admin;
    } 
    
    
    // Get an iterator for admins
    public Iterator<Administrator> findAdministrator(Administrator modelAdministrator) throws RDException {
        return persistence.restoreAdministrator(modelAdministrator);
    }
    
    
    // stores an admin
    public void storeAdministrator(Administrator administrator) throws RDException {
        persistence.storeAdministrator(administrator);
    }
    
    
    // Deletes an admin
    public void deleteAdministrator(Administrator administrator) throws RDException {
        persistence.deleteAdministrator(administrator);
    } 
    
    
    // Create a student with given attributes
    public Student createStudent(String firstName, String lastName, String userName, String password, String emailAddress, String studentId, String major, String address) throws RDException {
        return new StudentImpl(firstName, lastName, userName, password, emailAddress, studentId, major, address);
    }

    
    // Creates a blank student
    public Student createStudent() {
        Student student = new StudentImpl();
        student.setId(-1);
        return student;
    }
    
    
    // Returns a student iterator
    public Iterator<Student> findStudent(Student modelStudent) throws RDException {
        return persistence.restoreStudent(modelStudent);
    }
    
    
    // stores a student
    public void storeStudent(Student student) throws RDException {
        persistence.storeStudent(student);
    }
    
    
    // Deletes a student
    public void deleteStudent(Student student) throws RDException {
        persistence.deleteStudent(student);
    }      

    
    // Creates a league with the given attributes
    public League createLeague(String name, String leagueRules, String matchRules, boolean isIndoor, int minTeams, int maxTeams, int minPlayers, int maxPlayers) throws RDException {
        return new LeagueImpl(name, leagueRules, matchRules, isIndoor, minTeams, maxTeams, minPlayers, maxPlayers);
    }

    
    // Creates a blank league
    public League createLeague() {
        League league = new LeagueImpl();
        league.setId(-1);
        return league;
    }

    
    // Returns a league iterator
    public Iterator<League> findLeague(League modelLeague) throws RDException {
        return persistence.restoreLeague(modelLeague);
    }
    
    
    // stores a league
    public void storeLeague(League league) throws RDException {
        persistence.storeLeague(league);
    }
    
    
    // Deletes a league
    public void deleteLeague(League league) throws RDException {
        persistence.deleteLeague(league);
    } 

    
    // Creates Team with a given name
    public Team createTeam(String name) throws RDException {
        Team team = new TeamImpl(name, null, null);
        team.setId(-1);
        return team;
    }

    
    // Creates a blank team
    public Team createTeam() {
        Team team = new TeamImpl(null, null, null);
        team.setId(-1);
        return team;
    }

    
    // Returns a team iterator
    public Iterator<Team> findTeam(Team modelTeam) throws RDException {
        return persistence.restoreTeam(modelTeam);
    }
    
    
    // Stores Team in persistenceLayer
    public void storeTeam(Team team) throws RDException {
        persistence.storeTeam(team);
    }
    
    
    // Deletes a team
    public void deleteTeam(Team team) throws RDException {
        persistence.deleteTeam(team);
    }   
 
    
    // Creates a sports venue with the given attributes
    public SportsVenue createSportsVenue(String name, String address, boolean isIndoor) throws RDException {
        SportsVenue sv = new SportsVenueImpl(name, address, isIndoor);
        sv.setId(-1);
        return sv;
    }

    
    // Creates a blank sports venue
    public SportsVenue createSportsVenue() {
        return new SportsVenueImpl();
    }

    
    // Returns a sports venue iterator
    public Iterator<SportsVenue> findSportsVenue(SportsVenue modelSportsVenue) throws RDException {
        return persistence.restoreSportsVenue(modelSportsVenue);
    }
    
    
    // stores a sports venue
    public void storeSportsVenue(SportsVenue sportsVenue) throws RDException {
        persistence.storeSportsVenue(sportsVenue);
    }
    
    
    // Deletes a sports venue
    public void deleteSportsVenue(SportsVenue sportsVenue) throws RDException {
        persistence.deleteSportsVenue(sportsVenue);
    }    

    
    // Creates a match with the given attributes
    public Match createMatch(int homePoints, int awayPoints, Date date, boolean isCompleted, Team homeTeam, Team awayTeam) throws RDException {
        return new MatchImpl(homePoints, awayPoints, date, isCompleted, homeTeam, awayTeam);
    }

    
    // Creates a blank match
    public Match createMatch() {
        Match match = new MatchImpl();
        match.setId(-1);
        return match;
    }

    
    // Returns a match iterator
    public Iterator<Match> findMatch(Match modelMatch) throws RDException {
        return persistence.restoreMatch(modelMatch);
    }
    
    
    // stores a match
    public void storeMatch(Match match) throws RDException {
        persistence.storeMatch(match);
    }
    
    
    // Deletes a match
    public void deleteMatch(Match match) throws RDException {
        persistence.deleteMatch(match);
    }    

    
    // Creates a round with the given attribute
    public Round createRound(int number) throws RDException {
        return new RoundImpl(number);
    }

    
    // Creates a blank round
    public Round createRound() {
        Round round = new RoundImpl();
        round.setId(-1);
        return round;
    }
    
    
    // Returns a round iterator
    public Iterator<Round> findRound(Round modelRound) throws RDException {
        return persistence.restoreRound(modelRound);
    }
    
    
    // stores a round
    public void storeRound(Round round) throws RDException {
        persistence.storeRound(round);
    }
    
    
    // Deletes a round
    public void deleteRound(Round round) throws RDException {
        persistence.deleteRound(round);
    }    

    
    // Creates a score report
    public ScoreReport createScoreReport(int homePoints, int awayPoints, Date date, Student student, Match match) throws RDException {
        return new ScoreReportImpl(homePoints, awayPoints, date, student, match);
    }

    
    // Creates a blank score report
    public ScoreReport createScoreReport() {
        ScoreReport sr = new ScoreReportImpl();
        sr.setId(-1);
        return sr;
    }

    
    
    public Iterator<ScoreReport> findScoreReport(ScoreReport modelScoreReport) throws RDException {
        return persistence.restoreScoreReport(modelScoreReport);
    }
    
    
    
    public void storeScoreReport(ScoreReport scoreReport) throws RDException {
        persistence.storeScoreReport(scoreReport);
    }
    
    
    
    public void deleteScoreReport(ScoreReport scoreReport) throws RDException {
        persistence.deleteScoreReport(scoreReport);
    }    

    
    
    public void createStudentCaptainOfTeam(Student student, Team team) throws RDException {
        persistence.storeStudentCaptainOfTeam(student, team);
    }
    
    
//    public Iterator<Student> findStudentCaptainOfTeam(Student modelStudent, Team team) throws RDException {
//        return persistence.restoreStudent(modelStudent);
//    }
    
    
    public Student restoreStudentCaptainOfTeam(Team team) throws RDException {
        return persistence.restoreStudentCaptainOfTeam(team);
    }
    
    
    
    public Iterator<Team> restoreStudentCaptainOfTeam(Student student) throws RDException {
        return persistence.restoreStudentCaptainOfTeam(student);
    }
    
    
    
    public void deleteStudentCaptainOfTeam(Student student, Team team, Student newCaptain) throws RDException {
        persistence.deleteStudentCaptainOfTeam(student, team, newCaptain);
    }

    
    
    public void createStudentMemberOfTeam(Student student, Team team) throws RDException {
        persistence.storeStudentMemberOfTeam(student, team);
    }
    
    
    
    public Iterator<Student> restoreStudentMemberOfTeam(Team team) throws RDException {
        return persistence.restoreStudentMemberOfTeam(team);
    }
    
    
    
    public Iterator<Team> restoreStudentMemberOfTeam(Student student) throws RDException {
        return persistence.restoreStudentMemberOfTeam(student);
    }
    
    
    
    public void deleteStudentMemberOfTeam(Student student, Team team) throws RDException {
        persistence.deleteStudentMemberOfTeam(student, team);
    }

    
    
    public void createTeamHomeTeamMatch(Team team, Match match) throws RDException {
        persistence.storeTeamHomeTeamMatch(team, match);
    }
    
   
    
    public Team restoreTeamHomeTeamMatch(Match match) throws RDException {
        return persistence.restoreTeamHomeTeamMatch(match);
    }
    
    
    
    public Iterator<Match> restoreTeamHomeTeamMatch(Team team) throws RDException {
        return persistence.restoreTeamHomeTeamMatch(team);
    }
    
    
    
    public void deleteTeamHomeTeamMatch(Team team, Match match) throws RDException {
        persistence.deleteTeamHomeTeamMatch(team, match);
    }

    
    
    public void createTeamAwayTeamMatch(Team team, Match match) throws RDException {
        persistence.storeTeamAwayTeamMatch(team, match);
    }
    
    
    
    public Team restoreTeamAwayTeamMatch(Match match) throws RDException {
        return persistence.restoreTeamAwayTeamMatch(match);
    }
    
    
    
    public Iterator<Match> restoreTeamAwayTeamMatch(Team team) throws RDException {
        return persistence.restoreTeamAwayTeamMatch(team);
    }
    
    
    
    public void deleteTeamAwayTeamMatch(Team team, Match match) throws RDException {
        persistence.deleteTeamAwayTeamMatch(team, match);
    }

    
    
    public void createTeamParticipatesInLeague(Team team, League league) throws RDException {
        persistence.storeTeamParticipatesInLeague(team, league);
    }
    
    
    
    public League restoreTeamParticipatesInLeague(Team team) throws RDException {
        return persistence.restoreTeamParticipatesInLeague(team);
    }
    
    
    
    public Iterator<Team> restoreTeamParticipatesInLeague(League league) throws RDException {
        return persistence.restoreTeamParticipatesInLeague(league);
    }
    
    
    
    public void deleteTeamParticipatesInLeague(Team team, League league) throws RDException {
        persistence.deleteTeamParticipatesInLeague(team, league);
    }

    
    
    public void createTeamWinnerOfLeague(Team team, League league) throws RDException {
        persistence.storeTeamWinnerOfLeague(team, league);
    }
    
    
    
    public League restoreTeamWinnerOfLeague(Team team) throws RDException {
        return persistence.restoreTeamWinnerOfLeague(team);
    }
    
    
    
    public Team restoreTeamWinnerOfLeague(League league) throws RDException {
        return persistence.restoreTeamWinnerOfLeague(league);
    }
    
    
    
    public void deleteTeamWinnerOfLeague(Team team, League league) throws RDException {
        persistence.deleteTeamWinnerOfLeague(team, league);
    }


    
    
    public void createLeagueSportsVenue(League league, SportsVenue sportsVenue) throws RDException {
        persistence.storeLeagueSportsVenue(league, sportsVenue);
    }
    
    
    
    public Iterator<SportsVenue> restoreLeagueSportsVenue(League league) throws RDException {
        return persistence.restoreLeagueSportsVenue(league);
    }
    
    
    // Restores league
    public Iterator<League> restoreLeagueSportsVenue(SportsVenue sportsVenue) throws RDException {
        return persistence.restoreLeagueSportsVenue(sportsVenue);
    }
    
    
    // Deletes league sports venue
    public void deleteLeagueSportsVenue(League league, SportsVenue sportsVenue) throws RDException {
        persistence.deleteLeagueSportsVenue(league, sportsVenue);
    }

    
    // Creates a league round
    public void createLeagueRound(League league, Round round) throws RDException {
        persistence.storeLeagueRound(league, round);
    }
    
    
    // Returns a round iterator
    public Iterator<Round> restoreLeagueRound(League league) throws RDException {
        return persistence.restoreLeagueRound(league);
    }
    
    
    // Deletes league round
    public void deleteLeagueRound(League league, Round round) throws RDException {
        persistence.deleteLeagueRound(league, round);
    }


    // Creates a round match
    public void createRoundMatch(Round round, Match match) throws RDException {
        persistence.storeRoundMatch(round, match);
    }
    
    
    // Returns a match iterator
    public Iterator<Match> restoreRoundMatch(Round round) throws RDException {
        return persistence.restoreRoundMatch(round);
    }
    
    
    // Deletes round match
    public void deleteRoundMatch(Round round, Match match) throws RDException {
        persistence.deleteRoundMatch(round, match);
    }


    // Creates a match sports venue
    public void createMatchSportsVenue(Match match, SportsVenue sportsVenue) throws RDException {
        persistence.storeMatchSportsVenue(match, sportsVenue);
    }
    
    
    // Creates a new sports venue from the given match
    public SportsVenue restoreMatchSportsVenue(Match match) throws RDException {
        return persistence.restoreMatchSportsVenue(match);
    }
    
    
    // Returns a match iterator
    public Iterator<Match> restoreMatchSportsVenue(SportsVenue sportsVenue) throws RDException {
        return persistence.restoreMatchSportsVenue(sportsVenue);
    }
    
    
    // Deletes a match sports venue
    public void deleteMatchSportsVenue(Match match, SportsVenue sportsVenue) throws RDException {
        persistence.deleteMatchSportsVenue(match, sportsVenue);
    }

}

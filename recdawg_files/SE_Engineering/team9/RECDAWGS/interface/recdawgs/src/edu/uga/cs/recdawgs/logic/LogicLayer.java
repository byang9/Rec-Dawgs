package edu.uga.cs.recdawgs.logic;


import java.util.Date;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.session.Session;


public interface LogicLayer
{
	// Find All
    public List<Team>			findAllTeams() throws RDException;
    public List<Student>        findAllStudents() throws RDException;
    public List<Administrator> 	findAllAdmins() throws RDException;
    public List<League> 	 	findAllLeagues() throws RDException;
    public List<Match>			findAllMatches() throws RDException;
    public List<ScoreReport>	findAllScoreReports() throws RDException;
    public List<SportsVenue> 	findAllSportsVenues() throws RDException;
    
    // Find My
    public List<Team> 		  	findMyTeams(Student modelStudent) throws RDException;
	
	// Find Object-to-Object
	public List<Student>      	findTeamMembers(String teamName) throws RDException;
	public List<Team>      		findTeamsOfLeague(String leagueName) throws RDException;
	public List<League>			findLeaguesOfSV(String svName) throws RDException;
	public List<SportsVenue>	findSVsOfLeague(String leagueName) throws RDException;
	
	// Join
    public long               	joinTeam(long personId, String clubName) throws RDException;
    
    // Create
    public long               	createTeam(String teamName) throws RDException;
    public long               	createStudent(String userName, String password, String email, String firstName, String lastName, String studentId, String major, String address) throws RDException;
    public long               	createLeague(String name, boolean isIndoor, int minTeams, int maxTeams, int minTeamMembers, int maxTeamMembers, String matchRules, String leagueRules) throws RDException;
    public long					createSportsVenue(String name, String address, boolean isIndoor) throws RDException;
    public long					createScoreReport(String homeName, String awayName, int homePoints, int awayPoints, Date date, Student student, Match match) throws RDException;
    
    // Login/Logout
    public void               	logout(String ssid) throws RDException;
    public String             	login(Session session, String userName, String password) throws RDException;
    public Student             	retrieveStudent(Session session) throws RDException;
	public boolean 			  	isAdmin(User user);
}

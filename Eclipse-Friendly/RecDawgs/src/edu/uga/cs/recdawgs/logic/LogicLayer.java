package edu.uga.cs.recdawgs.logic;


import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.session.Session;


public interface LogicLayer
{
	// Find All
    public List<Team>			findAllTeams() throws RDException;
    public List<User>         	findAllUsers() throws RDException;
    public List<Administrator> 	findAllAdmins() throws RDException;
    public List<League> 	 	findAllLeagues() throws RDException;
    public List<Match>			findAllMatches() throws RDException;
    public List<ScoreReport>	findAllScoreReports() throws RDException;
    
    // Find My
    public List<Team> 		  	findMyTeams(Student user);
	public List<League>		  	findMyLeagues(Student user);
	public List<Match>			findMyMatches(Student user);
	public List<ScoreReport>	findMyScoreReports(Student user);
	
	// Find Object-to-Object
	public List<Student>      	findTeamMembers(String teamName) throws RDException;
	public List<Team>      		findTeamsOfLeague(String leagueName) throws RDException;
	
	// Join
    public long               	joinTeam(User user, Team team) throws RDException;
    public long               	joinTeam(long personId, String clubName) throws RDException;
    
    // Create
    public long               	createTeam(String teamName) throws RDException;
    public long               	createStudent(String userName, String password, String email, String firstName, String lastName, String studentId, String major, String address) throws RDException;
    public long               	createLeague(String name, boolean isIndoor, int minTeams, int maxTeams, int minTeamMembers, int maxTeamMembers, String matchRules, String leagueRules) throws RDException;
    public long					createSportsVenue(String name, String address, boolean isIndoor) throws RDException;
    public long					createScoreReport(String name, String address, boolean isIndoor) throws RDException;
    
    // Login/Logout
    public void               	logout(String ssid) throws RDException;
    public String             	login(Session session, String userName, String password) throws RDException;
	public boolean 			  	isAdmin(User user);
	public boolean				isTeamCaptain(User user);
}

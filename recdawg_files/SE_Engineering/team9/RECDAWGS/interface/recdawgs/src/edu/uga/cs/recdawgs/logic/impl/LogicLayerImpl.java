/**
 * 
 */
package edu.uga.cs.recdawgs.logic.impl;

import java.sql.Connection;
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
import edu.uga.cs.recdawgs.logic.LogicLayer;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImpl;
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;
import edu.uga.cs.recdawgs.persistence.impl.PersistenceLayerImpl;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;


/**
 * @author Logan Gordon Jahnke
 * @version 1.0
 */
public class LogicLayerImpl implements LogicLayer {
    private ObjectLayer objectLayer = null;

    // !!!!!!!!!!!!!!!!!!!!
    // !!! Constructors !!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public LogicLayerImpl( Connection conn ) {
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
        objectLayer.setPersistence( persistenceLayer );
        System.out.println( "LogicLayerImpl.LogicLayerImpl(conn): initialized" );
    }
    
    
    public LogicLayerImpl( ObjectLayer objectLayer ) {
        this.objectLayer = objectLayer;
        System.out.println( "LogicLayerImpl.LogicLayerImpl(objectLayer): initialized" );
    }
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!!! Find All !!!!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public List<Team> findAllTeams() throws RDException {
        FindTeamsCtrl ctrlFindAllTeams = new FindTeamsCtrl( objectLayer );
        return ctrlFindAllTeams.findAllTeams();
    }

    public List<Student> findAllStudents() throws RDException {
        FindStudentsCtrl ctrlFindAllUsers = new FindStudentsCtrl( objectLayer );
        return ctrlFindAllUsers.findAllStudents();
    }
    
    
    public List<Administrator> findAllAdmins() throws RDException {
        FindAllAdminsCtrl ctrlFindAllAdmins = new FindAllAdminsCtrl( objectLayer );
        return ctrlFindAllAdmins.findAllAdministrators();
    }
    

	public List<League> findAllLeagues() throws RDException {
		FindLeaguesCtrl ctrlFindAllLeagues = new FindLeaguesCtrl( objectLayer );
		return ctrlFindAllLeagues.findAllLeagues();
	}
	
	
	public List<SportsVenue> findAllSportsVenues() throws RDException {
		FindSportsVenuesCtrl ctrlFindAllSportsVenues = new FindSportsVenuesCtrl( objectLayer );
		return ctrlFindAllSportsVenues.findAllSportsVenues();
	}


	public List<Match> findAllMatches() throws RDException {
		FindMatchesCtrl ctrlFindAllMatches = new FindMatchesCtrl( objectLayer );
		return ctrlFindAllMatches.findAllMatches();
	}


	public List<ScoreReport> findAllScoreReports() throws RDException {
		FindScoreReportsCtrl ctrlFindAllScoreReports = new FindScoreReportsCtrl( objectLayer );
		return ctrlFindAllScoreReports.findAllScoreReports();
	}
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!!! Find -My !!!!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public List<Team> findMyTeams(Student modelStudent) throws RDException {
    	FindTeamsCtrl ctrlFindMyTeams = new FindTeamsCtrl( objectLayer );
        return ctrlFindMyTeams.findMyTeams(modelStudent);
    }

    public List<Team> findMyAccount(Student modelStudent) throws RDException {
        FindStudentsCtrl ctrlFindMyTeams = new FindStudentsCtrl( objectLayer );
        return ctrlFindMyTeams.findMyAccount(modelStudent);
    }
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!! Obj-to-Obj !!!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public List<Student> findTeamMembers(String teamName) throws RDException {
        FindAssociationsCtrl ctrlFindTeamMembers = new FindAssociationsCtrl( objectLayer );
        return ctrlFindTeamMembers.findTeamMembers( teamName );
    }
    
    public List<Team> findTeamsOfLeague(String leagueName) throws RDException {
    	FindAssociationsCtrl ctrlFindTeamsOfLeague = new FindAssociationsCtrl( objectLayer );
		return ctrlFindTeamsOfLeague.findTeamsOfLeague(leagueName);
	}
    
    public List<League> findLeaguesOfSV(String svName) throws RDException {
    	FindAssociationsCtrl ctrlFindLeaguesofSV = new FindAssociationsCtrl( objectLayer );
		return ctrlFindLeaguesofSV.findLeaguesOfSV(svName);
	}
    
    public List<SportsVenue> findSVsOfLeague(String leagueName) throws RDException {
    	FindAssociationsCtrl ctrlFindLeaguesofSV = new FindAssociationsCtrl( objectLayer );
		return ctrlFindLeaguesofSV.findSVOfLeague(leagueName);
	}
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!!!!! Join !!!!!!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public long joinTeam(long userId, String teamName) throws RDException {
        JoinTeamCtrl ctrlJoinTeam = new JoinTeamCtrl( objectLayer );
        return ctrlJoinTeam.joinTeam( userId, teamName );
    }
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!!!! Create !!!!!!
    // !!!!!!!!!!!!!!!!!!!!

    public long createTeam(String teamName) throws RDException {
    	CreateCtrl ctrlCreateTeam = new CreateCtrl( objectLayer );
        return ctrlCreateTeam.createTeam( teamName );
	}


    public long createTeam(String teamName, String leagueName, long userID) throws RDException {
        CreateCtrl ctrlCreateTeam = new CreateCtrl( objectLayer );
        return ctrlCreateTeam.createTeam( teamName, leagueName, userID );
    }


	public long createStudent(String username, String password, String email, String firstname, String lastname, String studentId, String major, String address) throws RDException {
		CreateCtrl ctrlCreateStudent = new CreateCtrl( objectLayer );
        return ctrlCreateStudent.createStudent( username,  password, email, firstname, lastname, address, studentId, major );
	}


	public long createLeague(String name, boolean isIndoor, int minTeams, int maxTeams, int minTeamMembers, int maxTeamMembers, String matchRules, String leagueRules) throws RDException {
		CreateCtrl ctrlCreateLeague = new CreateCtrl( objectLayer );
        return ctrlCreateLeague.createLeague( name,  isIndoor, minTeams, maxTeams, minTeamMembers, maxTeamMembers, matchRules, leagueRules );
	}


	public long createSportsVenue(String name, String address, boolean isIndoor) throws RDException {
		CreateCtrl ctrlCreateStudent = new CreateCtrl( objectLayer );
        return ctrlCreateStudent.createSportsVenue( name,  address, isIndoor );
	}


	public long createScoreReport(String homeName, String awayName, int homePoints, int awayPoints, Date date, Student student, Match match) throws RDException {
		CreateCtrl ctrlCreateStudent = new CreateCtrl( objectLayer );
        return ctrlCreateStudent.createScoreReport( homeName,  awayName, homePoints, awayPoints, date, student, match );
	}
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!! Login/Logout !!!
    // !!!!!!!!!!!!!!!!!!!!

    public void logout( String ssid ) throws RDException {
        SessionManager.logout( ssid );
    }

    
    public String login( Session session, String userName, String password ) throws RDException {
        LoginCtrl ctrlVerifyUser = new LoginCtrl( objectLayer );
        return ctrlVerifyUser.login( session, userName, password );
    }
    
    
    public boolean isAdmin(User user) {
    	try {
			objectLayer.findAdministrator((Administrator)user);
		} catch (RDException e) {
			return false;
		}
    	return true;
    }
    
    public Student retrieveStudent(Session session) throws RDException {
    	if (objectLayer.findStudent((Student)session.getUser()).hasNext())
    		return objectLayer.findStudent((Student)session.getUser()).next();
    	else
    		throw new RDException("Your account does not exist. How did you log in?");
    }
    
}

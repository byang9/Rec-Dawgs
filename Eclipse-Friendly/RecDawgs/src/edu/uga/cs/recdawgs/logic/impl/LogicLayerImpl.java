/**
 * 
 */
package edu.uga.cs.recdawgs.logic.impl;

import java.sql.Connection;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.ScoreReport;
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
        FindAllTeamsCtrl ctrlFindAllTeams = new FindAllTeamsCtrl( objectLayer );
        return ctrlFindAllTeams.findAllTeams();
    }

    public List<Student> findAllStudents() throws RDException {
        FindAllStudentsCtrl ctrlFindAllUsers = new FindAllStudentsCtrl( objectLayer );
        return ctrlFindAllUsers.findAllStudents();
    }
    
    public List<Administrator> findAllAdmins() throws RDException {
        FindAllAdminsCtrl ctrlFindAllAdmins = new FindAllAdminsCtrl( objectLayer );
        return ctrlFindAllAdmins.findAllAdministrators();
    }
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!!! Find -My !!!!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public List<Team> findMyTeams(Student user) {
    	FindMyTeamsCtrl ctrlFindMyTeams = new FindMyTeamsCtrl( objectLayer );
        return ctrlFindMyTeams.findMyTeams();
    }
    
    
    public List<League> findMyLeagues(Student user) {
    	FindMyLeaguesCtrl ctrlFindMyLeagues = new FindMyLeaguesCtrl( objectLayer );
        return ctrlFindMyLeagues.findMyLeagues();
    }
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!! Obj-to-Obj !!!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public List<Student> findTeamMembers(String teamName) throws RDException {
        FindTeamMembersCtrl ctrlFindTeamMembers = new FindTeamMembersCtrl( objectLayer );
        return ctrlFindTeamMembers.findTeamMembers( teamName );
    }
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!!!!! Join !!!!!!!
    // !!!!!!!!!!!!!!!!!!!!
    
    public long joinTeam(User user, Team team) throws RDException {
        return 0;
    }

    
    public long joinTeam(long userId, String teamName) throws RDException {
        JoinTeamCtrl ctrlJoinTeam = new JoinTeamCtrl( objectLayer );
        return ctrlJoinTeam.joinTeam( userId, teamName );
    }
    
    // !!!!!!!!!!!!!!!!!!!!
    // !!!!!! Create !!!!!!
    // !!!!!!!!!!!!!!!!!!!!

    public long createTeam(String teamName) throws RDException {
    	CreateTeamCtrl ctrlCreateTeam = new CreateTeamCtrl( objectLayer );
        return ctrlCreateTeam.createTeam( teamName );
	}


	public long createStudent(String username, String password, String email, String firstname, String lastname, String studentId, String major, String address) throws RDException {
		CreateStudentCtrl ctrlCreateStudent = new CreateStudentCtrl( objectLayer );
        return ctrlCreateStudent.createStudent( username,  password, email, firstname, lastname, address, studentId, major );
	}


	public long createLeague(String name, boolean isIndoor, int minTeams, int maxTeams, int minTeamMembers, int maxTeamMembers, String matchRules, String leagueRules) throws RDException {
		return 0;
	}


	public long createSportsVenue(String name, String address, boolean isIndoor) throws RDException {
		return 0;
	}


	public long createScoreReport(String name, String address, boolean isIndoor) throws RDException {
		return 0;
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


	public List<User> findAllUsers() throws RDException {
		return null;
	}


	public List<League> findAllLeagues() throws RDException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Match> findAllMatches() throws RDException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<ScoreReport> findAllScoreReports() throws RDException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Match> findMyMatches(Student user) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<ScoreReport> findMyScoreReports(Student user) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Team> findTeamsOfLeague(String leagueName) throws RDException {
		// TODO Auto-generated method stub
		return null;
	}
}

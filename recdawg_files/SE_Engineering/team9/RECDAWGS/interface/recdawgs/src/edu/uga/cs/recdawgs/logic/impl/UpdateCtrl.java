//
// A control class to implement the 'Create team' use case
//
//

package edu.uga.cs.recdawgs.logic.impl;


import java.util.Date;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class UpdateCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public UpdateCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
        
    public Student updateStudent( String username, String password, String email, String firstname, String lastname, String address, String studentId, String major, long id ) throws RDException { 
        Student               student  = null;
        Student               modelStudent  = null;
        Iterator<Student>     studentIter  = null;

        // check if the userName already exists
        modelStudent = objectLayer.createStudent();
        modelStudent.setId( id );
        studentIter = objectLayer.findStudent( modelStudent );
        while( studentIter.hasNext() ) {
            student = studentIter.next();
        }
        
        // check if the student actually exists, and if so, throw an exception
        if( student == null )
            throw new RDException( "This account does not exist. Cannot modify account that does not exist." );
        
        student.setUserName(username);
        student.setFirstName(firstname);
        student.setLastName(lastname);
        student.setPassword(password);
        student.setEmailAddress(email);
        student.setStudentId(studentId);
        student.setMajor(major);
        student.setAddress(address);

        objectLayer.storeStudent( student );

        return student;
    }

    public League updateLeague(long id, String newName, int minTeams, int maxTeams, int minMems, int maxMems, String matchRules, String leagueRules) throws RDException {
        League               league  = null;
        League               modelLeague  = null;
        Iterator<League>     leagueIter  = null;

        // check if the userName already exists
        modelLeague = objectLayer.createLeague();
        modelLeague.setId( id );
        leagueIter = objectLayer.findLeague( modelLeague );
        while( leagueIter.hasNext() ) {
            league = leagueIter.next();
        }
        
        // check if the league actually exists, and if so, throw an exception
        if( league == null )
            throw new RDException( "This league does not exist. Cannot modify league that does not exist." );
        
        league.setName(newName);
        league.setMinTeams(minTeams);
        league.setMaxTeams(maxTeams);
        league.setMinMembers(minMems);
        league.setMaxMembers(maxMems);
        league.setMatchRules(matchRules);
        league.setLeagueRules(leagueRules);

        objectLayer.storeLeague( league );

        return league;
    }

    public Team updateTeam(long id, String newName) throws RDException {
        Team               team  = null;
        Team               modelTeam  = null;
        Iterator<Team>     teamIter  = null;

        // check if the userName already exists
        modelTeam = objectLayer.createTeam();
        modelTeam.setId( id );
        teamIter = objectLayer.findTeam( modelTeam );
        while( teamIter.hasNext() ) {
            team = teamIter.next();
        }
        
        // check if the team actually exists, and if so, throw an exception
        if( team == null )
            throw new RDException( "This team does not exist. Cannot modify team that does not exist." );
        
        team.setName(newName);

        objectLayer.storeTeam( team );

        return team;
    }

    public SportsVenue updateSportsVenue(long id, String newName, boolean isIndoor, String address) throws RDException {
        SportsVenue               venue  = null;
        SportsVenue               modelSportsVenue  = null;
        Iterator<SportsVenue>     sportsVenueIter  = null;

        // check if the userName already exists
        modelSportsVenue = objectLayer.createSportsVenue();
        modelSportsVenue.setId( id );
        sportsVenueIter = objectLayer.findSportsVenue( modelSportsVenue );
        while( sportsVenueIter.hasNext() ) {
            venue = sportsVenueIter.next();
        }
        
        // check if the SportsVenue actually exists, and if so, throw an exception
        if( venue == null )
            throw new RDException( "This venue does not exist. Cannot modify venue that does not exist." );
        
        venue.setName(newName);
        venue.setIsIndoor(isIndoor);
        venue.setAddress(address);

        objectLayer.storeSportsVenue( venue );

        return venue;
    }



}
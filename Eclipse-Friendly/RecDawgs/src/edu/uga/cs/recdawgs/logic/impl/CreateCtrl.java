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



public class CreateCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createTeam( String team_name )
            throws RDException
    { 
        Team 		    team  = null;
        Team                modelTeam = null;
        Iterator<Team>      teamIter = null;

        // check if a team with this name already exists (name is unique)
        modelTeam = objectLayer.createTeam();
        modelTeam.setName( team_name );
        teamIter = objectLayer.findTeam( modelTeam );
        if( teamIter.hasNext() )
            throw new RDException( "A team with this name already exists: " + team_name );

        team = objectLayer.createTeam(team_name);
        objectLayer.storeTeam( team );

        return team.getId();
    }
    
    public long createStudent( String username, String password, String email, String firstname, String lastname, String address, String studentId, String major ) throws RDException { 
        Student               student  = null;
        Student               modelStudent  = null;
        Iterator<Student>     studentIter  = null;

        // check if the userName already exists
        modelStudent = objectLayer.createStudent();
        modelStudent.setUserName( username );
        studentIter = objectLayer.findStudent( modelStudent );
        while( studentIter.hasNext() ) {
            student = studentIter.next();
        }
        
        // check if the student actually exists, and if so, throw an exception
        if( student != null )
            throw new RDException( "A student with this user name already exists" );
        
        student = objectLayer.createStudent( firstname, lastname, username, password, email, studentId, major, address);
        objectLayer.storeStudent( student );

        return student.getId();
    }
    
    public long createLeague( String name, boolean isIndoor, int minTeams, int maxTeams, int minMembers, int maxMembers, String matchRules, String leagueRules ) throws RDException { 
        League               league  = null;
        League               modelLeague  = null;
        Iterator<League>     leagueIter  = null;

        // check if the userName already exists
        modelLeague = objectLayer.createLeague();
        modelLeague.setName( name );
        leagueIter = objectLayer.findLeague( modelLeague );
        while( leagueIter.hasNext() ) {
        	league = leagueIter.next();
        }
        
        // check if the league actually exists, and if so, throw an exception
        if( league != null )
            throw new RDException( "A league with this user name already exists" );
        
        league = objectLayer.createLeague(name, leagueRules, matchRules, isIndoor, minTeams, maxTeams, minMembers, maxMembers);
        objectLayer.storeLeague( league );

        return league.getId();
    }
    
    public long createSportsVenue(String name, String address, boolean isIndoor) throws RDException { 
        SportsVenue               sportsVenue  = null;
        SportsVenue               modelSportsVenue  = null;
        Iterator<SportsVenue>     sportsVenueIter  = null;

        // check if the userName already exists
        modelSportsVenue = objectLayer.createSportsVenue();
        modelSportsVenue.setName( name );
        sportsVenueIter = objectLayer.findSportsVenue( modelSportsVenue );
        while( sportsVenueIter.hasNext() ) {
        	sportsVenue = sportsVenueIter.next();
        }
        
        // check if the sportsVenue actually exists, and if so, throw an exception
        if( sportsVenue != null )
            throw new RDException( "A Sports Venue with this user name already exists" );
        
        sportsVenue = objectLayer.createSportsVenue(name, address, isIndoor);
        objectLayer.storeSportsVenue( sportsVenue );

        return sportsVenue.getId();
    }
    
    public long createScoreReport(String homeName, String awayName, int homePoints, int awayPoints, Date date, Student student, Match match) throws RDException { 
        ScoreReport               scoreReport  = null;
        ScoreReport               modelScoreReport  = null;
        Iterator<ScoreReport>     scoreReportIter  = null;

        // check if the userName already exists
        modelScoreReport = objectLayer.createScoreReport();
        modelScoreReport.setStudent(student);
        modelScoreReport.setMatch(match);
        scoreReportIter = objectLayer.findScoreReport( modelScoreReport );
        while( scoreReportIter.hasNext() ) {
        	scoreReport = scoreReportIter.next();
        }
        
        // check if the Score Report actually exists, and if so, throw an exception
        if( scoreReport != null )
            throw new RDException( "This Score Report already exists" );
        
        scoreReport = objectLayer.createScoreReport(homePoints, awayPoints, date, student, match);
        objectLayer.storeScoreReport( scoreReport );

        return scoreReport.getId();
    }
}


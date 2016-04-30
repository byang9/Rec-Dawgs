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



public class DeleteCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public DeleteCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public void deleteStudent( long id ) throws RDException { 
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
            throw new RDException( "User account does not exist" );

        objectLayer.deleteStudent( student );
    }

    public void deleteTeam( String name ) throws RDException { 
        Team               team  = null;
        Team               modelTeam  = null;
        Iterator<Team>     teamIter  = null;

        // check if the userName already exists
        modelTeam = objectLayer.createTeam();
        modelTeam.setName( name );
        teamIter = objectLayer.findTeam( modelTeam );
        while( teamIter.hasNext() ) {
            team = teamIter.next();
        }
        
        // check if the team actually exists, and if so, throw an exception
        if( team == null )
            throw new RDException( "Team does not exist" );

        objectLayer.deleteTeam( team );
    }

    public void leaveTeam( long id, String nameOfTeam ) throws RDException { 
        Student               student  = null;
        Student               modelStudent  = null;
        Iterator<Student>     studentIter  = null;
        Team               team  = null;
        Team               modelTeam  = null;
        Iterator<Team>     teamIter  = null;

        // check if the userName already exists
        modelStudent = objectLayer.createStudent();
        modelStudent.setId( id );
        studentIter = objectLayer.findStudent( modelStudent );
        while( studentIter.hasNext() ) {
            student = studentIter.next();
        }

        System.out.println("Name of team trying to leave: " + nameOfTeam);

        // check if the userName already exists
        modelTeam = objectLayer.createTeam();
        modelTeam.setName( nameOfTeam );
        teamIter = objectLayer.findTeam( modelTeam );
        while( teamIter.hasNext() ) {
            team = teamIter.next();
        }
        
        // check if the student actually exists, and if so, throw an exception
        if( student == null )
            throw new RDException( "User account does not exist" );

        // check if the student actually exists, and if so, throw an exception
        if( team == null )
            throw new RDException( "Team does not exist" );
        
        objectLayer.deleteStudentMemberOfTeam( student, team );
        if (team.getCaptain().getUserName().equals(student.getUserName()))
            objectLayer.deleteStudentCaptainOfTeam(student, team);
    }
    
    public void deleteLeague( String name ) throws RDException { 
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
        
        // check if the team actually exists, and if so, throw an exception
        if( league == null )
            throw new RDException( "League does not exist" );

        objectLayer.deleteLeague( league );
    }
}


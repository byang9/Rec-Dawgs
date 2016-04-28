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



}
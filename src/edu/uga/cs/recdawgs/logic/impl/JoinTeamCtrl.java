//
// A control class to implement the 'Join a team' use case
//
//


package edu.uga.cs.recdawgs.logic.impl;




import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class JoinTeamCtrl {
    
    private ObjectLayer objectLayer = null;

    public JoinTeamCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public long joinTeam( long studentId, String teamName )
            throws RDException
    {
        Student               student = null;
        Student               modelStudent = null;
        Iterator<Student>     studentIter = null;
        Team                 team = null;
        Team                 modelTeam = null;
        Iterator<Team>       teamIter = null;

        modelTeam = objectLayer.createTeam();
        modelTeam.setName( teamName );
        teamIter = objectLayer.findTeam( modelTeam );
        while( teamIter.hasNext() ) {
            team = teamIter.next();
            System.out.println( "CtrlJoinTeam.joinTeam: found team: " + team );
        }
        if( team == null )
            throw new RDException( "Failed to locate a Team called: " + teamName );

        modelStudent = objectLayer.createStudent();
        modelStudent.setId( studentId );
        studentIter = objectLayer.findStudent( modelStudent );
        while( studentIter.hasNext() ) {
            student = studentIter.next();
            System.out.println( "CtrlJoinTeam.joinTeam: found student: " + student );
        }
        if( student == null )
            throw new RDException( "Failed to locate a Student with id: " + studentId );

        FindTeamsCtrl ctrlFindMyTeams = new FindTeamsCtrl( objectLayer );
        List<Team> teams = ctrlFindMyTeams.findMyTeams(modelStudent);
        for (int i = 0; i < teams.size(); i++)
            if (teams.get(i).getParticipatesInLeague().getName().equals(team.getParticipatesInLeague().getName()))
                throw new RDException("Cannot join team because you are already on a team in this league");

    	objectLayer.createStudentMemberOfTeam(student, team);

        return team.getId();
    }
}

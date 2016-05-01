//
// A control class to implement the 'List all teams' use case
//
//


package edu.uga.cs.recdawgs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class FindTeamsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindTeamsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Team> findAllTeams()
            throws RDException
    {
        List<Team> 	teams  = null;
        Iterator<Team> 	teamIter = null;
        Team     	team = null;

        teams = new LinkedList<Team>();
        
        // retrieve all Team objects
        //
        teamIter = objectLayer.findTeam( null );
        while( teamIter.hasNext() ) {
            team = teamIter.next();
            teams.add( team );
        }

        return teams;
    }
    
    public List<Team> findMyTeams(Student modelStudent)
            throws RDException
    {
        List<Team> 	teams  = null;
        Iterator<Team> 	teamIter = null;
        Team     	team = null;

        teams = new LinkedList<Team>();
        
        teamIter = objectLayer.restoreStudentMemberOfTeam(modelStudent);
        while( teamIter.hasNext() ) {
            team = teamIter.next();
            System.out.println( team );
            teams.add( team );
        }

        return teams;
    }

    public Team findTeam(String nameOfTeam)
            throws RDException
    {
        Iterator<Team>  teamIter = null;
        Team        team = null;
        
        Team modelTeam = objectLayer.createTeam();
        modelTeam.setName(nameOfTeam);
        teamIter = objectLayer.findTeam(modelTeam);
        while( teamIter.hasNext() ) {
            team = teamIter.next();
        }

        return team;
    }
}

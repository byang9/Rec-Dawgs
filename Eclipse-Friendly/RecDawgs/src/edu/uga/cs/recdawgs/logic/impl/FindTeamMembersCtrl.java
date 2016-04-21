//
// A control class to implement the 'List team membership' use case
//
//


package edu.uga.cs.recdawgs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class FindTeamMembersCtrl 
{
    private ObjectLayer objectLayer = null;
    
    public FindTeamMembersCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public List<Student> findTeamMembers( String teamName )
            throws RDException
    {
        Team                team = null;
        Team                modelTeam = null;
        Iterator<Team>      teamIter = null;
        List<Student>        students  = null;

        students = new LinkedList<Student>();

        // find the team object
        modelTeam = objectLayer.createTeam();
        modelTeam.setName( teamName );
        teamIter = objectLayer.findTeam( modelTeam );
        while( teamIter.hasNext() ) {
            team = teamIter.next();
        }
        if( team == null )
            throw new RDException( "A team with this name does not exist: " + teamName );

        Membership modelMembership = objectLayer.createMembership();
        modelMembership.setTeam( team );
        Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
        while( membershipIter != null && membershipIter.hasNext() ) {
            Membership m = membershipIter.next();
            students.add( m.getStudent() );
        }

        return students;
    }
  
}

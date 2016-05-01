//
// A control class to implement the 'Create team' use case
//
//

package edu.uga.cs.recdawgs.logic.impl;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class AssignCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public AssignCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public void assignVenueToLeague(League league, SportsVenue sv)
            throws RDException
    { 
        objectLayer.createLeagueSportsVenue(league, sv);
    }
    
    public void assignCaptainToTeam(Student captain, Team team)
            throws RDException
    { 
        objectLayer.createStudentCaptainOfTeam(captain, team);
    }
    
}


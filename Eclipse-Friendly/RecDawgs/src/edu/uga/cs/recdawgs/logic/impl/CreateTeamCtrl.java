//
// A control class to implement the 'Create team' use case
//
//

package edu.uga.cs.recdawgs.logic.impl;


import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class CreateTeamCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateTeamCtrl( ObjectLayer objectModel )
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
}


//
// A control class to implement the 'Create team' use case
//
//

package edu.uga.cs.recdawgs.logic.impl;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class CompleteLeague {
    
    private ObjectLayer objectLayer = null;
    
    public CompleteLeague( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public void completeLeague( String leagueName ) throws RDException { 
        League               league  = null;
        League               modelLeague  = null;
        Iterator<League>     leagueIter  = null;
        Team                 team  = null;
        Iterator<Team>     teamIter  = null;
        List<Team>          teamList = null;
        List<Integer>           scoreList = null;

        // check if the userName already exists
        modelLeague = objectLayer.createLeague();
        modelLeague.setName( leagueName );
        leagueIter = objectLayer.findLeague( modelLeague );
        while( leagueIter.hasNext() ) {
            league = leagueIter.next();
        }
        
        teamIter = objectLayer.findTeam(null);
        while( teamIter.hasNext() ) {
            team = teamIter.next();
            teamList.add(team);
            scoreList.add(0);
        }
        
        // check if the team actually exists, and if so, throw an exception
        if( league == null )
            throw new RDException( "League does not exist" );
        
        //Get all matches in league
        //calculate which team has most wins
        //createTeamWinnerOfLeague(team)

        
    }
}


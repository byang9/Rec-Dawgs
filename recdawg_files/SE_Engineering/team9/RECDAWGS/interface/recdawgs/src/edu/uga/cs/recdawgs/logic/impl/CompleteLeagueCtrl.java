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
        Iterator<Team>       teamIter  = null;
        Match                match = null;
        Iterator<Match>       matchIter  = null;
        List<Team>           teamList = null;
        List<Integer>        scoreList = null;

        // gets the reference to the league
        modelLeague = objectLayer.createLeague();
        modelLeague.setName( leagueName );
        leagueIter = objectLayer.findLeague( modelLeague );
        while( leagueIter.hasNext() ) {
            league = leagueIter.next();
        }
        
        
        // check if the team actually exists, and if so, throw an exception
        if( league == null )
            throw new RDException( "League does not exist" );
        
        //gets all the teams in the league
        teamIter = objectLayer.findTeam(null);
        while( teamIter.hasNext() ) {
            team = teamIter.next();
            if(team.getParticipatesInLeague().getId() == league.getId()){
                teamList.add(team);
                scoreList.add(0);
            }
        }
        
        //gets all matches in a league and calculate wins
        matchIter = objectLayer.findMatch(null);
        while( matchIter.hasNext() ) {
            match = matchIter.next();
            if(match.getHomeTeam().getParticipatesInLeague().getId() == league.getId()){
                int homePoints = match.getHomePoints();
                int awayPoints = match.getAwayPoints();
                if(homePoints == 0 && awayPoints == 0){
                    throw new RDException( "There is a match that hasn't been played" );
                }else if(homePoints>awayPoints){
                    Integer newScore = new Integer(scoreList.get(teamList.indexOf(match.getHomeTeam()))+1);
                    scoreList.set(teamList.indexOf(match.getHomeTeam()), newScore);

                }else if(awayPoints>homePoints){
                    Integer newScore = new Integer(scoreList.get(teamList.indexOf(match.getAwayTeam()))+1);
                    scoreList.set(teamList.indexOf(match.getAwayTeam()), newScore);
                }
            }
        }
        //gets winning team and createTeamWinnerOfLeague(team)
        Integer max = Collections.max(scoreList);
        Team winTeam = teamList.get(scoreList.indexOf(max));
        objectLayer.createTeamWinnerOfLeague(winTeam,league);
        
    }
}


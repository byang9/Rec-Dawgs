package edu.uga.cs.recdawgs.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;


public class FindMatchesCtrl {

private ObjectLayer objectLayer = null;
    
    public FindMatchesCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public List<Match> findAllMatches()
            throws RDException
    {
        Match                modelMatch = null;
        Iterator<Match>      matchIter = null;
        List<Match>			 matches = null;

        matches = new LinkedList<Match>();

        // find the match object
        modelMatch = objectLayer.createMatch();
        matchIter = objectLayer.findMatch( modelMatch );
        while( matchIter.hasNext() ) {
            matches.add(matchIter.next());
        }
        if( matches.isEmpty() )
            throw new RDException( "No matches exist!" );
        
        return matches;
    }
    
    public List<Match> findMyMatch(Student modelStudent){
        List<Match> matches = new LinkedList<Match>();
        Match match = null;
        Team     	team = null;
        Iterator<Match> matchIter = null;
        Iterator<Team> 	teamIter = null;

        try{
        matchIter = objectLayer.findMatch(null);

        while( matchIter.hasNext() ){
            match = matchIter.next();//Gets all the match objects
            teamIter = objectLayer.restoreStudentMemberOfTeam(modelStudent);
            while( teamIter.hasNext() ) {
                team = teamIter.next();//Gets all team objects
                if(team.getId() == match.getHomeTeam().getId() || team.getId() == match.getAwayTeam().getId()){
                    matches.add(match);
                }
            }
        }

        }catch(RDException e){
                System.out.println(e);
        }
        
        return matches;
    }

    /*
    public Match findMatch(Student modelStudent){
        List<Match> matches = new LinkedList<Match>();
        Match match = null;
        Team        team = null;
        Iterator<Match> matchIter = null;
        Iterator<Team>  teamIter = null;

        Match modelMatch = objectLayer.createMatch();

        try{
        matchIter = objectLayer.findMatch(modelMatch);

            while( matchIter.hasNext() ){
                match = matchIter.next();//Gets all the match objects
                teamIter = objectLayer.restoreStudentMemberOfTeam(modelStudent);
                while( teamIter.hasNext() ) {
                    team = teamIter.next();//Gets all team objects
                    if(team.getId() == match.getHomeTeam().getId() || team.getId() == match.getAwayTeam().getId()){
                        matches.add(match);
                    }
                }
            }

        }catch(RDException e){
                System.out.println(e);
        }
        
        return matches;
    }
    */
}

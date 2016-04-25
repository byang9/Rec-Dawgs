package edu.uga.cs.recdawgs.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindLeaguesCtrl {

private ObjectLayer objectLayer = null;
    
    public FindLeaguesCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<League> findAllLeagues()
            throws RDException
    {
        List<League> 	leagues  = null;
        Iterator<League> 	leagueIter = null;
        League     	league = null;

        leagues = new LinkedList<League>();
        
        // retrieve all League objects
        //
        leagueIter = objectLayer.findLeague( null );
        while( leagueIter.hasNext() ) {
            league = leagueIter.next();
            System.out.println( league.getName() );
            leagues.add( league );
        }

        return leagues;
    }
	
}

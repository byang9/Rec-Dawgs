package edu.uga.cs.recdawgs.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.object.ObjectLayer;

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
	
}

//
// A control class to implement the 'List all clubs' use case
//
//


package edu.uga.clubs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.object.ObjectLayer;



public class FindAllClubsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindAllClubsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Club> findAllClubs()
            throws ClubsException
    {
        List<Club> 	clubs  = null;
        Iterator<Club> 	clubIter = null;
        Club     	club = null;

        clubs = new LinkedList<Club>();
        
        // retrieve all Club objects
        //
        clubIter = objectLayer.findClub( null );
        while( clubIter.hasNext() ) {
            club = clubIter.next();
            System.out.println( club );
            clubs.add( club );
        }

        return clubs;
    }
}

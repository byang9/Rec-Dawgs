package edu.uga.cs.recdawgs.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindSportsVenuesCtrl {

	private ObjectLayer objectLayer = null;
    
    public FindSportsVenuesCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public List<SportsVenue> findAllSportsVenues()
            throws RDException
    {
    	SportsVenue                modelSportsVenue = null;
        Iterator<SportsVenue>      svIter = null;
        List<SportsVenue>			 svs = null;

        svs = new LinkedList<SportsVenue>();

        // find the sv object
        modelSportsVenue = objectLayer.createSportsVenue();
        svIter = objectLayer.findSportsVenue( modelSportsVenue );
        while( svIter.hasNext() ) {
            svs.add(svIter.next());
        }
        if( svs.isEmpty() )
            throw new RDException( "No sves exist!" );
        
        return svs;
    }

    public SportsVenue findSportsVenue(String name)
            throws RDException
    {
        SportsVenue                modelSportsVenue = null;
        Iterator<SportsVenue>      svIter = null;
        List<SportsVenue>            svs = null;
        SportsVenue                 venue = null;

        svs = new LinkedList<SportsVenue>();

        // find the sv object
        modelSportsVenue = objectLayer.createSportsVenue();
        modelSportsVenue.setName(name);
        svIter = objectLayer.findSportsVenue( modelSportsVenue );
        while( svIter.hasNext() ) {
            venue = svIter.next();
        }
        if( venue == null )
            throw new RDException( "This sports venue does not exist!" );
        
        return venue;
    }
}

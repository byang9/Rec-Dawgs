package edu.uga.cs.recdawgs.logic.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class FindScoreReportsCtrl {

	private ObjectLayer objectLayer = null;
    
    public FindScoreReportsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public List<ScoreReport> findAllScoreReports()
            throws RDException
    {
        ScoreReport                modelScoreReport = null;
        Iterator<ScoreReport>      reportIter = null;
        List<ScoreReport>			 reports = null;

        reports = new LinkedList<ScoreReport>();

        // find the report object
        modelScoreReport = objectLayer.createScoreReport();
        reportIter = objectLayer.findScoreReport( modelScoreReport );
        while( reportIter.hasNext() ) {
            reports.add(reportIter.next());
        }
        if( reports.isEmpty() )
            throw new RDException( "No reportes exist!" );
        
        return reports;
    }
	
}

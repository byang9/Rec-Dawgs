/**
 * File: ScoreReportIterator.java
 * Author: Jay Springfield
 */

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Date;

import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.ScoreReport;

public class ScoreReportIterator implements Iterator<ScoreReport>{
	private ResultSet		rs = null;
	private ObjectLayer		objectLayer = null;
	private boolean			more = false;

	public ScoreReportIterator( ResultSet rs, ObjectLayer objectLayer ) throws RDException{
		this.rs = rs;
		this.objectLayer = objectLayer;
		try{
			more = rs.next();
		}
		catch( Exception e ){
			throw new RDException( "ScoreReportIterator: Cannot create ScoreReport iterator: root cause: " + e );
		}
	}

	public boolean hasNext(){
		return more;
	}

	public ScoreReport next(){
//		long	matchID;
//		long	homeTeamId;
//		long	awayTeamId;
		long	homePoints;
		long	awayPoints;
		Date	date;
		//long	studentID;

		if( more ){
			try{
//				matchID = rs.getLong( 1 );
//				homeTeamId = rs.getLong( 2 );
//				awayTeamId = rs.getLong( 3 );
				homePoints = rs.getLong( 4 );
				awayPoints = rs.getLong( 5 );
				date = rs.getDate( 6 );
				//studentID = rs.getLong( 7 );

				more = rs.next();
				
				return objectLayer.createScoreReport((int)homePoints, (int)awayPoints, date, objectLayer.createStudent(), objectLayer.createMatch());
			}
			catch( Exception e ){
				throw new NoSuchElementException( "ScoreReportIterator: No next ScoreReport object; root cause: " + e );
			}
		}
		else{
			throw new NoSuchElementException( "ScoreReportIterator: No next ScoreReport object" );
		}
	}
}
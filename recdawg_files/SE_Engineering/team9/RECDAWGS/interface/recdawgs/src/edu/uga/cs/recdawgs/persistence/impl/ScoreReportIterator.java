/**
 * File: ScoreReportIterator.java
 * Author: Jay Springfield
 */

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Date;
import java.util.RDException;

import edu.uga.ScoreReport.object.ObjectLayer;
import edu.uga.ScoreReport.RDException;
import edu.uga.ScoreReport.entity.Matchup;
import edu.uga.ScoreReport.entity.Person;

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
		long	matchId;
		long	homeTeamId;
		long	awayTeamId;
		long	homeTeamPoints;
		long	awayTeamPoints;
		Date	matchDate;
		long	studentId;

		if( more ){
			try{
				matchId = rs.getLong( 1 );
				homeTeamId = rs.getLong( 2 );
				awayTeamId = rs.getLong( 3 );
				homeTeamPoints = rs.getLong( 4 );
				awayTeamPoints = rs.getLong( 5 );
				matchDate = rs.getDate( 6 );
				studentId = rs.getLong( 7 );

				more = rs.next();
			}
			catch( Exception e ){
				throw new NoSuchElementException( "ScoreReportIterator: No next ScoreReport object; root cause: " + e );
			}

			return scoreReport;
		}
		else{
			throw new NoSuchElementException( "ScoreReportIterator: No next ScoreReport object" );
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
}
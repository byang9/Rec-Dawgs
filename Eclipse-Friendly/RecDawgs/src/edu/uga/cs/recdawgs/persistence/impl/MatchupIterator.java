/**
 * File: MatchupIterator.java
 * Author: Jay Springfield
 */

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;

public class MatchupIterator implements Iterator<Match>{
	private ResultSet		rs = null;
	private ObjectLayer		objectLayer = null;
	private boolean			more = false;

	public MatchupIterator( ResultSet rs, ObjectLayer objectLayer ) throws RDException{
		this.rs = rs;
		this.objectLayer = objectLayer;
		try {
			this.more = rs.next();
		}
		catch( Exception e ){
			throw new RDException( "MatchupIterator: Cannot create Matchup iterator; root cause: " + e );
		}
	}

	public boolean hasNext(){
		return more;
	}

	public Match next(){
		long	id;
		long	homeTeamId;
		long	awayTeamId;
		long	homePoints;
		long	awayPoints;
		Date	matchDate;
		boolean	isCompleted;
		String	homeTeamName;
		String	awayTeamName;
		Team homeTeam = null;
		Team awayTeam = null;

		if( more ){
			try{
				id = rs.getLong( 1 );
				homeTeamId = rs.getLong( 2 );
				awayTeamId = rs.getLong( 3 );
				homePoints = rs.getLong( 4 );
				awayPoints = rs.getLong( 5 );
				matchDate = rs.getDate( 6 );
				isCompleted = rs.getBoolean( 7 );
				homeTeamName = rs.getString( 8 );
				awayTeamName = rs.getString( 9 );

				more = rs.next();
			}
			catch( Exception e ){
				throw new NoSuchElementException( "MatchupIterator: No next Matchup object; root cause: " + e );
			}
			
			//ObjectFromID objFromID = new ObjectFromID(conn, objectLayer);
			//homeTeam = objFromID.getTeamFromID(homeTeamId);
			//awayTeam = objFromID.getTeamFromID(homeTeamId);

			Match match = null;
			try {
				match = objectLayer.createMatch((int)homePoints, (int)awayPoints, matchDate, isCompleted, homeTeam, awayTeam);
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return match;
		}
		else{
			throw new NoSuchElementException( "MatchupIterator: No next Matchup object" );
		}
	}

	public void remove(){
		throw new UnsupportedOperationException();
	}
}
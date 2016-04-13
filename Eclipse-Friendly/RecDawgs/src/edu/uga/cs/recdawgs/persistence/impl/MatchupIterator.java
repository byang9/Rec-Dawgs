/**
 * File: MatchupIterator.java
 * Author: Jay Springfield
 */

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Date;
import java.util.RDException;

import edu.uga.Matchup.object.ObjectLayer;
import edu.uga.Matchup.RDException;
import edu.uga.Matchup.entity.Matchup;
import edu.uga.Matchup.entity.Team;

public class MatchupIterator implements Iterator<Matchup>{
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

	public Matchup next(){
		long	id;
		long	homeTeamId;
		long	awayTeamId;
		long	homePoints;
		long	awayPoints;
		Date	matchDate;
		boolean	isCompleted;
		String	homeTeamName;
		String	awayTeamName;
		long	homeTeamId;
		long	awayTeamId;
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
				awayTeamname = rs.getString( 9 );
				homeTeamId = rs.getLong( 10 );
				awayTeamId = rs.getLong( 11 );

				more = rs.next();
			}
			catch( Exception e ){
				throw new NoSuchElementException( "MatchupIterator: No next Matchup object; root cause: " + e );
			}

			homeTeam = objectLayer.createTeam( name );
			awayTeam = objectLayer.createTeam( name );

			return matchup;
		}
		else{
			throw new NoSuchElementException( "MatchupIterator: No next Matchup object" );
		}
	}

	public void remove(){
		throw new UnsupportedOperationException();
	}
}
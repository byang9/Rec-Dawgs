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
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Student;

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

				more = rs.next();
			}
			catch( Exception e ){
				throw new NoSuchElementException( "MatchupIterator: No next Matchup object; root cause: " + e );
			}

			// "select m.id, m.homeTeamId, m.awayTeamId, m.homePoints, m.awayPoints, m.matchDate, m.isCompleted, " +
			// "ht.id, at.id, 10 ht.name, ht.leagueid, ht.captainid, at.name, at.leagueid, at.captainid, l.id, l.name, l.winnerID, l.isIndoor, l.minTeams, " +
			// "l.maxTeams, l.minTeamMembers, l.maxTeamMembers, l.matchRules, " +
			// "l.leagueRules, 26 hp.id, hp.firstname, hp.lastname, hp.username, hp.password, "
			// + "hp.email, hp.studentID, hp.major, 34 hp.address, ap.id, ap.firstname, ap.lastname, ap.username, ap.password, "
			// + "ap.email, ap.studentID, ap.major, ap.address" +
			// "from matchup m, team ht, team at, league l, person hp, person ap where m.homeTeamId = ht.id and m.awayTeamId = at.id" +
			// " and ht.captainid=hp.id and at.captainid=ap.id and ht.leagueid=l.id and at.leagueid=l.id";

			Match match = null;
			try {
				League league = objectLayer.createLeague(rs.getString(17), rs.getString(25), rs.getString(24), rs.getBoolean(19), (int)rs.getLong(20), (int)rs.getLong(21), (int)rs.getLong(22), (int)rs.getLong(23));
				league.setId(rs.getLong(16));
				Student homeCaptain = objectLayer.createStudent(rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getString(34));
				homeCaptain.setId(rs.getLong(26));
				Student awayCaptain = objectLayer.createStudent(rs.getString(36), rs.getString(37), rs.getString(38), rs.getString(39), rs.getString(40), rs.getString(41), rs.getString(42), rs.getString(43));
				awayCaptain.setId(rs.getLong(35));
				homeTeam = objectLayer.createTeam(rs.getString(10));
				homeTeam.setId(rs.getLong(8));
				homeTeam.setParticipatesInLeague(league);
				homeTeam.setCaptain(homeCaptain);
				awayTeam = objectLayer.createTeam(rs.getString(13));
				awayTeam.setId(rs.getLong(9));
				awayTeam.setParticipatesInLeague(league);
				awayTeam.setCaptain(awayCaptain);
				match = objectLayer.createMatch((int)homePoints, (int)awayPoints, matchDate, isCompleted, homeTeam, awayTeam);
				System.out.println("Match received: " + match);
			} catch (RDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
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
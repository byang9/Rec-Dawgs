/**
 * File: MatchupManager.java
 * Author: Jay Springfield
 */

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class MatchupManager{
	private ObjectLayer objectLayer = null;
	private Connection conn = null;

	public MatchupManager( Connection conn, ObjectLayer objectLayer ){
		this.conn = conn;
		this.objectLayer = objectLayer;
	}

	public void save( Match matchup ) throws RDException{
		String insertMatchupSql = "insert into matchup ( homeTeamId, awayTeamId, homePoints, awayPoints, matchDate, isCompleted ) values ( ?, ?, ?, ?, ?, ? )";
		String updateMatchupSql = "update matchup set homeTeamId = ?, awayTeamId = ?, homePoints = ?, awayPoints = ?, matchDate = ?, isCompleted = ? where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		long matchupId;

		try{
			if( !matchup.isPersistent() )
				stmt = (PreparedStatement) conn.prepareStatement(insertMatchupSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateMatchupSql);
			
			if (matchup.isPersistent())
				stmt.setLong(7, matchup.getId());

			if( matchup.getHomeTeam() != null )
				stmt.setLong(1, matchup.getHomeTeam().getId());
			else
				throw new RDException( "MatchupManager.save: can't save a Matchup: home team undefined" );
			
			if( matchup.getAwayTeam() != null )
				stmt.setLong(2, matchup.getAwayTeam().getId());
			else
				throw new RDException( "MatchupManager.save: can't save a Matchup: away team undefined" );

			stmt.setLong(3, matchup.getHomePoints());
			stmt.setLong(4, matchup.getAwayPoints());
			stmt.setDate(5, new java.sql.Date(matchup.getDate().getTime()));
			stmt.setBoolean(6, matchup.getIsCompleted());

			inscnt = stmt.executeUpdate();

			if( !matchup.isPersistent() ) {
				if(inscnt >= 1 ) {
					String sql = "select last_insert_id()";
					if( stmt.execute( sql ) ) {

						ResultSet r = stmt.getResultSet();

						while( r.next() ) {
								matchupId = r.getLong( 1 );
								if( matchupId > 0 )
									matchup.setId( matchupId );
						}
					}
				}
				else
					throw new RDException( "MatchupManager.save: failed to save a Matchup" );
			}
			else {
				if( inscnt < 1 )
					throw new RDException( "MatchupManager.save: failed to save a Matchup" );
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
			throw new RDException( "MatchupManager.save: failed to save a Matchup: " + e );
		}
	}

	public Iterator<Match> restore(Match matchup)
		throws RDException
	{
		//setString		
		String			selectMatchupSql = "select m.id, m.homeTeamId, m.awayTeamId, m.homeTeamId, m.homePoints, m.awayPoints, m.matchDate, m.isCompleted, " +
											"ht.id, at.id, ht.name, ht.leagueid, ht.captainid, at.name, at.leagueid, at.captainid, l.id, l.name, l.winnerID, l.isIndoor, l.minTeams, " +
    										"l.maxTeams, l.minTeamMembers, l.maxTeamMembers, l.matchRules, " +
    										"l.leagueRules, hp.id, hp.firstname, hp.lastname, hp.username, hp.password, "
    										+ "hp.email, hp.studentID, hp.major, hp.address, ap.id, ap.firstname, ap.lastname, ha.username, ap.password, "
    										+ "ap.email, ap.studentID, ap.major, ap.address" +
											"from matchup m, team ht, team at, league l, person hp, person ap where m.homeTeamId = ht.id and m.awayTeamId = at.id" +
											" and ht.captainid=hp.id and at.captainid=ap.id and ht.leagueid=l.id and at.leagueid=l.id";
		Statement		stmt = null;
		StringBuffer 	query = new StringBuffer( 100 );
		StringBuffer	condition = new StringBuffer( 100 );

		condition.setLength( 0 );

		// form the query based on the given Matchup object instance
		query.append( selectMatchupSql );

		if( matchup != null ) {
			if( matchup.getId() >= 0 ) // id is unique, so it is sufficiient to get a person
				query.append( " and m.id = " + matchup.getId() );
			else if( matchup.getHomeTeam() != null ) // home team id is unique, so it is sufficient to get a home team
				query.append( " and m.homeTeamId = '" + matchup.getHomeTeam().getId() + "'" );
			else if( matchup.getAwayTeam() != null ) // away team id is unique, so it is sufficient to get an away team
				query.append( " and m.awayTeamId = '" + matchup.getAwayTeam().getId() + "'" );
			else {

				condition.append( " and m.homePoints = '" + matchup.getHomePoints() + "'" );
				condition.append( " and m.awayPoints = '" + matchup.getAwayPoints() + "'");
				if( condition.length() > 0 ) {
					condition.append( " and" ); 
					condition.append( " m.date = '" + matchup.getDate() + "'");
				}

			condition.append( " and m.isCompleted = '" + matchup.getIsCompleted() + "'" );
			}	
		}

		try {

			stmt = conn.createStatement();

			if( stmt.execute( query.toString() ) ) {
				ResultSet r = stmt.getResultSet();
				return new MatchupIterator(r, objectLayer);
			}
		}
		catch( Exception e ) {
			throw new RDException( "MatchupManager.restore: Could not restore persistent Matchup object; Root cause: " + e );
		}

		throw new RDException( "MatchupManager.restore: Could not restore persistent Matchup object" );
	}

	public void delete(Match matchup)
		throws RDException
	{
		String				deleteMatchSql = "delete from matchup where id = ?";
		PreparedStatement	stmt = null;
		int					inscnt;

		if( !matchup.isPersistent() ) // is the Matchup object persistent? If not, nothing to actually delete
			return;

		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteMatchSql);
			stmt.setLong( 1, matchup.getId() );
			inscnt = stmt.executeUpdate();
			if( inscnt == 1 ) {
				return;
			}
			else
				throw new RDException( "MatchupManager.delete: failed to delete a Matchup" );
		}
		catch( SQLException e ) {
			e.printStackTrace();
			throw new RDException( "MatchupManager.delete: failed to delete a Matchup: " + e );
		}
	}
}
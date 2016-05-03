package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class TeamMatchManager {
	
	private ObjectLayer objectLayer = null;
	private Connection conn = null;

	public TeamMatchManager( Connection conn, ObjectLayer objectLayer ){
		this.conn = conn;
		this.objectLayer = objectLayer;
	}
	
	/* Storage */
	
	public void saveHomeTeam( Team team, Match match ) throws RDException{
		String insertMatchupSql = "insert into matchup ( homeTeamId, awayTeamId, homePoints, awayPoints, matchDate, isCompleted ) values ( ?, ?, ?, ?, ?, ? )";
		String updateMatchupSql = "update matchup set homeTeamId = ?, awayTeamId = ?, homePoints = ?, awayPoints = ?, matchDate = ?, isCompleted = ? where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		long matchupId;

		try{
			if( !match.isPersistent() )
				stmt = (PreparedStatement) conn.prepareStatement(insertMatchupSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateMatchupSql);
			
			if (match.isPersistent())
				stmt.setLong(7, match.getId());

			if( team != null )
				stmt.setLong(1, team.getId());
			else
				throw new RDException( "MatchupManager.save: can't save a Matchup: home team undefined" );
			
			if( match.getAwayTeam() != null )
				stmt.setLong(2, match.getAwayTeam().getId());
			else
				throw new RDException( "MatchupManager.save: can't save a Matchup: away team undefined" );

			stmt.setLong(3, match.getHomePoints());
			stmt.setLong(4, match.getAwayPoints());
			stmt.setDate(5, new java.sql.Date(match.getDate().getTime()));
			stmt.setBoolean(6, match.getIsCompleted());

			inscnt = stmt.executeUpdate();

			if( !match.isPersistent() ) {
				if(inscnt >= 1 ) {
					String sql = "select last_insert_id()";
					if( stmt.execute( sql ) ) {

						ResultSet r = stmt.getResultSet();

						while( r.next() ) {
								matchupId = r.getLong( 1 );
								if( matchupId > 0 )
									match.setId( matchupId );
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
	
	public void saveAwayTeam( Team team, Match match ) throws RDException{
		String insertMatchupSql = "insert into matchup ( homeTeamId, awayTeamId, homePoints, awayPoints, matchDate, isCompleted ) values ( ?, ?, ?, ?, ?, ? )";
		String updateMatchupSql = "update matchup set homeTeamId = ?, awayTeamId = ?, homePoints = ?, awayPoints = ?, matchDate = ?, isCompleted = ?";
		PreparedStatement stmt = null;
		int inscnt;
		long matchupId;

		try{
			if( !match.isPersistent() )
				stmt = (PreparedStatement) conn.prepareStatement(insertMatchupSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateMatchupSql);
			
			stmt.setLong(1, match.getId());

			if( team != null )
				stmt.setLong(3, team.getId());
			else
				throw new RDException( "MatchupManager.save: can't save a Matchup: home team undefined" );
			
			if( match.getHomeTeam() != null )
				stmt.setLong(2, match.getHomeTeam().getId());
			else
				throw new RDException( "MatchupManager.save: can't save a Matchup: away team undefined" );

			stmt.setLong(4, match.getHomePoints());
			stmt.setLong(5, match.getAwayPoints());
			stmt.setBoolean(7, match.getIsCompleted());

			inscnt = stmt.executeUpdate();

			if( !match.isPersistent() ) {
				if(inscnt >= 1 ) {
					String sql = "select last_insert_id()";
					if( stmt.execute( sql ) ) {

						ResultSet r = stmt.getResultSet();

						while( r.next() ) {
								matchupId = r.getLong( 1 );
								if( matchupId > 0 )
									match.setId( matchupId );
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

	/* Restore */
	
	
	public Team restoreHomeTeam( Match matchup )
			throws RDException
		{
			String		selectTeamSql = "select ht.id, ht.name, ht.leagueid, ht.established, ht.captainid from team ht, matchup m where ht.id = m.homeTeamId";
			Statement	stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );

			condition.setLength( 0 );

			// form the query based on the given Team object instance
			query.append(selectTeamSql);

			if( matchup != null ) {
				if( matchup.getId() >= 0 ) // id is unique, so it is sufficiient to get a person
					query.append( " and m.id = " + matchup.getId() );
				else if( matchup.getHomeTeam() != null ) // home team id is unique, so it is sufficient to get a home team
					query.append( " and m.homeTeamId = '" + matchup.getHomeTeam().getId() + "'" );
				else {
					
					condition.append( " and m.homePoints = '" + matchup.getHomePoints() + "'" );

					if( matchup.getDate() != null ) { 
						condition.append( " and m.matchDate = '" + matchup.getDate() + "'");
					}

					if( condition.length() > 0 ) {
						query.append( condition );
					}

					condition.append( " and m.isCompleted = '" + matchup.getIsCompleted() + "'" );
				}
			}

			try {

				stmt = conn.createStatement();

				// retrieve the persistent Team object
				//
				if( stmt.execute( query.toString() ) ) {
					ResultSet r = stmt.getResultSet();
					Iterator<Team> teamIter = new TeamIterator(r, objectLayer);
					if ( teamIter != null && teamIter.hasNext() ) {
						return teamIter.next();
					}
					else
						return null;
				}
			}
			catch( Exception e ) {
				throw new RDException( "MatchupManager.restoreHomeTeam: Could not restore persistent Team object; Root cause: " + e );
			}

			// if we reach this point, it's an error
			throw new RDException( "MatchupManager.restoreHomeTeam: Could not restore persistent Team object" );
		}

		public Team restoreAwayTeam( Match matchup )
			throws RDException
		{
			String		selectTeamSql = "select at.id, at.name, at.leagueid, at.established, at.captainid from team at, matchup m where at.id = m.awayTeamId";
			Statement	stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );

			condition.setLength( 0 );

			// form the query based on the given Team object instance
			query.append( selectTeamSql );

			if( matchup != null ) {
				if( matchup.getId() >= 0 ) // id is unique, so it is sufficiient to get a team
					query.append( " and m.id = " + matchup.getId() );
				else if( matchup.getHomeTeam() != null ) // away team id is unique, so it is sufficient to get an away team
					query.append( " and m.awayTeamId = '" + matchup.getAwayTeam().getId() + "'" );
				else {

					condition.append( " and m.awayPoints = '" + matchup.getAwayPoints() + "'" );

					if( matchup.getDate() != null ) { 
						condition.append( " and m.matchDate = '" + matchup.getDate() + "'");
					}

					if( condition.length() > 0 ) {
						query.append( condition );
					}

					condition.append( " and m.isCompleted = '" + matchup.getIsCompleted() + "'" );
				}
			}

			try {

				stmt = conn.createStatement();

				// retrieve the persistent Team object
				//
				if( stmt.execute( query.toString() ) ) {
					ResultSet r = stmt.getResultSet();
					Iterator<Team> teamIter = new TeamIterator(r, objectLayer);
					if ( teamIter != null && teamIter.hasNext() ) {
						return teamIter.next();
					}
					else
						return null;
				}
			}
			catch( Exception e ) {
				throw new RDException( "MatchupManager.restoreAwayTeam: Could not restore persistent Team object; Root cause: " + e );
			}

			// if we reach this point, it's an error
			throw new RDException( "MatchupManager.restoreAwayTeam: Could not restore persistent Team object" );
		}
		
		public Iterator<Match> restoreAwayTeamMatch(Team team) throws RDException {
			//String
			String			selectTeamSql = "select m.id, m.homeTeamId, m.awayTeamId, m.homePoints, m.awayPoints, m.matchDate, m.isCompleted, " +
											"ht.id, at.id, ht.name, ht.leagueid, ht.captainid, at.name, at.leagueid, at.captainid, l.id, l.name, l.winnerID, l.isIndoor, l.minTeams, " +
    										"l.maxTeams, l.minTeamMembers, l.maxTeamMembers, l.matchRules, " +
    										"l.leagueRules, hp.id, hp.firstname, hp.lastname, hp.username, hp.password, "
    										+ "hp.email, hp.studentID, hp.major, hp.address, ap.id, ap.firstname, ap.lastname, ap.username, ap.password, "
    										+ "ap.email, ap.studentID, ap.major, ap.address" +
											"from matchup m, team ht, team at, league l, person hp, person ap where m.homeTeamId = ht.id and m.awayTeamId = at.id" +
											" and ht.captainid=hp.id and at.captainid=ap.id and ht.leagueid=l.id and at.leagueid=l.id and at.id = " + team.getId();
			Statement		stmt = null;
			StringBuffer	query = new StringBuffer( 100 );
			StringBuffer	condition = new StringBuffer( 100 );
					
			condition.setLength(0);
					
			query.append(selectTeamSql);
					
			try{
				stmt = conn.createStatement();
						
				// retrieve the persistent Person object
				//
				if( stmt.execute( query.toString() ) ) {
					ResultSet r = stmt.getResultSet();
					return new MatchupIterator( r, objectLayer );
				}
			}
			catch( Exception e ){
				throw new RDException( "MatchupManager.restoreHomeTeamMatch: Could not restore persistent Team object; Root cause: " + e );
			}
					
			// if we read this point it's an error
			throw new RDException( "MatchupManager.restoreHomeTeamMatch: Could not restore persistent Team object" );
		}
		
		public Iterator<Match> restoreHomeTeamMatch(Team team) throws RDException {
			//String
			String			selectTeamSql = "select m.id, m.homeTeamId, m.awayTeamId, m.homePoints, m.awayPoints, m.matchDate, m.isCompleted, " +
											"ht.id, at.id, ht.name, ht.leagueid, ht.captainid, at.name, at.leagueid, at.captainid, l.id, l.name, l.winnerID, l.isIndoor, l.minTeams, " +
    										"l.maxTeams, l.minTeamMembers, l.maxTeamMembers, l.matchRules, " +
    										"l.leagueRules, hp.id, hp.firstname, hp.lastname, hp.username, hp.password, "
    										+ "hp.email, hp.studentID, hp.major, hp.address, ap.id, ap.firstname, ap.lastname, ap.username, ap.password, "
    										+ "ap.email, ap.studentID, ap.major, ap.address" +
											"from matchup m, team ht, team at, league l, person hp, person ap where m.homeTeamId = ht.id and m.awayTeamId = at.id" +
											" and ht.captainid=hp.id and at.captainid=ap.id and ht.leagueid=l.id and at.leagueid=l.id and ht.id = " + team.getId();

			Statement		stmt = null;
			StringBuffer	query = new StringBuffer( 100 );
			StringBuffer	condition = new StringBuffer( 100 );
			
			condition.setLength(0);
			
			query.append(selectTeamSql);
			
			try{
				stmt = conn.createStatement();
				
				// retrieve the persistent Person object
				//
				if( stmt.execute( query.toString() ) ) {
					ResultSet r = stmt.getResultSet();
					return new MatchupIterator( r, objectLayer );
				}
			}
			catch( Exception e ){
				throw new RDException( "MatchupManager.restoreHomeTeamMatch: Could not restore persistent Team object; Root cause: " + e );
			}
			
			// if we readh this point it's an error
			throw new RDException( "MatchupManager.restoreHomeTeamMatch: Could not restore persistent Team object" );
		}
		
		/* Delete */
		
		public void deleteHomeTeam(Team team, Match match) throws RDException{
	        String               deleteMembershipSql = "update team set homeTeamId=null where id = " + team.getId();              
	        PreparedStatement    stmt = null;
	        int                  inscnt;
	             
	        if( !team.isPersistent() || !match.isPersistent() ) // is the Membership object persistent?  If not, nothing to actually delete
	            return;
	        
	        try {
	            stmt = (PreparedStatement) conn.prepareStatement( deleteMembershipSql );
	            inscnt = stmt.executeUpdate();
	            
	            if( inscnt == 1 ) {
	                return;
	            }
	            else
	                throw new RDException( "MembershipManager.delete: failed to delete a Membership" );
	        }
	        catch( SQLException e ) {
	            e.printStackTrace();
	            throw new RDException( "MembershipManager.delete: failed to delete a Membership: " + e );               }
	    }
		
		public void deleteAwayTeam(Team team, Match match) throws RDException{
	        String               deleteMembershipSql = "update team set awayTeamId=null where id = " + team.getId();              
	        PreparedStatement    stmt = null;
	        int                  inscnt;
	             
	        if( !team.isPersistent() || !match.isPersistent() ) // is the Membership object persistent?  If not, nothing to actually delete
	            return;
	        
	        try {
	            stmt = (PreparedStatement) conn.prepareStatement( deleteMembershipSql );
	            inscnt = stmt.executeUpdate();
	            
	            if( inscnt == 1 ) {
	                return;
	            }
	            else
	                throw new RDException( "MembershipManager.delete: failed to delete a Membership" );
	        }
	        catch( SQLException e ) {
	            e.printStackTrace();
	            throw new RDException( "MembershipManager.delete: failed to delete a Membership: " + e );               }
	    }
	
}

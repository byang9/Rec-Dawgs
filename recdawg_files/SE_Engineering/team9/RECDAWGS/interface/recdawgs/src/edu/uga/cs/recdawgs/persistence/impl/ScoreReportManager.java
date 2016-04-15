/**
 * File: ScoreReportManager.java
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
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class ScoreReportManager {
	
	private ObjectLayer objectLayer = null;
	private Connection conn = null;

	public ScoreReportManager( Connection conn, ObjectLayer objectLayer ){
		this.conn = conn;
		this.objectLayer = objectLayer;
	}

	public void save(ScoreReport scoreReport) throws RDException{
		String insertScoreReportSql = "insert into score report ( matchId, homeTeamId, awayTeamId, homePoints, awayPoints, matchDate, studentId ) values ( ?, ?, ?, ?, ?, ?, ? )";
		String updateScoreReportSql = "update score report set matchId = ?, homeTeamId = ?, awayTeamId = ?, homePoints = ?, awayPoints = ?, matchDate = ?, studentId = ?";
		PreparedStatement stmt = null;
		int inscnt;
		long scoreReportId;

		try{
			if( !scoreReport.isPersistent() )
				stmt = (PreparedStatement) conn.prepareStatement(insertScoreReportSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateScoreReportSql);
			if( scoreReport.getMatch().getId() >= 0 )
				stmt = (PreparedStatement) conn.prepareStatement(updateScoreReportSql);
			if( scoreReport.getMatch() != null )
				stmt.setLong(1, scoreReport.getMatch().getId());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report ID undefined" );

			if( scoreReport.getMatch().getHomeTeam().getId() >= 0 )
				stmt.setLong(2, scoreReport.getMatch().getHomeTeam().getId() );
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report home team ID undefined" );

			if( scoreReport.getMatch().getAwayTeam().getId() >= 0 )
				stmt.setLong(3, scoreReport.getMatch().getAwayTeam().getId() );
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report away team ID undefined" );

			if( scoreReport.getHomePoint() >= 0 )
				stmt.setLong(4, scoreReport.getHomePoint() );
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report home points undefinded" );

			if( scoreReport.getAwayPoints() >= 0 )
				stmt.setLong(5, scoreReport.getAwayPoints());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report away points undefined" );

			if( scoreReport.getDate() != null )
				stmt.setDate(6, new java.sql.Date(scoreReport.getDate().getTime()));
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report date undefined" );

			if( scoreReport.getStudent().getId() >= 0 )
			if( scoreReport.getMatch().getHomeTeam() != null )
				stmt.setLong(2, scoreReport.getMatch().getHomeTeam().getId());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report home team ID undefined" );

			if( scoreReport.getMatch().getAwayTeam() != null )
				stmt.setLong(3, scoreReport.getMatch().getAwayTeam().getId());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report away team ID undefined" );

			stmt.setLong(4, scoreReport.getHomePoint());
			stmt.setLong(5, scoreReport.getAwayPoints());
			
			if( scoreReport.getDate() != null )
				stmt.setDate(6, new java.sql.Date(scoreReport.getDate().getTime()));
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report date undefined" );

			if( scoreReport.getStudent() != null )
				stmt.setLong(7, scoreReport.getStudent().getId());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report student ID undefined" );

			inscnt = stmt.executeUpdate();

			if( !scoreReport.isPersistent() ){
				if(inscnt >= 1 ){
					String sql = "select last_insert_id()";
					if( stmt.execute( sql ) ){
						ResultSet r = stmt.getResultSet();

						while( r.next() ){
							scoreReportId = r.getLong( 1 );
							if( scoreReportId > 0 )
								scoreReport.setId( scoreReportId );
						}
					}
				}
				else
					throw new RDException( "ScoreReportManager.save: failed to save a ScoreReport" );
			}
			else{
				if( inscnt < 1 )
					throw new RDException( "ScoreReportManager.save: failed to save a ScoreReport" );
			}
		}
		catch( SQLException e ){
			e.printStackTrace();
			throw new RDException( "ScoreReportManager.save: failed to save a ScoreReport: " + e );
		}
	}

	public Iterator<ScoreReport> restore(ScoreReport scoreReport) throws RDException{

		//setString
		String			selectScoreReportSql = "select s.matchId, s.homeTeamId, s.awayTeamId, s.homePoints, s.awayPoints, s.matchDate, s.studentId,  "+
												"m.id, m.homeTeamId, m.awayTeamId, m.homePoints, m.awayPoints, m.matchDate, m.isCompleted, " +
												"p.id, p.firstName, p.lastName, p.username, p.password, p.email, p.isStudent, p.studentId, p.address, p.phone "+
												"from scorereport s, matchup m, person p";
		Statement		stmt = null;
		StringBuffer	query = new StringBuffer( 100 );
		StringBuffer	condition = new StringBuffer( 100 );

		condition.setLength( 0 );

		// form the query based on the given ScoreReport object instance
		query.append( selectScoreReportSql );

		if( scoreReport != null ){
			if( scoreReport.getMatch().getId() >= 0 )
				query.append( " and s.id = " + scoreReport.getMatch().getId() );
			else if( scoreReport.getMatch().getHomeTeam().getId() >= 0 )
				query.append( " and s.homeTeamId = '" + scoreReport.getMatch().getHomeTeam().getId() + "'" );
			else if( scoreReport.getMatch().getAwayTeam().getId() >= 0 )
				query.append( " and s.awayTeamId = '" + scoreReport.getMatch().getAwayTeam().getId() + "'" );
			else if( scoreReport.getHomePoint() >= 0 )
				query.append( " and s.homePoints = '" + scoreReport.getHomePoint() + "'" );
			else if( scoreReport.getAwayPoints() >= 0 )
				query.append( " and s.awayPoints = '" + scoreReport.getAwayPoints() + "'" );
			else if(scoreReport.getDate() != null )
				query.append( " and s.matchDate = '" + scoreReport.getDate() + "'" );
			else if( scoreReport.getMatch().getHomeTeam() != null )
				query.append( " and s.homeTeamId = '" + scoreReport.getMatch().getHomeTeam().getId() + "'" );
			else if( scoreReport.getMatch().getAwayTeam() != null )
				query.append( " and s.awayTeamId = '" + scoreReport.getMatch().getAwayTeam().getId() + "'" );
			query.append( " and s.homePoints = '" + scoreReport.getHomePoint() + "'" );
			query.append( " and s.awayPoints = '" + scoreReport.getAwayPoints() + "'" );
			if(scoreReport.getDate() != null ){
					if( query.length() > 0 )
						query.append( " and" );
					query.append( " s.date = '" + scoreReport.getDate() + "'");
			}
			else if( scoreReport.getStudent() != null )
				query.append( " and s.studentId = '" + scoreReport.getStudent().getId() + "'" );
		}

		try {

			stmt = conn.createStatement();

			if( stmt.execute( query.toString() ) ) {
				ResultSet r = stmt.getResultSet();
				return new ScoreReportIterator( r, objectLayer );
			}
		}
		catch( Exception e ){
			throw new RDException( "ScoreReportManager.restore: Could not restore persistent ScoreReport object; Root cause: " + e );
		}

		throw new RDException( "ScoreReportManager.restore: Could not restore persistent ScoreReport object" );
	}

	public void delete(ScoreReport scoreReport) throws RDException{
		String				deleteScoreReportSql = "delete from club where id = ?";
		PreparedStatement	stmt = null;
		int					inscnt;

		try{
			stmt = (PreparedStatement) conn.prepareStatement( deleteScoreReportSql );
			stmt.setLong( 1, scoreReport.getMatch().getId() );
			inscnt = stmt.executeUpdate();
			if( inscnt == 1 ){
				return;
			}
			else
				throw new RDException( "ScoreReportManager.delete: failed to delete a ScoreReport" );
		}
		catch( SQLException e ){
			e.printStackTrace();
			throw new RDException( "ScoreReportManager.delete: failed to delete a ScoreReport: " + e );
		}
	}
}
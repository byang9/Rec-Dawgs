/**
 * File: ScoreReportManager.java
 * Author: Jay Springfield
 */

package edu.uga.cs.persistence.impl;

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

public class ScoreReportManager{
	private ObjectLayer objectLayer = null;
	private Connection conn = null;

	public ScoreReportManager( Connection conn, ObjectLayer objectLayer ){
		this.conn = conn;
		this.objectLayer;
	}

	public void save( ScoreReport scoreReport ) throws RDException{
		String insertScoreReportSql = "insert into score report ( matchId, homeTeamId, awayTeamId, homePoints, awayPoints, matchDate, studentId ) values ( ?, ?, ?, ?, ?, ?, ? )";
		String updateScoreReportSql = "update score report set matchId = ?, homeTeamId = ?, awayTeamId = ?, homePoints = ?, awayPoints = ?, matchDate = ?, studentId = ?";
		PreparedStatement stmt = null;
		int inscnt;
		long scoreReportId;

		try{
			if( !scoreReport.isPersistent() )
				stmt = (PreparedStatement) conn.prepareStatement(insertScoreReportSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateMatchupSql);
			if( scoreReport.getMatch().getId() != null )
				stmt.setLong(1, scoreReport.getMatch().getId());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report ID undefined" );

			if( scoreReport.getMatch().getHomeTeam().getId() != null )
				stmt.setLong(2, scoreReport.getMatch().getHomeTeam().getId() != null );
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report home team ID undefined" );

			if( scoreReport.getMatch().getAwayTeam().getId() != null )
				stmt.setLong(3, scoreReport.getMatch().getAwayTeam().getId() != null );
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report away team ID undefined" );

			if( scoreReport.getHomePoints() != null )
				stmt.setLong(4, scoreReport.getHomePoints() != null );
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report home points undefinded" );

			if( scoreReport.getAwayPoints() != null )
				stmt.setLong(5, scoreReport.getAwayPoints());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report away points undefined" );

			if( scoreReport.getDate() != null )
				stmt.setString(6, scoreReport.getDate());
			else
				throw new RDException( "ScoreReportManager.save: can't save a ScoreReport: score report date undefined" );

			if( scoreReport.getStudent().getId() != null )
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
		String			selectScoreReportSql = "select "
	}
}
package edu.uga.cs.recdawgs.entity.impl;

import java.util.Date;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;

public class MatchImpl extends Persistent implements Match {
	
	private int homePoints;
	private int awayPoints;
	private Date date;
	private boolean isCompleted;
	private Team homeTeam;
	private Team awayTeam;
	private SportsVenue sv;
	private Round round;
	
	public MatchImpl(int homePoints, int awayPoints, Date date, boolean isCompleted, Team homeTeam, Team awayTeam) {
		this.homePoints = homePoints;
		this.awayPoints = awayPoints;
		this.date = date;
		this.isCompleted = isCompleted;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public MatchImpl() {
		this.homePoints = 0;
		this.awayPoints = 0;
		this.date = null;
		this.isCompleted = false;
		this.homeTeam = null;
		this.awayTeam = null;
	}

	@Override
	public int getHomePoints() {
		return homePoints;
	}

	@Override
	public void setHomePoints(int homePoints) throws RDException {
		this.homePoints = homePoints;
	}

	@Override
	public int getAwayPoints() {
		return awayPoints;
	}

	@Override
	public void setAwayPoints(int awayPoints) throws RDException {
		this.awayPoints = awayPoints;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) throws RDException {
		this.date = date;
	}

	@Override
	public boolean getIsCompleted() {
		return isCompleted;
	}

	@Override
	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public Team getHomeTeam() {
		return homeTeam;
	}

	@Override
	public void setHomeTeam(Team homeTeam) throws RDException {
		this.homeTeam = homeTeam;
	}

	@Override
	public Team getAwayTeam() {
		return awayTeam;
	}

	@Override
	public void setAwayTeam(Team awayTeam) throws RDException {
		this.awayTeam = awayTeam;
	}

	@Override
	public SportsVenue getSportsVenue() {
		return sv;
	}

	@Override
	public void setSportsVenue(SportsVenue sportsVenue) {
		this.sv = sportsVenue;
	}

	@Override
	public Round getRound() {
		return round;
	}

	@Override
	public void setRound(Round round) {
		this.round = round;
	}

}

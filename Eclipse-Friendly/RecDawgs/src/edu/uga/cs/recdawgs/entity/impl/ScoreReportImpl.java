package edu.uga.cs.recdawgs.entity.impl;

import java.util.Date;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;

public class ScoreReportImpl extends Persistent implements ScoreReport {
	
	private int homePoint;
	private int awayPoints;
	private Date date;
	private Match match;
	private Student student;
	
	public ScoreReportImpl(int homePoints, int awayPoints, Date date, Student student, Match match) {
		this.homePoint = homePoints;
		this.awayPoints = awayPoints;
		this.date = date;
		this.match = match;
		this.student = student;
	}
	
	public ScoreReportImpl() {
		this.homePoint = 0;
		this.awayPoints = 0;
		this.date = null;
		this.match = null;
		this.student = null;
	}

	@Override
	public int getHomePoint() {
		return homePoint;
	}

	@Override
	public void setHomePoint(int homePoints) throws RDException {
		this.homePoint = homePoints;
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
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Match getMatch() {
		return match;
	}

	@Override
	public void setMatch(Match match) throws RDException {
		this.match = match;
	}

	@Override
	public Student getStudent() {
		return student;
	}

	@Override
	public void setStudent(Student student) throws RDException {
		this.student = student;
	}

}

package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.persistence.impl.Persistent;


public class TeamImpl extends Persistent implements Team{

	private String name;
	private Student captain;
	private League league;
	private League winnerOfLeague = null;

	public TeamImpl(String name, 
                    Student captain, 
                    League league)
    {
        super(-1);
		this.name = name;
		this.captain = captain;
		this.league = league;
	}

    /** Return the name of this team.
     * @return a string which is the name of this team
     */
    public String getName(){
    	return this.name;
    }
    
    /** Set the new name for this team.
     * @param name the new name for this team
     * @throws RDException in case a team with the given name already exists
     */
    public void setName( String name ) throws RDException{
    	this.name = name;
    }
    
    /** Return the team's captain.
     * @return the student who is this team's captain
     */
    public Student getCaptain(){
    	return this.captain;
    }
    
    /** Set the team's captain.
     * @param student the student who is the new captain of this team
     * @throws RDException in case the student is null
     */
    public void setCaptain( Student student ) throws RDException{
    	this.captain = student;
    }
    
    /** Return the league in which this team participates.
     * @return the league of this team
     */
    public League getParticipatesInLeague(){
    	return this.league;
    }
    
    /** Set the league in which this team participates.
     * @param league the new league for this team
     * @throws RDException in case the league is null
     */
    public void setParticipatesInLeague( League league ) throws RDException{
    	this.league = league;
    }
    
    /** Return the league of which this team is the winner.
     * @return the league that this team won
     */
    public League getWinnerOfLeague(){
    	return this.winnerOfLeague;
    }
    
    /** Set the league in which this team is the winner.
     * @param league the league that this team won
     * @throws RDException in case the league is null or this team does not participate in the league
     */
    public void setWinnerOfLeague( League league ) throws RDException{
    	this.league = winnerOfLeague;
    }

    public String toString()
    {
        return "Team[" + getName() + "] " + getCaptin() + " " + getParticipatesInLeague() + " " + getWinnerOfLeague();
    }
    
    public boolean equals( Object otherTeam )
    {
        if( otherTeam == null )
            return false;
        if( otherTeam instanceof Team ) // name is a unique attribute
            return getName().equals( ((Team)otherTeam).getName() );
        return false;        
    }

}
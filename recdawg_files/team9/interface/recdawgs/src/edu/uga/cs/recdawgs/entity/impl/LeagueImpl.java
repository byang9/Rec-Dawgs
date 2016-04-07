package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.persistence.Persistable;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;


public class LeagueImpl extends Pesistence implements League {
    
    private String name;
    private Team winnerOfLeague;
    private boolean isIndoor;
    private int minTeams;
    private int maxTeams;
    private int minTeamMembers;
    private int maxTeamMembers;
    private String matchRules;
    private String leagueRules;
    
    
    /** Return the name of this league.
     * @return the name of this league
     */
    public String getName() {
        return name;
    }
    
    
    /** Set the new name of this league.
     * @param name the new name of this league
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /** Return the rules of this league.
     * @return the rules of this league
     */
    public String getLeagueRules() {
        return leagueRules;
    }
    
    
    /** Set the new rules of this league.
     * @param leagueRules the new rules of this league
     */
    public void setLeagueRules(String leagueRules) {
        this.leagueRules = leagueRules;
    }

    
    /** Return the match rules of this league.
     * @return the match rules of this league
     */
    public String getMatchRules() {
        return matchRules;
    }
    
    
    /** Set the new match rules of this league.
     * @param matchRules the new match rules of this league
     */
    public void setMatchRules(String matchRules) {
        this.matchRules = matchRules;
    }
    
    
    /** Return the indoor status of this league.
     * @return the indoor status of this league
     */
    public boolean getIsIndoor() {
        return isIndoor;    
    }
    
    
    /** Set the new indoor status of this league
     * @param isIndoor the new indoor status of this league
     */
    public void setIsIndoor(boolean isIndoor) {
        this.isIndoor = isIndoor;
    }
    
    
    /** Return the minimum number of teams in this league.
     * @return the minimum number of teams in this league
     */
    public int getMinTeams() {
        return minTeams;
    }
    

    /** Set the new minimum number of teams in this league.
     * @param minTeams the new minimum number of teams in this league
     * @throws RDException in case minTeams is not positive
     */
    public void setMinTeams(int minTeams) throws RDException {
        if (minTeams < 0) throw new RDException("RDException in LeagueImpl.java: Cannot set minTeams to be below 0.");
        this.minTeams = minTeams;
    }
    

    /** Return the maximum number of teams in this league.
     * @return the maximum number of teams in this league
     */
    public int getMaxTeams() {
        return maxTeams;
    }

    
    /** Set the new maximum number of teams in this league.
     * @param maxTeams the new maximum number of teams in this league
     * @throws RDException in case maxTeams is not positive or less than the current minimum number of teams for this league
     */
    public void setMaxTeams(int maxTeams) throws RDException {
        if (minTeams < 0) throw new RDException("RDException in LeagueImpl.java: Cannot set maxTeams to be below 0.");
        if (maxTeams < minTeams) throw new RDException("RDException in LeagueImpl.java: Cannot set maxTeams to be below minTeams.");
        this.maxTeams = maxTeams;
    }
    

    /** Return the minimum number of team players in this league.
     * @return the minimum number of team players in this league
     */
    public int getMinTeamMembers() {
        return minTeamMembers;
    }
    

    /** Set the new minimum number of team players in this league
     * @param minMembers the new minimum number of team players in this league
     * @throws RDException in case minMembers is not positive
     */
    public void setMinTeamMembers(int minTeamMembers) throws RDException {
        if (minTeamMembers < 0) throw new RDException("RDException in LeagueImpl.java: Cannot set minTeamMembers to be below 0.");
        this.minTeamMembers = minTeamMembers;
    }
    

    /** Return the maximum number of team players in this league.
     * @return the maximum number of team players in this league
     */
    public int getMaxTeamMembers() {
        return maxTeamMembers;
    }
    

    /** Set the new maximum number of team players in this league
     * @param maxMembers the new maximum number of team players in this league
     * @throws RDException in case maxMembers is not positive or less than the current minimum number of team players for this league
     */
    public void setMaxTeamMembers( int maxTeamMembers ) throws RDException {
        if (maxTeamMembers < 0) throw new RDException("RDException in LeagueImpl.java: Cannot set maxTeamMembers to be below 0.");
        if (maxTeamMembers < minTeamMembers) throw new RDException("RDException in LeagueImpl.java: Cannot set maxTeamMembers to be below minTeamMembers.");
        this.maxTeamMembers = maxTeamMembers;
    }
    
      
    /** Return the the winner of this league.
     * @return the team which is the winner of this league
     */
    public Team getWinnerOfLeague() {
        return winnerOfLeague;
    }
    
    
    /** Set the league winner.
     * @param team which is this league's winner
     * @throws RDException in case the team is null or the team is not participating in this league
     */
    public void setWinnerOfLeague(Team team) throws RDException {
        if (team == null) throw new RDException("RDException in LeagueImpl.java: Cannot set winnerOfLeague cannot be null or a team that is not in this League.");
        this.winnerOfLeague = team;
    }

}

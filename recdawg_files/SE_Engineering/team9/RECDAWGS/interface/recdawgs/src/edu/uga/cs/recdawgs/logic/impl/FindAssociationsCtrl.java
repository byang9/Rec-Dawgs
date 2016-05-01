//
// A control class to implement the 'List team membership' use case
//
//


package edu.uga.cs.recdawgs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class FindAssociationsCtrl 
{
    private ObjectLayer objectLayer = null;
    
    public FindAssociationsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public List<Student> findTeamMembers( String teamName )
            throws RDException
    {
        Team                team = null;
        Team                modelTeam = null;
        Iterator<Team>      teamIter = null;
        Iterator<Student>     studentIter = null;
        List<Student>        students  = null;

        students = new LinkedList<Student>();

        // find the team object
        modelTeam = objectLayer.createTeam();
        modelTeam.setName( teamName );
        teamIter = objectLayer.findTeam( modelTeam );
        while( teamIter.hasNext() ) {
            team = teamIter.next();
        }
        if( team == null )
            throw new RDException( "A team with this name does not exist: " + teamName );

        studentIter = objectLayer.restoreStudentMemberOfTeam(team);
        while ( studentIter.hasNext() )
        	students.add(studentIter.next());
        
        return students;
    }
    
    public List<Team> findTeamsOfLeague( String leagueName )
            throws RDException
    {
        League                league = null;
        League                modelLeague = null;
        Iterator<League>      leagueIter = null;
        Iterator<Team>     teamIter = null;
        List<Team>        teams  = null;

        teams = new LinkedList<Team>();

        // find the league object
        modelLeague = objectLayer.createLeague();
        modelLeague.setName( leagueName );
        leagueIter = objectLayer.findLeague( modelLeague );
        while( leagueIter.hasNext() ) {
            league = leagueIter.next();
        }
        if( league == null )
            throw new RDException( "A league with this name does not exist: " + leagueName );

        teamIter = objectLayer.restoreTeamParticipatesInLeague(league);
        while ( teamIter.hasNext() )
        	teams.add(teamIter.next());
        
        return teams;
    }
    
    
    public List<League> findLeaguesOfSV( String svName )
            throws RDException
    {
        SportsVenue				sv = null;
        SportsVenue				modelSV = null;
        Iterator<SportsVenue>	svIter = null;
        Iterator<League>		leagueIter = null;
        List<League>			leagues  = null;

        leagues = new LinkedList<League>();

        // find the league object
        modelSV = objectLayer.createSportsVenue();
        modelSV.setName( svName );
        svIter = objectLayer.findSportsVenue( modelSV );
        while( svIter.hasNext() ) {
            sv = svIter.next();
        }
        if( sv == null )
            throw new RDException( "A sports venue with this name does not exist: " + svName );

        leagueIter = objectLayer.restoreLeagueSportsVenue(sv);
        while ( leagueIter.hasNext() )
        	leagues.add(leagueIter.next());
        
        return leagues;
    }
    
    
    public List<SportsVenue> findSVOfLeague( String leagueName )
            throws RDException
    {
        League					league = null;
        League					modelLeague = null;
        Iterator<SportsVenue>	svIter = null;
        Iterator<League>		leagueIter = null;
        List<SportsVenue>		svs  = null;

        svs = new LinkedList<SportsVenue>();

        // find the league object
        modelLeague = objectLayer.createLeague();
        modelLeague.setName( leagueName );
        leagueIter = objectLayer.findLeague( modelLeague );
        while( leagueIter.hasNext() ) {
            league = leagueIter.next();
        }
        if( league == null )
            throw new RDException( "A sports venue with this name does not exist: " + leagueName );

        svIter = objectLayer.restoreLeagueSportsVenue(league);
        while ( svIter.hasNext() )
        	svs.add(svIter.next());
        
        return svs;
    }
    
    public List<ScoreReport> findLeagueResult(String leagueName) throws RDException{
        League                  league = null;
        League                  modelLeague = null;
        Iterator<League>        leagueIter = null;
        ScoreReport             sr = null;
        Iterator<ScoreReport>   srIter = null;
        List<ScoreReport>       svs = null;
        
        modelLeague = objectLayer.createLeague();
        modelLeague.setName(leagueName);
        leagueIter = objectLayer.findLeague(modelLeague);
        while( leagueIter.hasNext() ) {
            league = leagueIter.next();
        }
        if( league == null )
            throw new RDException( "The league could not be found:  " + leagueName );
        
        srIter = objectLayer.findScoreReport(null);
        while (srIter.hasNext()){
            sr = srIter.next();
            if (sr.getMatch() != null)
                if(sr.getMatch().getHomeTeam().getParticipatesInLeague().getId() == modelLeague.getId()){
                    svs.add(sr);
            }   
        }
        
        return svs;
    }
  
}

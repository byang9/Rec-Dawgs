//
// A control class to implement the 'Create league' use case
//
//

package edu.uga.cs.recdawgs.logic.impl;


import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import java.util.*;



public class CreateSchedule {
    

    private ObjectLayer objectLayer = null;
    
    public CreateSchedule( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
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

    public void createSchedule( String nameOfLeague )
            throws RDException
    { 
      
        List<Team> teams = findTeamsOfLeague(nameOfLeague);
        int numOfTeams = teams.size();
        //create a matrix for the schedule
        int[][] schedule = scheduler(numOfTeams);
        
        //HashMap<Integer, Integer> teamToScheduleId = new HashMap<Integer, Integer>();
        //create a hashmap that maps a schedule Id to a team
        HashMap<Integer, Team> teamToScheduleId = new HashMap<Integer, Team>();

        //initialize hashmap
        for (int i = 0; i < teams.size(); i++){
           // teamToScheduleId.put((int) teams.get(i).getId(), i);
        	teamToScheduleId.put(i, teams.get(i));
        }


        for (int row = 0; row < schedule.length; row++){
            

            for (int col = row+1; col < schedule[0].length; col++){
                Calendar c = Calendar.getInstance();
                c.set(Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR + (7 * schedule[row][col]));

                Date roundDate = c.getTime();
                //create round= round[row , col] 
                //create match (team 1 = row, team 2 = col, round  )
                //create Match on the given data
                objectLayer.createMatch(0, 0, roundDate, false, teamToScheduleId.get(row), teamToScheduleId.get(col));
                
            }
        }

        //    public Match createMatch( int homePoints, int awayPoints, Date date, boolean isCompleted,
         //   Team homeTeam, Team awayTeam ) throws RDException;
    }

    public int getRemaining(int[][]schedules, int i, int j){
        //Check rows and check columns
        //get the lowest value thats not in the column
        HashSet<Integer> invalids = new HashSet<Integer>();
        HashSet<Integer> daysCanPlay = new HashSet<Integer>();
        
        
        for (int row = 0; row < schedules.length; row++){
            //keep a list of the teams
            daysCanPlay.add(row+1);
            //add in extra days just in case
            daysCanPlay.add((row+1 + schedules.length));
            for (int col = 0; col < schedules[0].length; col++){
                if (row == i-1){
                    invalids.add(schedules[row][col]);
                } 
                if (col == j-1){
                    invalids.add(schedules[row][col]);
                }

            }
        }
        //from the set of teams, remove all that have been in the same rows or same columns
        daysCanPlay.removeAll(invalids);
            Iterator iter = daysCanPlay.iterator();
        int lowest = 99;
        while(iter.hasNext()){
            int temp = (Integer)iter.next();
            lowest = temp < lowest ? temp : lowest;
        }
        return lowest;
    }

    public int[][] scheduler(int numTeams){
        int[][]schedule = new int[numTeams][numTeams];  
        for (int i = 1; i <= schedule.length; i++){
            for (int j = 1; j <= schedule[0].length; j++){
                if (i == j) schedule[i-1][j-1] = 0;
                else{
                    //newScheduleAndIndex update = getRemaining(schedules, i, j);
                    schedule[i-1][j-1] = getRemaining(schedule, i, j);
                }
            }//for

        }//for
        return schedule;
    }//createSchedule
    
}


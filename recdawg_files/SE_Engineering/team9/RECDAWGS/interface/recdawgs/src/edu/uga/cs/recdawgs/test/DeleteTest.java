package edu.uga.cs.recdawgs.test;

import java.sql.Connection;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.*;
import edu.uga.cs.recdawgs.object.*;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImpl;
import edu.uga.cs.recdawgs.persistence.*;
import edu.uga.cs.recdawgs.persistence.impl.PersistenceLayerImpl;

public class DeleteTest{
    public static void main(String[] args){
        Connection  conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

         // get a database connection
        try {
            conn = DbUtils.connect();
        }catch (Exception seq) {
            System.err.println( "DeleteTest: Unable to obtain a database connection" );
        }
        // obtain a reference to the ObjectModel module      
        objectLayer = new ObjectLayerImpl();
        // obtain a reference to Persistence module and connect it to the ObjectModel        
        persistence = new PersistenceLayerImpl( conn, objectLayer ); 
        // connect the ObjectModel module to the Persistence module
        objectLayer.setPersistence( persistence ); 
        
        Iterator<League> leagueIter = null;
        
        try{
            //printing out all leagues
            League tennisLeague = null;
            League swimLeague = objectLayer.createLeague();
            swimLeague.setName("Swimming");
            leagueIter = objectLayer.findLeague(swimLeague);
            while (leagueIter.hasNext()){
                tennisLeague = leagueIter.next();
                System.out.println("League: " +tennisLeague);
            }
            if(tennisLeague != null){
                objectLayer.deleteLeague(tennisLeague);
                System.out.println("Deleted Leagues");
            }else{
                System.out.println("Error finding Leagues");
            }
            
            //printing out all Rounds
            Round tempRound = null;
            Round startRound = objectLayer.createRound();
            Iterator<Round> roundIter = objectLayer.findRound(startRound);
            while(roundIter.hasNext()){
                tempRound = roundIter.next();
                System.out.println("Round: " + tempRound);
            }
            if(tempRound != null){
                objectLayer.deleteRound(tempRound);
                System.out.println("Deleted Round");
            }else{
                System.out.println("Error finding Rounds");
            }
            
            //printing out all Match
            Match tempMatch = null;
            Match startMatch = objectLayer.createMatch();
            Iterator<Match> matchIter = objectLayer.findMatch(startMatch);
            while(matchIter.hasNext()){
                tempMatch = matchIter.next();
                System.out.println("Match: " + tempMatch);
            }
            if(tempMatch != null){
                objectLayer.deleteMatch(tempMatch);
                System.out.println("Deleted Match");
            }else{
                System.out.println("Error finding Matches");
            }
            
            //printing out all Teams
            Team tempTeam = null;
            Team startTeam = objectLayer.createTeam();
            Iterator<Team> teamIter = objectLayer.findTeam(startTeam);
            while(teamIter.hasNext()){
                tempTeam = teamIter.next();
                System.out.println("Team: " + tempTeam);
            }
            if(tempTeam != null){
                objectLayer.deleteTeam(tempTeam);
                System.out.println("Deleted Team");
            }else{
                System.out.println("Error finding Teams");
            }
            
            //printing out all Students
            Student tempStudent = null;
            Student startStudent = objectLayer.createStudent();
            Iterator<Student> studentIter = objectLayer.findStudent(startStudent);
            while(studentIter.hasNext()){
                tempStudent = studentIter.next();
                System.out.println("Student: " + tempStudent);
            }
            if(tempStudent != null){
                objectLayer.deleteStudent(tempStudent);
                System.out.println("Deleted Student");
            }else{
                System.out.println("Error finding Students");
            }
            
            //printing out all SportsVenues
            SportsVenue tempSportsVenue = null;
            SportsVenue startSportsVenue = objectLayer.createSportsVenue("grass","yolo street",false);
            Iterator<SportsVenue> sportsVenueIter = objectLayer.findSportsVenue(startSportsVenue);
            while(sportsVenueIter.hasNext()){
                tempSportsVenue = sportsVenueIter.next();
                System.out.println("SportsVenue: " + tempSportsVenue);
            }
            if(tempSportsVenue != null){
                objectLayer.deleteSportsVenue(tempSportsVenue);
                System.out.println("Deleted SportsVenue");
            }else{
                System.out.println("Error finding SportsVenues");
            }
            
            //printing out all Administrators
            Administrator tempAdministrator = null;
            Administrator startAdministrator = objectLayer.createAdministrator();
            Iterator<Administrator> administratorIter = objectLayer.findAdministrator(startAdministrator);
            while(administratorIter.hasNext()){
                tempAdministrator = administratorIter.next();
                System.out.println("Administrator: " + tempAdministrator);
            }
            if(tempAdministrator != null){
                objectLayer.deleteAdministrator(tempAdministrator);
                System.out.println("Deleted Administrator");
            }else{
                System.out.println("Error finding Administrators");
            }
            
            //printing out all ScoreReports
            ScoreReport tempScoreReport = null;
            ScoreReport startScoreReport = objectLayer.createScoreReport();
            Iterator<ScoreReport> scoreReportIter = objectLayer.findScoreReport(startScoreReport);
            while(scoreReportIter.hasNext()){
                tempScoreReport = scoreReportIter.next();
                System.out.println("ScoreReport: " + tempScoreReport);
            }
            if(tempScoreReport != null){
                objectLayer.deleteScoreReport(tempScoreReport);
                System.out.println("Deleted ScoreReport");
            }else{
                System.out.println("Error finding ScoreReports");
            }
            
        }
        catch( RDException ce )
         {
             System.err.println( "RDException: " + ce );
             ce.printStackTrace();
         }
         catch( Exception e )
         {
             System.err.println( "Exception: " + e );
             e.printStackTrace();
         }
         finally {
             // close the connection
             try {
                 conn.close();
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }   
    }
}
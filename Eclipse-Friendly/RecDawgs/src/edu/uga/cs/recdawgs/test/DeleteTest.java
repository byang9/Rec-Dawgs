package edu.uga.cs.recdawgs.test;

import java.sql.Connection;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.*;
import edu.uga.cs.recdawgs.object.*;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImpl;
import edu.uga.cs.recdawgs.persistence.*;
import edu.uga.cs.recdawgs.persistence.impl.DbUtils;
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
        
        
        try{
            //printing out all leagues
            League tempLeague = null;
            Iterator<League> leagueIter = objectLayer.findLeague(null);
            while (leagueIter.hasNext()){
                tempLeague = leagueIter.next();
                if(tempLeague != null){
                    System.out.println("Deleted League: "+ tempLeague.getName());
                    objectLayer.deleteLeague(tempLeague);
                }
            }if(tempLeague == null){
                System.out.println("Error finding Leagues");
            }
            
            //printing out all Rounds
            Round tempRound = null;
            Iterator<Round> RoundIter = objectLayer.findRound(null);
            while (RoundIter.hasNext()){
                tempRound = RoundIter.next();
                if(tempRound != null){
                    System.out.println("Deleted Round: #"+ tempRound.getNumber());
                    objectLayer.deleteRound(tempRound);
                }
            }if(tempRound == null){
                System.out.println("Error finding Rounds");
            }
            
            //printing out all Matchs
            Match tempMatch = null;
            Iterator<Match> MatchIter = objectLayer.findMatch(null);
            while (MatchIter.hasNext()){
                tempMatch = MatchIter.next();
                if(tempMatch != null){
                    System.out.println("Deleted Match on date: "+ tempMatch.getDate());
                    objectLayer.deleteMatch(tempMatch);
                }
            }if(tempMatch == null){
                System.out.println("Error finding Matchs");
            }
            
            
            //printing out all Teams
            Team tempTeam = null;
            Iterator<Team> TeamIter = objectLayer.findTeam(null);
            while (TeamIter.hasNext()){
                tempTeam = TeamIter.next();
                if(tempTeam != null){
                    System.out.println("Deleted Team: "+ tempTeam.getName());
                    objectLayer.deleteTeam(tempTeam);
                }
            }if(tempTeam == null){
                System.out.println("Error finding Teams");
            }
            
            
            //printing out all Students
            Student tempStudent = null;
            Iterator<Student> StudentIter = objectLayer.findStudent(null);
            while (StudentIter.hasNext()){
                tempStudent = StudentIter.next();
                if(tempStudent != null){
                    System.out.println("Deleted Student: "+ tempStudent.getFirstName());
                    objectLayer.deleteStudent(tempStudent);
                }
            }if(tempStudent == null){
                System.out.println("Error finding Students");
            }
            
            //printing out all SportsVenues
            SportsVenue tempSportsVenue = null;
            Iterator<SportsVenue> SportsVenueIter = objectLayer.findSportsVenue(null);
            while (SportsVenueIter.hasNext()){
                tempSportsVenue = SportsVenueIter.next();
                if(tempSportsVenue != null){
                    System.out.println("Deleted SportsVenue: "+ tempSportsVenue.getName());
                    objectLayer.deleteSportsVenue(tempSportsVenue);
                }
            }if(tempSportsVenue == null){
                System.out.println("Error finding SportsVenues");
            }
            
            //printing out all Administrators
            Administrator tempAdministrator = null;
            Iterator<Administrator> AdministratorIter = objectLayer.findAdministrator(null);
            while (AdministratorIter.hasNext()){
                tempAdministrator = AdministratorIter.next();
                if(tempAdministrator != null){
                    System.out.println("Deleted Administrator: "+ tempAdministrator.getFirstName());
                    objectLayer.deleteAdministrator(tempAdministrator);
                }
            }if(tempAdministrator == null){
                System.out.println("Error finding Administrators");
            }
            
            //printing out all ScoreReports
            ScoreReport tempScoreReport = null;
            Iterator<ScoreReport> ScoreReportIter = objectLayer.findScoreReport(null);
            while (ScoreReportIter.hasNext()){
                tempScoreReport = ScoreReportIter.next();
                if(tempScoreReport != null){
                    System.out.println("Deleted ScoreReport on date: "+ tempScoreReport.getDate());
                    objectLayer.deleteScoreReport(tempScoreReport);
                }
            }if(tempScoreReport == null){
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
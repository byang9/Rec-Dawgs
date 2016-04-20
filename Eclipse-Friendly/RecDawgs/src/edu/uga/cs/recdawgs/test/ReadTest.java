package edu.uga.cs.recdawgs.test;

import java.sql.Connection;
import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.*;
import edu.uga.cs.recdawgs.object.*;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImpl;
import edu.uga.cs.recdawgs.persistence.*;
import edu.uga.cs.recdawgs.persistence.impl.*;
//testing Read
public class ReadTest{
    public static void main(String[] args){
        Connection  conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

         // get a database connection
        try {
            conn = DbUtils.connect();
        }catch (Exception seq) {
            System.err.println( "ReadTest: Unable to obtain a database connection" );
        }
        // obtain a reference to the ObjectModel module      
        objectLayer = new ObjectLayerImpl();
        // obtain a reference to Persistence module and connect it to the ObjectModel        
        persistence = new PersistenceLayerImpl( conn, objectLayer ); 
        // connect the ObjectModel module to the Persistence module
        objectLayer.setPersistence( persistence ); 
        
        try{
            System.out.println("League objects: ");
            Iterator<League> leagueIter = objectLayer.findLeague(null);
            while(leagueIter.hasNext()){
                League a = leagueIter.next();
                System.out.print(a.getName()+ " ");
            }
            System.out.println("\nRound objects: ");
            Iterator<Round> roundIter = objectLayer.findRound(null);
            while(roundIter.hasNext()){
                Round a = roundIter.next();
                System.out.print(a+ " ");
            }
            System.out.println("\nTeam objects: ");
            Iterator<Team> teamIter = objectLayer.findTeam(null);
            while(teamIter.hasNext()){
                Team a = teamIter.next();
                System.out.print(a+ " ");
            }
            System.out.println("\nMatch objects: ");
            Iterator<Match> matchIter = objectLayer.findMatch(null);
            while(matchIter.hasNext()){
                Match a = matchIter.next();
                System.out.print(a + " ");
            }
            System.out.println("\nSportsVenue objects: ");
            Iterator<SportsVenue> sportsVenueIter = objectLayer.findSportsVenue(null);
            while(sportsVenueIter.hasNext()){
                SportsVenue a = sportsVenueIter.next();
                System.out.print(a.getName() + " ");
            }
            System.out.println("\nStudent objects: ");
            Iterator<Student> studentIter = objectLayer.findStudent(null);
            while(studentIter.hasNext()){
                Student a = studentIter.next();
                System.out.print(a.getFirstName() +" " + a.getLastName()+ " ");
            }
            System.out.println("\nAdministrator objects: ");
            Iterator<Administrator> administratorIter = objectLayer.findAdministrator(null);
            while(administratorIter.hasNext()){
                Administrator a = administratorIter.next();
                System.out.print(a.getFirstName()+ " ");
            }
            System.out.println("\nScoreReport objects: ");
            Iterator<ScoreReport> scoreReportIter = objectLayer.findScoreReport(null);
            while(scoreReportIter.hasNext()){
                ScoreReport a = scoreReportIter.next();
                System.out.print(a+ " ");
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
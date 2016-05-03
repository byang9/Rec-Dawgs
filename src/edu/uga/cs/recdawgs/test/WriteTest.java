package edu.uga.cs.recdawgs.test;

import java.sql.Connection;
import java.util.Date;

//TODO imports
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImpl;
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;
import edu.uga.cs.recdawgs.persistence.impl.DbUtils;
import edu.uga.cs.recdawgs.persistence.impl.PersistenceLayerImpl;

public class WriteTest
{
    public static void main(String[] args)
    {
         Connection conn = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;
         
         Administrator logan;
         Administrator justin;
         Administrator jay;
         Administrator bowen;

         Student       tim;
         Student       gary;
         Student       fred;
         Student       hank;
         Student       bob;
         Student       brandon;

         League        golf;
         League        bowling;
         League        baseball;

         Team          teamUno;
         Team          teamDos;
         Team          baseball1;
         Team          baseball2;
         Team          baseball3;
         Team          baseball4;

         SportsVenue   golfCourse;
         SportsVenue   quad;
         SportsVenue   baseballField;
         
         Match		   baseballgame;
         
         ScoreReport   baseballreport;


         
         
         // get a database connection
         try {
             conn = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "WriteTest: Unable to obtain a database connection" );
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( conn, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence );   

         try {
        	 
             // create a few Admins
             //createAdministrator(String firstName, String lastName, String userName, String password, String emailAddress) 
             logan = objectLayer.createAdministrator("Logan", "Jahnke","loganjahnke","admin","jahnke@uga.edu");
             justin = objectLayer.createAdministrator("Justin", "Tumale","justintumale","admin","jtumale1@uga.edu");
             jay = objectLayer.createAdministrator("Jerry", "Springfield","jerryspringfield","admin","jls93@uga.edu");
             bowen = objectLayer.createAdministrator("Bowen", "Yang","bowenyang","admin","byang9@uga.edu");

             persistence.storeAdministrator(logan);
             persistence.storeAdministrator(justin);
             persistence.storeAdministrator(jay);
             persistence.storeAdministrator(bowen);


             //create a few Students
             //createStudent(String firstName, String lastName, String userName, String password, String emailAddress, String studentId, String major, String address)
             tim = objectLayer.createStudent("Tim", "Time", "tt1", "recdawgs", "tt@uga.edu", "151", "Communication", "123 Fairy Lane, Athens, GA");
             gary = objectLayer.createStudent("Gary", "Garsmith", "gg1", "recdawgs", "gg@uga.edu","535", "Friendship", "666 Doomsville Street, Athens, GA");
             fred = objectLayer.createStudent("Fred", "Flinestone", "ff1", "recdawgs", "ff@uga.edu","657", "Computer Science", "122 Hit n' Run Lane, Athens, GA");
             hank = objectLayer.createStudent("Hank", "Aaron", "ha1", "recdawgs", "ha@uga.edu","234", "Math", "404 Not Found Street, Athens, GA");
             bob = objectLayer.createStudent("Bob", "Millie", "bm1", "recdawgs", "bm@uga.edu","345", "Pre-Med", "403 Redirected Street, Athens, GA");
             brandon = objectLayer.createStudent("Brandon", "Rockwell", "br1", "recdawgs", "brandon.rockwell42@uga.edu","999", "Biochemistry", "100 First Road, Athens, GA");

             persistence.storeStudent( tim );
             persistence.storeStudent( gary );
             persistence.storeStudent( fred );
             persistence.storeStudent( hank );
             persistence.storeStudent( bob );
             persistence.storeStudent( brandon );
             
             //String name, String leagueRules, String matchRules, boolean isIndoor, int minTeams, int maxTeams, int minPlayers, int maxPlayers
             golf = objectLayer.createLeague("Golf", "no cheating", "please", false, 10 , 16, 10, 12);
             bowling = objectLayer.createLeague("Bowling", "no cheating", "don't", true, 10 , 16, 10, 12);
             baseball = objectLayer.createLeague("Baseball", "no cheating", "don't", true, 4 , 8, 9, 15);

             persistence.storeLeague(golf);
             persistence.storeLeague(bowling);
             persistence.storeLeague(baseball);
             
             //createSportsVenue(String name, String address, boolean isIndoor)
             golfCourse = objectLayer.createSportsVenue("The Golf Course", "1 East Campus Rd., Athens, GA", true);
             quad = objectLayer.createSportsVenue("Myers Quad", "Myers Building, Athens, GA", false);
             baseballField = objectLayer.createSportsVenue("Baseball Field", "Baseball Building, Athens, GA", false);
             
             persistence.storeSportsVenue(golfCourse);
             persistence.storeSportsVenue(quad);
             persistence.storeSportsVenue(baseballField);

             //Team createTeam(String name)
             teamUno = objectLayer.createTeam("Golf Team Bro");
             teamUno.setParticipatesInLeague(golf);
             teamUno.setCaptain(tim);
             teamDos = objectLayer.createTeam("Football Club FC");
             teamDos.setParticipatesInLeague(bowling);
             teamDos.setCaptain(gary);
             baseball1 = objectLayer.createTeam("The Braves");
             baseball1.setParticipatesInLeague(baseball);
             baseball1.setCaptain(fred);
             baseball2 = objectLayer.createTeam("Titans");
             baseball2.setParticipatesInLeague(baseball);
             baseball2.setCaptain(bob);
             baseball3 = objectLayer.createTeam("Pollution");
             baseball3.setParticipatesInLeague(baseball);
             baseball3.setCaptain(hank);
             baseball4 = objectLayer.createTeam("Galactic Empire");
             baseball4.setParticipatesInLeague(baseball);
             baseball4.setCaptain(brandon);
             
             persistence.storeTeam(teamUno);
             persistence.storeTeam(teamDos);
             persistence.storeTeam(baseball1);
             persistence.storeTeam(baseball2);
             persistence.storeTeam(baseball3);
             persistence.storeTeam(baseball4);
             
             // Add League and Sports Venue Connection
             persistence.storeLeagueSportsVenue(baseball, baseballField);
             
             // Add Baseball Match
             baseballgame = objectLayer.createMatch(7, 6, new Date(java.lang.System.currentTimeMillis()), true, baseball4, baseball2);
             persistence.storeMatch(baseballgame);
             
             // Add Score Report
             baseballreport = objectLayer.createScoreReport(7, 6, new Date(java.lang.System.currentTimeMillis()), brandon, baseballgame);
             persistence.storeScoreReport(baseballreport);

             // Add Associations
             persistence.storeMatchSportsVenue(baseballgame, baseballField);
             persistence.storeTeamParticipatesInLeague(baseball4, baseball);
             persistence.storeTeamParticipatesInLeague(baseball3, baseball);
             persistence.storeTeamParticipatesInLeague(baseball2, baseball);
             persistence.storeTeamParticipatesInLeague(baseball1, baseball);
             
             System.out.println( "Entity objects created and saved in the persistence module" );
             
         }
         catch( RDException ce) {
             System.err.println( "Exception: " + ce );
             ce.printStackTrace();
         }
         catch( Exception e ) {
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

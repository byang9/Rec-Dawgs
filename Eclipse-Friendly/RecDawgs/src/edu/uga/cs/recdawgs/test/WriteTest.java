package edu.uga.cs.recdawgs.test;

import java.sql.Connection;

//TODO imports
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
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
         
         Administrator brandon;

         Student       tim;
         Student       gary;

         League        golf;
         League        bowling;

         Team          teamUno;
         Team          teamDos;

         SportsVenue   golfCourse;
         SportsVenue   quad;


         
         
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
//             brandon = objectLayer.createAdministrator("brandon", "rockwell","br1","recdawgs","br@uga.edu");
//
//             persistence.storeAdministrator(brandon);


             //create a few Students
             //createStudent(String firstName, String lastName, String userName, String password, String emailAddress, String studentId, String major, String address)
             tim = objectLayer.createStudent("tim", "time", "tt1", "recdawgs1", "tt@uga.edu", "151", "Communication", "123 Fairy Lane, Athens, GA");
             tim.setId(16);
             gary = objectLayer.createStudent("gary", "garsmith", "gg1", "recdawgs2", "gg@uga.edu","535", "Friendship", "666 Doomsville Street, Athens, GA");
             gary.setId(17);

             //persistence.storeStudent( tim );
             //persistence.storeStudent( gary );
             
             //String name, String leagueRules, String matchRules, boolean isIndoor, int minTeams, int maxTeams, int minPlayers, int maxPlayers
             golf = objectLayer.createLeague("Golf", "no cheating", "please", false, 10 , 16, 10, 12);
             bowling = objectLayer.createLeague("Bowling", "no cheating", "don't", true, 10 , 16, 10, 12);

             //persistence.storeLeague(golf);
             //persistence.storeLeague(bowling);

             //Team createTeam(String name)
             teamUno = objectLayer.createTeam("Golf Team Bro");
             teamUno.setParticipatesInLeague(golf);
             teamUno.setCaptain(tim);
             teamDos = objectLayer.createTeam("Football Club FC");
             teamDos.setParticipatesInLeague(bowling);
             teamDos.setCaptain(gary);
             
             //persistence.storeTeam(teamUno);
             //persistence.storeTeam(teamDos);

             //createSportsVenue(String name, String address, boolean isIndoor)
             golfCourse = objectLayer.createSportsVenue("The Golf Course", "1 East Campus Rd., Athens, GA", true);
             golfCourse.setId(8);
             quad = objectLayer.createSportsVenue("Myers Quad", "Myers Building, Athens, GA", false);
             quad.setId(5);
             persistence.storeSportsVenue(golfCourse);
             persistence.storeSportsVenue(quad);

             
             
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

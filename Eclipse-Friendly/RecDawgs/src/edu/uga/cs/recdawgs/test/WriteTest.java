package edu.uga.cs.recdawgs.test.object;

import java.sql.Connection;
import java.util.Date;

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
         
         Admin         logan;
         Admin         bowen;

         Student       jay;
         Student       fred;
         Student       ani;
         Student       joe;

         League         soccer;
         League         basketball;

         Team           teamA1;
         Team           teamB1;
         Team           teamA2;
         Team           teamB2;

         SportsVenue          ramsey;
         SportsVenue          iMFields;


         
         
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
             logan = objectLayer.createAdministrator("logan", "jahnke","lj1","recdawgs","lj@uga.edu");
             bowen = objectLayer.createAdministrator("bowen", "yang","by1","recdawgs","by@uga.edu");

             persistence.storeAdministrator(logan);
             persistence.storedministrator(bowen);


             //create a few Students
             //createStudent(String firstName, String lastName, String userName, String password, String emailAddress, String studentId, String major, String address)
             jay = objectLayer.createStudent("jay", "springfield", "js1", "recdawgs1", "js@uga.edu", "8109998888", "CS", "123 Fairy Lane, Athens, GA");
             fred = objectLayer.createStudent("fred", "flintstone", "ff1", "recdawgs2", "ff@uga.edu","8109998881", "CS", "565 Stonewall Drive, Athens, GA");
             ani = objectLayer.createStudent("ani", "skywalker", "as1", "recdawgs3", "as@uga.edu", "8109998882", "Drawing", "777 Lucky Drive, Athens, GA");
             joe = objectLayer.createStudent("joe", "johnson", "jj", "recdawgs4", "johnson@uga.edu", "8104221334", "Sports Medicine", "651 Brookhaven Cr. Athens, GA");

             persistence.storePerson( jay );
             persistence.storePerson( fred );
             persistence.storePerson( ani );
             persistence.storePerson( joe );
             
             //String name, String leagueRules, String matchRules, boolean isIndoor, int minTeams, int maxTeams, int minPlayers, int maxPlayers
             soccer = objectLayer.createLeague("soccer", "no cheating", False, 10 , 16, 10, 12);
             basketball = objectLayer.createLeague("basketball", "no cheating", True, 10 , 16, 10, 12);

             persistence.storeLeague(soccer);
             persistence.storeLeague(basketball);

             //Team createTeam(String name)
             teamA1 = objectLayer.createTeam("Hurricanes");
             teamB1 = objectLayer.createTeam("Dolphins");
             teamA2 = objectLayer.createTeam("Bulldogs");
             teamB2 = objectLayer.createTeam("Parrots");

             persistence.storeTeam(teamA1);
             persistence.storeTeam(teamB1);
             persistence.storeTeam(teamA2);
             persistence.storeTeam(teamB2);

             //createSportsVenue(String name, String address, boolean isIndoor)
             ramsey = objectLayer.createSportsVenue("Ramsey", "1 East Campus Rd., Athens, GA", True);
             iMFields = objectLayer.createSportsVenue("IM Fields", "13 Intramural Fields., Athens, GA", False);
             persistence.storeSportsVenue(ramsey);
             persistence.storeSportsVenue(iMFields);

             
             
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

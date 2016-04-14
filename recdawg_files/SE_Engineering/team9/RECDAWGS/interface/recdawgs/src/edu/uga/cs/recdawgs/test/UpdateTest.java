package edu.uga.cs.recdawgs.test;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;

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

public class UpdateTest
{
    public static void main(String[] args)
    {
         Connection  conn = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;

         // get a database connection
         try {
             conn = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "UpdateTest: Unable to obtain a database connection" );
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( conn, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence ); 
         
         Iterator<Administrator> adminIter = null;
                  
         try {
            //////////////////////////////////////////////////////////////////////////////////////////////////////
            //ADMIN UPDATE
            Administrator tempAdmin = null;
            Administrator modelAdmin = objectLayer.createAdministrator();
            modelAdmin.setFirstName("Justin");
            adminIter = objectLayer.findAdministrator( modelAdmin );
            while(adminIter.hasNext()){
                tempAdmin = adminIter.next();
                System.out.println(tempAdmin);
            }
            tempAdmin.setFirstName( "Francis" );
            objectLayer.storeAdministrator( tempAdmin );
            System.out.println( "Updated the name of Justin to Francis." );
            //////////////////////////////////////////////////////////////////////////////////////////////////////
            //STUDENT UPDATE
            Student tempStudent = null;
            Student modelStudent = objectLayer.createStudent();
            modelStudent.setFirstName("Jay");
            Iterator<Student> studentIter = objectLayer.findStudent( modelStudent );
            while(studentIter.hasNext()){
                tempStudent = studentIter.next();
                System.out.println(tempAdmin);
            }
            tempStudent.setFirstName( "Jerry" );
            objectLayer.storeStudent( tempStudent );
            System.out.println( "Updated the name of Jay to Jerry." );

            //////////////////////////////////////////////////////////////////////////////////////////////////////
            //LEAGUE UPDATE
            League tempLeague = null;
            League modelLeague = objectLayer.createLeague();
            modelLeague.setName("Basketball");
            Iterator<League> leagueIter = objectLayer.findLeague( modelLeague );
            while(leagueIter.hasNext()){
                tempLeague = leagueIter.next();
                System.out.println(tempLeague);
            }
            tempLeague.setName( "Advanced Basketball" );
            objectLayer.storeLeague( tempLeague );
            System.out.println( "Updated the name of Basketball to Advanced Basketball." );

            ////////////////////////////////////////////////////////////////////////////////////////////////////// 
            //SPORTSVENUE UPDATE
            SportsVenue tempVenue = null;
            SportsVenue modelVenue = objectLayer.createSportsVenue();
            modelVenue.setName("Ramsey");
            Iterator<SportsVenue> venueIter = objectLayer.findSportsVenue( modelVenue );
            while(venueIter.hasNext()){
                tempVenue = venueIter.next();
                System.out.println(tempVenue);
            }
            tempVenue.setName( "Ramsey Center" );
            objectLayer.storeSportsVenue( tempVenue );
            System.out.println( "Updated the name of Ramsey to Ramsey Center." );


            //////////////////////////////////////////////////////////////////////////////////////////////////////
            //TEAM UPDATE
            Team tempTeam = null;
            Team modelTeam = objectLayer.createTeam();
            modelTeam.setName("Hurricanes");
            Iterator<Team> teamIter = objectLayer.findTeam( modelTeam );
            while(teamIter.hasNext()){
                tempTeam = teamIter.next();
                System.out.println(tempTeam);
            }
            tempTeam.setName( "Dolphins" );
            objectLayer.storeTeam( tempTeam );
            System.out.println( "Updated the name of Hurricanes to Dolphins." );

         }
         catch( RDException ce)
         {
             System.err.println( "RDException: " + ce );
         }
         catch( Exception e)
         {
             System.err.println( "Exception: " + e );
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

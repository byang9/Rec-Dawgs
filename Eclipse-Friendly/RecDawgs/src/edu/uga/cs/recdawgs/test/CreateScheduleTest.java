package edu.uga.cs.recdawgs.test;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;

//TODO imports
import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.entity.ScoreReport;
import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.object.impl.ObjectLayerImpl;
import edu.uga.cs.recdawgs.persistence.PersistenceLayer;
import edu.uga.cs.recdawgs.persistence.impl.DbUtils;
import edu.uga.cs.recdawgs.persistence.impl.PersistenceLayerImpl;

public class CreateScheduleTest {
    public static void main(String[] args){
        Connection  conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

         // get a database connection
        try {
            conn = DbUtils.connect();
        }catch (Exception seq) {
            System.err.println( "CreateScheduleTest: Unable to obtain a database connection" );
        }
        // obtain a reference to the ObjectModel module      
        objectLayer = new ObjectLayerImpl();
        // obtain a reference to Persistence module and connect it to the ObjectModel        
        persistence = new PersistenceLayerImpl( conn, objectLayer ); 
        // connect the ObjectModel module to the Persistence module
        objectLayer.setPersistence( persistence ); 
        
        try{

            System.out.println("\nLeague objects: ");
            Iterator<League> leagueIter = objectLayer.findLeague(null);
            League league = null;
            while(leagueIter.hasNext()){
                league = leagueIter.next();
                System.out.print(league.getName()+ " ");
                if (league.getName() == "Baseball") break;
            }
            System.out.println("\nLeague chosen: " + league.getName());
            
 


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

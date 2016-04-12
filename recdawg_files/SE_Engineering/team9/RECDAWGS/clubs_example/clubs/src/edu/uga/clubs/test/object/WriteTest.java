package edu.uga.clubs.test.object;

import java.sql.Connection;
import java.util.Date;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;
import edu.uga.clubs.object.impl.ObjectLayerImpl;
import edu.uga.clubs.persistence.PersistenceLayer;
import edu.uga.clubs.persistence.impl.DbUtils;
import edu.uga.clubs.persistence.impl.PersistenceLayerImpl;

public class WriteTest
{
    public static void main(String[] args)
    {
         Connection conn = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;
         
         Club        bridge;
         Club        chess;
         Club        tennis;
         Club        running;
         Person      joe;
         Person      mary;
         Person      bob;
         Person      julie;
         Person      heather;
         Membership  membership;
         
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
             
             // create a few people
             joe = objectLayer.createPerson( "joe", "joepass", "joe@mail.com", "Joe", "Doe", "133 Maple St., Big Town, AZ. 87888", "333-4456" );
             mary = objectLayer.createPerson( "mary", "marypass", "mary@mail.com", "Mary", "Swift", "14 Oak Dr., Small Town, TX. 77888", "444-9876" );
             bob = objectLayer.createPerson( "bob", "bobpass", "bob@mail.com", "Robert", "Wilson", "33 Cedar Cr., Middle Town, NV. 81888", "567-7788" );
             julie = objectLayer.createPerson( "julie", "juliepass", "julie@mail.com", "Julie", "Hart", "99 Magnolia St., Splash Town, NY. 21888", "364-7592" );
             heather = objectLayer.createPerson( "heather", "heatherpass", "julie@mail.com", "Heather", "Brooks", "1 Pine Ave., Boom Town, GA. 30688", "339-9923" );
             
             persistence.savePerson( joe );
             persistence.savePerson( mary );
             persistence.savePerson( bob );
             persistence.savePerson( julie );
             persistence.savePerson( heather );
             
             bridge = objectLayer.createClub( "Bridge", "33 Leaf St., Blossom, OR. 88888", new Date(), joe );
             persistence.saveClub( bridge );
             
             chess = objectLayer.createClub( "Chess", "734 Pine Straw Dr., Bloom, KY. 48878", new Date(), mary );
             persistence.saveClub( chess );
             
             tennis = objectLayer.createClub( "Tennis", "333 Wide St., Flower, RI. 17345", new Date(), mary );
             persistence.saveClub( tennis );
             
             running = objectLayer.createClub( "Running", "445 Pace St., Quicker, Wy. 77546", new Date(), bob );
             persistence.saveClub( running );
             
             membership = objectLayer.createMembership( joe, bridge, new Date() );
             persistence.saveMembership( membership );
             
             membership = objectLayer.createMembership( bob, bridge, new Date() );
             persistence.saveMembership( membership );
             
             membership = objectLayer.createMembership( heather, bridge, new Date() );
             persistence.saveMembership( membership );
             
             membership = objectLayer.createMembership( mary, chess, new Date() );
             persistence.saveMembership( membership );
             
             membership = objectLayer.createMembership( mary, tennis, new Date() );
             persistence.saveMembership( membership );
             
             membership = objectLayer.createMembership( julie, tennis, new Date() );
             persistence.saveMembership( membership );
             
             membership = objectLayer.createMembership( bob, tennis, new Date() );
             persistence.saveMembership( membership );
             
             membership = objectLayer.createMembership( joe, chess, new Date() );
             persistence.saveMembership( membership );
             
             System.out.println( "Entity objects created and saved in the persistence module" );
             
         }
         catch( ClubsException ce) {
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

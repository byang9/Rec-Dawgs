package edu.uga.clubs.test.object;

import java.sql.Connection;
import java.util.Iterator;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;
import edu.uga.clubs.object.impl.ObjectLayerImpl;
import edu.uga.clubs.persistence.PersistenceLayer;
import edu.uga.clubs.persistence.impl.DbUtils;
import edu.uga.clubs.persistence.impl.PersistenceLayerImpl;

public class ReadTest
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
             System.err.println( "ReadTest: Unable to obtain a database connection" );
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( conn, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence );   
                  
         try {
             
             System.out.println( "Club objects:" );
             Iterator<Club> clubIter = objectLayer.findClub( null );
             while( clubIter.hasNext() ) {
                 Club c = clubIter.next();
                 System.out.println( c );
                 Person founder = objectLayer.findFounderFoundedClub( c );
                 System.out.println( "   Founded by: " + founder );
                 System.out.println( "   Members: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setClub( c );
                 Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getPerson() );
                 }
             }
             
             System.out.println( "Person objects:" );
             Iterator<Person> personIter = objectLayer.findPerson( null );
             while( personIter.hasNext() ) {
                 Person p = personIter.next();
                 System.out.println( p );
                 System.out.print( "   Founder of: " );
                 clubIter = objectLayer.findFounderFoundedClub( p );
                 while( clubIter != null && clubIter.hasNext() ) {
                     Club c = clubIter.next();
                     System.out.print( c + " " );
                 }
                 System.out.println();System.out.flush();
                 System.out.println( "   Member of: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setPerson( p );
                 Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getClub() );
                 }
             }

         }
         catch( ClubsException ce)
         {
             System.err.println( "ClubsException: " + ce );
         }
         catch( Exception e)
         {
             System.out.flush();
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

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
         
         Iterator<Club> clubIter = null;
                  
         try {
             
             Club tennisClub = null;
             Club modelClub = objectLayer.createClub();
             modelClub.setName( "Tennis" );
             clubIter = objectLayer.findClub( modelClub );
             while( clubIter.hasNext() ) {
                 tennisClub = clubIter.next();
                 System.out.println( tennisClub );
                 Person founder = objectLayer.findFounderFoundedClub( tennisClub );
                 System.out.println( "   Founded by: " + founder );
                 System.out.println( "   Members: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setClub( tennisClub );
                 Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getPerson() );
                 }
             }
             
             // modify the name of the Tennis club to Advanced Tennis
             tennisClub.setName( "Advanced Tennis" );
             objectLayer.storeClub( tennisClub );
             System.out.println( "Updated the name of the Tenis club to Advanced Tennis" );
             
             Person marySwift = null;
             Person modelPerson = objectLayer.createPerson();
             modelPerson.setFirstName( "Mary" );
             modelPerson.setLastName( "Swift" );
             Iterator<Person> personIter = objectLayer.findPerson( modelPerson );
             while( personIter.hasNext() ) {
                 marySwift = personIter.next();
                 System.out.println( marySwift );
                 System.out.println( "   Founder of: " );
                 clubIter = objectLayer.findFounderFoundedClub( marySwift );
                 while( clubIter != null && clubIter.hasNext() ) {
                     Club c = clubIter.next();
                     System.out.print( c + " " );
                 }
                 System.out.println();
                 System.out.println( "   Member of: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setPerson( marySwift );
                 Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getClub() );
                 }
             }
             
             // modify the name of the Tennis club to "Advanced Tennis"
             marySwift.setPhone( "(111) 123-4567" );
             objectLayer.storePerson( marySwift );
             System.out.println( "Updated the phone number of Mary Swift to (111) 123-4567" );

         }
         catch( ClubsException ce)
         {
             System.err.println( "ClubsException: " + ce );
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

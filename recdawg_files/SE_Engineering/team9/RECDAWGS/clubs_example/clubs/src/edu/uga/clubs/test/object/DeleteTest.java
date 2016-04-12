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

public class DeleteTest
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
             System.err.println( "DeleteTest: Unable to obtain a database connection" );
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( conn, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence ); 
         
         Iterator<Club> clubIter = null;
                  
         try {
             
             // find the Running club
             Club runningClub = null;
             Club modelClub = objectLayer.createClub();
             modelClub.setName( "Running" );
             clubIter = objectLayer.findClub( modelClub );
             while( clubIter.hasNext() ) {
                 runningClub = clubIter.next();
                 System.out.println( runningClub );
                 Person founder = objectLayer.findFounderFoundedClub( runningClub );
                 System.out.println( "   Founded by: " + founder );
                 System.out.println( "   Members: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setClub( runningClub );
                 Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getPerson() );
                 }
             }
             
             // delete the Running club
             if( runningClub != null ) {
                 objectLayer.deleteClub( runningClub );
                 System.out.println( "Deleted the Running club" );
             }
             else
                 System.out.println( "Failed to find the Running club" );
             
             // find Heather Brooks
             Person heatherBrooks = null;
             Person modelPerson = objectLayer.createPerson();
             modelPerson.setFirstName( "Heather" );
             modelPerson.setLastName( "Brooks" );
             Iterator<Person> personIter = objectLayer.findPerson( modelPerson );
             while( personIter.hasNext() ) {
                 heatherBrooks = personIter.next();
                 System.out.println( heatherBrooks );
                 System.out.print( "   Founder of: " );
                 clubIter = objectLayer.findFounderFoundedClub( heatherBrooks );
                 while( clubIter != null && clubIter.hasNext() ) {
                     Club c = clubIter.next();
                     System.out.print( c + " " );
                 }
                 System.out.println();
                 System.out.println( "   Member of: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setPerson( heatherBrooks );
                 Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getClub() );
                 }
             }
             
             // delete Heather Brooks
             if( heatherBrooks != null ) {
                 objectLayer.deletePerson( heatherBrooks );
                 System.out.println( "Deleted the Heather Brooks" );
             }
             else
                 System.out.println( "Failed to find Heather Brooks" );
             
             // remove membership of Robert Wilson in the Bridge club
             // locate the Bridge club
             Club bridgeClub = null;
             modelClub = objectLayer.createClub();
             modelClub.setName( "Bridge" );
             clubIter = objectLayer.findClub( modelClub );
             while( clubIter.hasNext() ) {
                 bridgeClub = clubIter.next();
                 System.out.println( bridgeClub );
             }
             
             if( bridgeClub == null )
                 System.out.println( "Failed to find the Bridge club" );
             
             // locate the Robert Wilson
             Person robertPerson = null;
             modelPerson = objectLayer.createPerson();
             modelPerson.setFirstName( "Robert" );
             modelPerson.setLastName( "Wilson" );
             personIter = objectLayer.findPerson( modelPerson );
             while( personIter.hasNext() ) {
                 robertPerson = personIter.next();
                 System.out.println( robertPerson );
             }
             
             if( robertPerson == null )
                 System.out.println( "Failed to find Robert Wilson person" );
             
             // locate the Membership record of Robert Wilson in the Bridge club
             if( bridgeClub != null && robertPerson != null ) {
                 Membership robertInbridgeMembership = null;
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setClub( bridgeClub );
                 modelMembership.setPerson( robertPerson );
                 Iterator<Membership> membershipIter = objectLayer.findMembership( modelMembership );
                 while( membershipIter.hasNext() ) {
                     robertInbridgeMembership = membershipIter.next();
                     System.out.println( robertInbridgeMembership );
                 }

                 // delete the Membership record of Robert Wilson in the Bridge club
                 if( robertInbridgeMembership != null ) {
                     objectLayer.deleteMembership( robertInbridgeMembership );
                     System.out.println( "Deleted the membership of Robert Wilson in the Bridge club" );
                 }
                 else
                     System.out.println( "Failed to find membership of Robert Wilson in the Bridge club" );
             }

         }
         catch( ClubsException ce )
         {
             System.err.println( "ClubsException: " + ce );
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

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Date;
import java.util.RDException;


import edu.uga.League.object.ObjectLayer;
import edu.uga.League.RDException;
import edu.uga.League.entity.Membership;

public class MembershipIterator implements Iterator <Membership>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public MembershipIterator( ResultSet rs, ObjectLayer objectLayer){
        this.rs = rs;
        this.objectLayer = objectLayer;
        try{
            this.more = rs.next();
        }
        catch( Exception e ) {  // just in case...                                                                                                     \
                                                                           
            throw new RecDawgsException( "MembershipIterator: Cannot create Membership iterator; root cause: " + e );
        }

    }

    public boolean hasNext(){
        return more;
    }

    public Membership next(){
        long   id;
        long   studentid;
        long   teamid;
        String firstName;
        String lastName;
        String userName;
        String password;
        String email;
        Boolean isStudent;
        String studentAddress;
        String phone;
        String teamname;
        Date   establishedOn;
        long   teamCaptainId;
        Membership membership = null;
        Student student = null;
        Team team = null;
        if( more ) {

            try {
                id = rs.getLong(1);
                studentid = rs.getLong(2);
                teamid = rs.getLong(3);
                firstName = rs.getString(4);
                lastName = rs.getString(5);
                userName = rs.getString(6);
                password = rs.getString(7);
                email = rs.getString(8);
                isStudent = rs.getBoolean(9);
                studentAddress = rs.getString(10);
                phone = rs.getString(11);
                teamname = rs.getString(12);
                establishedOn = rs.getDate(13);
                teamCaptainId = rs.getLong(14);

                more = rs.next();
            }
            catch( Exception e ) {      // just in case...
                throw new NoSuchElementException( "MembershipIterator: No next Membership object; root cause: " + e );
            }
            
            student = objectLayer.createStudent( firstName,lastName,userName, password, email,studentid, 'major?', studentAddress );
            try {
                team = objectLayer.createTeam(teamname);
                team.setId(teamid );
                membership = objectLayer.createStudentMemberOfTeam(student,team);
            }
            catch( ClubsException ce ) {
                ce.printStackTrace();
                System.out.println( ce );
            }
            
            return membership;
        }
        else {
            throw new NoSuchElementException( "MembershipIterator: No next Membership object" );
        }
    }

    public void remove(){
         throw new UnsupportedOperationException();
    }
};
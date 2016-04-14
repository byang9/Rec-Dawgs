package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Membership;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.object.ObjectLayer;


public class MembershipManager {
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public MembershipManager(Connection conn, ObjectLayer objectLayer){
    	this.conn = conn;
    	this.objectLayer = objectLayer;
    }
                                                                                                                         
    public void save(Student student, Team team) throws RDException{
        String insertMembershipSql = "insert into membership (studentid, teamid) values (?,?)";
        PreparedStatement  stmt = null;
        int inscnt;
        long membershipId;


        if( student.isPersistent() && team.isPersistent() )
            throw new RDException( "MembershipManager.save: Attempting to save a Membership with no Student or team defined" );
        if( !membership.getStudent().isPersistent() || !membership.getTeam().isPersistent() )
            throw new RDException( "MembershipManager.save: Attempting to save a Membership where either Student or Team are not persistent" );
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setLong( 1, membership.getStudent().getId() );
            stmt.setLong( 2, membership.getTeam().getId() );

            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                            membership.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new RDException( "MembershipManager.save: failed to save a Membership" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "MembershipManager.save: failed to save a Membership: " + e );
        }

	
    }

    public Iterator<Membership> restore(Membership membership) throws RDException{
         String selectMembershipSql = "select m.id, s.id, t.id, "
                                     + "s.firstName, s.lastName, s.userName, s.password, s.email, s.isStudent, s.studentAddress, s.phone, t.name,t.est, t.captain "
                                     + "from team t, membership m, student s where t.id = m.teamid and m.studentid = s.id";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        if( membership.getStudent() != null && !membership.getStudent().isPersistent() )
            throw new ClubsException( "MembershipManager.restore: the argument membership includes a non-persistent Student object" );
        if( membership.getTeam() != null && !membership.getTeam().isPersistent() )
            throw new ClubsException( "MembershipManager.restore: the argument membership includes a non-persistent Team object" ); 
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectMembershipSql );
        
        if( membership != null ) {
            if( membership.isPersistent() ) // id is unique, so it is sufficient to get a membership
                query.append( " where id = " + membership.getId() );
            else {

                if( membership.getStudent() != null ) {
                    condition.append( " and m.studentid = " + membership.getStudent().getId() ); 
                }

                if( membership.getTeam() != null ) {
                    condition.append( " and m.teamid = " + membership.getTeam().getId() ); 
                }

                if( condition.length() > 0 )
                    query.append( condition );
            }
        }
        
        try {
            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new MembershipIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RDException( "MembershipManager.restore: Could not restore persistent Membership object; Root cause: " + e );
        }

        // if we reach this point, it's an error
        throw new RDException( "MembershipManager.restore: Could not restore persistent Membership object" );
    }

    public void delete(Membership membership) throws RDException{
        String               deleteMembershipSql = "delete from membership where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !membership.isPersistent() ) // is the Membership object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteMembershipSql );          
            stmt.setLong( 1, membership.getId() );
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RDException( "MembershipManager.delete: failed to delete a Membership" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "MembershipManager.delete: failed to delete a Membership: " + e );               }
    }
}



package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
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


        if( student.isPersistent() && team.isPersistent() )
            throw new RDException( "MembershipManager.save: Attempting to save a Membership with no Student or team defined" );
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setLong( 2, student.getId() );
            stmt.setLong( 3, team.getId() );

            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

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

    public Iterator<Student> restore(Team team) throws RDException {
         String selectSql = "select t.id, s.id from membership where t.id = ";            
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        if( team.isPersistent() )
            throw new RDException( "MembershipManager.restore: the argument membership includes a non-persistent Student object" );
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectSql );
        
        if( team != null ) {
            if( team.isPersistent() ) // id is unique, so it is sufficient to get a membership
                query.append( team.getId() );
        }
        
        try {
            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
            	ResultSet r = stmt.getResultSet();
                if (stmt.execute("select s.id, s.name, s.address, s.isIndoor from student s where s.id = " + r.getLong(1))) {
                	ResultSet r2 = stmt.getResultSet();
                	return new StudentIterator(r2, objectLayer);
                }
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RDException( "MembershipManager.restore: Could not restore persistent Membership object; Root cause: " + e );
        }

        // if we reach this point, it's an error
        throw new RDException( "MembershipManager.restore: Could not restore persistent Membership object" );
    }
    
    public Iterator<Team> restore(Student student) throws RDException {
        String selectSql = "select t.id, s.id from membership where s.id = ";            
       Statement    stmt = null;
       StringBuffer query = new StringBuffer( 100 );
       StringBuffer condition = new StringBuffer( 100 );

       if( student.isPersistent() )
           throw new RDException( "MembershipManager.restore: the argument membership includes a non-persistent Student object" );
       
       condition.setLength( 0 );
       
       // form the query based on the given Club object instance
       query.append( selectSql );
       
       if( student != null ) {
           if( student.isPersistent() ) // id is unique, so it is sufficient to get a membership
               query.append( student.getId() );
       }
       
       try {
           stmt = conn.createStatement();

           // retrieve the persistent Person object
           //
           if( stmt.execute( query.toString() ) ) { // statement returned a result
           	ResultSet r = stmt.getResultSet();
               if (stmt.execute("select s.id, s.name, s.address, s.isIndoor from student s where s.id = " + r.getLong(1))) {
               	ResultSet r2 = stmt.getResultSet();
               	return new TeamIterator(r2, objectLayer);
               }
           }
       }
       catch( Exception e ) {      // just in case...
           throw new RDException( "MembershipManager.restore: Could not restore persistent Membership object; Root cause: " + e );
       }

       // if we reach this point, it's an error
       throw new RDException( "MembershipManager.restore: Could not restore persistent Membership object" );
   }

    public void delete(Student student, Team team) throws RDException{
        String               deleteMembershipSql = "delete from membership where studentid = ? and teamid = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !student.isPersistent() || !team.isPersistent() ) // is the Membership object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteMembershipSql );          
            stmt.setLong( 1, student.getId() );
            stmt.setLong( 2, team.getId() );
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



package edu.uga.clubs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.object.ObjectLayer;

public class MembershipManager
{
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;
    
    public MembershipManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(Membership membership) 
            throws ClubsException
    {
        String               insertMembershipSql = "insert into membership ( personid, clubid, joined ) values ( ?, ?, ? )";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( membership.getPerson() == null || membership.getClub() == null )
            throw new ClubsException( "MembershipManager.save: Attempting to save a Membership with no Person or Club defined" );
        if( !membership.getPerson().isPersistent() || !membership.getClub().isPersistent() )
            throw new ClubsException( "MembershipManager.save: Attempting to save a Membership where either Person or Club are not persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setLong( 1, membership.getPerson().getId() );
            stmt.setLong( 2, membership.getClub().getId() );

            if( membership.getJoinedOn() != null ) {
                java.util.Date jDate = membership.getJoinedOn();
                java.sql.Date sDate = new java.sql.Date( jDate.getTime() );
                stmt.setDate( 3, sDate );
            }
            else
                stmt.setNull(3, java.sql.Types.DATE);
            
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
                throw new ClubsException( "MembershipManager.save: failed to save a Membership" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new ClubsException( "MembershipManager.save: failed to save a Membership: " + e );
        }

    }

    public Iterator<Membership> restore(Membership membership) 
            throws ClubsException
    {
        String selectMembershipSql = "select c.name, c.address, c.established, c.founderid, m.id, m.clubid, m.personid, m.joined, "
                                     + "p.username, p.userpass, p.email, p.firstname, p.lastname, p.address, p.phone "
                                     + "from club c, membership m, person p where c.id = m.clubid and m.personid = p.id";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        if( membership.getPerson() != null && !membership.getPerson().isPersistent() )
            throw new ClubsException( "MembershipManager.restore: the argument membership includes a non-persistent Person object" );
        if( membership.getClub() != null && !membership.getClub().isPersistent() )
            throw new ClubsException( "MembershipManager.restore: the argument membership includes a non-persistent Club object" ); 
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectMembershipSql );
        
        if( membership != null ) {
            if( membership.isPersistent() ) // id is unique, so it is sufficient to get a membership
                query.append( " where id = " + membership.getId() );
            else {

                if( membership.getPerson() != null ) {
                    condition.append( " and m.personid = " + membership.getPerson().getId() ); 
                }

                if( membership.getClub() != null ) {
                    condition.append( " and m.clubid = " + membership.getClub().getId() ); 
                }
                
                if( membership.getJoinedOn() != null ) {
                    // fix the date conversion
                    condition.append( " and m.joined = '" + membership.getJoinedOn() + "'" );
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
            throw new ClubsException( "MembershipManager.restore: Could not restore persistent Membership object; Root cause: " + e );
        }

        // if we reach this point, it's an error
        throw new ClubsException( "MembershipManager.restore: Could not restore persistent Membership object" );
    }

    public void delete(Membership membership) 
            throws ClubsException
    {
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
                throw new ClubsException( "MembershipManager.delete: failed to delete a Membership" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new ClubsException( "MembershipManager.delete: failed to delete a Membership: " + e );        }
    }
}

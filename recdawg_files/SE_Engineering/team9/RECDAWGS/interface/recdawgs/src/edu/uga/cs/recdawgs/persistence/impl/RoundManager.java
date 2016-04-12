package edu.uga.clubs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Round;
import edu.uga.cs.recdawgs.object.ObjectLayer;

public class RoundManager
{
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;
    
    public RoundManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(Round round) throws RDException
    {
        String               insertRoundSql = "insert into round ( leagueID,Number ) values ( ?, ?)";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 roundId;
        
        if( round.getLeague().getId() == null)
            throw new ClubsException( "RoundManager.save: Attempting to save a Round with no League defined" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertRoundSql );
            
            if (round.getLeague().getId() != null) // league id
                stmt.setLong(1, round.getLeague().getId());
            else 
                throw new RDException("RoundManager.save: can't save a Round: league ID undefined");
            if (round.getRoundNum() != null) // round number
                stmt.setLong(2, round.getRoundNum());
            else 
                throw new RDException("RoundManager.save: can't save a Round: Round number undefined");
            
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
                        roundId = r.getLong( 1 );
                        if( roundId > 0 )
                            round.setNumber( roundId ); // set this roudnmanager's db id (proxy object)
                    }
                }
            }
            else
                throw new RDException( "RoundManager.save: failed to save a Round" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "RoundManager.save: failed to save a Round: " + e );
        }

    }

    public Iterator<Round> restore(Round round) 
            throws RDException
    {
        String selectRoundSql = "select r.leagueid r.number";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectRoundSql );
        
        if( round != null ) {
            if( round.isPersistent() ) // id is unique, so it is sufficient to get a round
                query.append( " where id = " + round.getNumber() );
            else {

                if( round.getLeague().getId() != null ) {
                    condition.append( " and m.personid = " + round.getLeague().getId()); 
                }
            }
        }
        
        try {
            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new RoundIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RDException( "RoundManager.restore: Could not restore persistent Round object; Root cause: " + e );
        }

        // if we reach this point, it's an error
        throw new RDException( "RoundManager.restore: Could not restore persistent Round object" );
    }

    public void delete(Round round) 
            throws RDException
    {
        String               deleteRoundSql = "delete from round where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !round.isPersistent() ) // is the Round object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteRoundSql );          
            stmt.setLong( 1, round.getNumber() );
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RDException( "RoundManager.delete: failed to delete a Round" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RDException( "RoundManager.delete: failed to delete a Round: " + e );        }
    }
}

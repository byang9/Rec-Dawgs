package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.League;
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
        
        if( round.getLeague() == null)
            throw new RDException( "RoundManager.save: Attempting to save a Round with no League defined" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertRoundSql );
            
            if (round.getLeague() != null) // league id
                stmt.setLong(1, round.getLeague().getId());
            else 
                throw new RDException("RoundManager.save: can't save a Round: league ID undefined");
            
            stmt.setLong(2, round.getNumber());
            
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
                            round.setNumber( (int)roundId ); // set this roudnmanager's db id (proxy object)
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
    
    public void save(League league, Round round) throws RDException{
        String insertTeamSql = "insert into round ( leagueid ) values ( ? )";              
        String updateTeamSql = "update team  set leagueId = ?";          
        PreparedStatement stmt;

        try{

            if(!league.isPersistent() || !round.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement (insertTeamSql);
            else
                stmt = (PreparedStatement) conn.prepareStatement (updateTeamSql);

            if(league != null)
                stmt.setLong(1, league.getId());
                
            stmt.executeUpdate();
            
        } catch (Exception e) {
        	throw new RDException("RoundManager.save: can't save a League/Round Association: league undefined");
        }
    }//save
    

    public Iterator<Round> restore(Round round) 
            throws RDException
    {
        String selectRoundSql = "select r.leagueid, r.roundNo from round r";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectRoundSql );
        
        if( round != null ) {
            if( round.isPersistent() ) // id is unique, so it is sufficient to get a round
                query.append( " where r.id = " + round.getNumber() );
            else {

                if( round.getLeague() != null ) {
                    condition.append( " where r.leagueid = " + round.getLeague().getId()); 
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
    
    public Iterator<Round> restore(League league) throws RDException {
    	
        String selectRoundSql = "select l.id, l.name, r.leagueid r.number from league l, round r";         
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        
        condition.setLength( 0 );
        
        // form the query based on the given Club object instance
        query.append( selectRoundSql );
        
        if( league != null ) {
            if( league.getId() >= 0 ) // id is unique, so it is sufficient to get a round
                query.append( " where l.id = " + league.getId() );
            else {

                if( league.getName() != null ) {
                    condition.append( " where l.name = " + league.getName()); 
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
    
    public void delete(League league, Round round){
        String               deleteTeamLeagueSql = "update round set leagueid = null where id = " + league.getId();              
        PreparedStatement    stmt = null;
        
        // form the query based on the given Team object instance
        if( !round.isPersistent() ) // is the Team object persistent?  If not, nothing to actually delete
            return;
        
        try {
            
            //DELETE t1, t2 FROM t1, t2 WHERE t1.id = t2.id;
            //DELETE FROM t1, t2 USING t1, t2 WHERE t1.id = t2.id;
            stmt = (PreparedStatement) conn.prepareStatement( deleteTeamLeagueSql );
            stmt.executeUpdate();
         
        }
        catch( SQLException e ) {
            System.out.println("Error: " + e);
        }

    }//delete
}

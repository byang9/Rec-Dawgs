package edu.uga.cs.recdawgs.session;


import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.persistence.impl.DbUtils;




/**
 * Based on the modified code from Matthew Eavenson
 * 
 * @author Matthew Eavenson
 */

/** This class provides different operations for the Sessions such as 
 *  creating new sessions, removing, login and logout.
 */
public class SessionManager
{
    /** 
     * Map for the existing sessions
     */
    private static Map<String, Session> sessions;
    
    /** 
     * Map for the currently logged-in users
     */
    private static Map<String, Session> loggedIn;
    
    private static Logger log = Logger.getLogger( SessionManager.class );
    
    static{
        sessions = new HashMap<String, Session>();
        loggedIn = new HashMap<String, Session>();
    } 
    
    public static Session createSession() 
            throws RDException 
    {
        Connection conn = null;
        Session s = null;
        
        // open a connection to the database
        try {
            conn = DbUtils.connect();
        } catch (Exception seq) {
            throw new RDException( "SessionManager.login: Unable to get a database connection" );
        }
        
        // initialize a new Session object
        // this creates PersistenceLayer, ObjectLayer, and LogicLayer instances
        // The LogicLayer reference is stored with the Session for use in other use cases later.
        s = new Session( conn );
        
        return s; 
    }
    
    public static String storeSession( Session session ) 
            throws RDException
    {
        User person = session.getUser();
        
        if( loggedIn.containsKey(person.getUserName()) ) {
            Session qs = loggedIn.get(person.getUserName());
            qs.setUser(person);
            return qs.getSessionId();
        }
                
        String ssid = secureHash( "CLUBS" + System.nanoTime() );
        session.setSessionId( ssid );
        
        sessions.put( ssid, session );
        loggedIn.put( person.getUserName(), session );
        session.start();
        return ssid;
    }
    
    /****************************************************
     * Logout of the current session (based on session)
     * @param  the session being used
     * @throws GVException
     */
    public static void logout(Session s) 
            throws RDException
    {
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    /****************************************************
     * Logout of the current session (based on session)
     * @param  ssid the session being used
     * @throws GVException
     */
    public static void logout(String ssid) 
            throws RDException
    {
        Session s = getSessionById(ssid);
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    /****************************************************
     * Remove the session
     * @param s the current session
     * @throws GVException
     */
    protected static void removeSession( Session s ) 
            throws RDException
    {
        try { 
            s.getConnection().close();
        } 
        catch( SQLException sqe ) { 
            log.error( "SessionManager.removeSession: cannot close connection", sqe );
            throw new RDException( "SessionManager.removeSession: Cannot close connection" );
        } // try
        sessions.remove( s.getSessionId() );
        loggedIn.remove( s.getUser().getUserName() );
    }
    
    /****************************************************
     * Get the session by session id
     * @param ssid the current session id
     */
    public static Session getSessionById( String ssid ){
        return sessions.get( ssid );
    }
    
    /*********************************************************************
     * Hashes the string input using the SHA1 algorithm.
     * @param   input   string to hash.
     * @return  SHA hash of the string.
     * @throws RDException 
     */
    public static String secureHash( String input ) 
            throws RDException
    {
        StringBuilder output = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance( "SHA" );
            md.update( input.getBytes() );
            byte[] digest = md.digest();
            for( int i = 0; i < digest.length; i++ ) {
                String hex = Integer.toHexString( digest[i] );
                if( hex.length() == 1 )
                    hex = "0" + hex;
                hex = hex.substring( hex.length() - 2 );
                output.append( hex );
            }
        }
        catch( Exception e ) {
            log.error(
                    "SessionManager.secureHash: Invalid Encryption Algorithm", e );
            throw new RDException(
                    "SessionManager.secureHash: Invalid Encryption Algorithm" );
        }
        return output.toString();
    }
    
    /**********************************************************************
     * Return the logger object. 
     * @return Logger object. 
     * @author Arsham Mesbah
     */
    public static Logger getLog()
    {
        return log; 
    }

}

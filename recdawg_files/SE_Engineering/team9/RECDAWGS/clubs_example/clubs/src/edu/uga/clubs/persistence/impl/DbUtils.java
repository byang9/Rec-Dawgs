package edu.uga.clubs.persistence.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.session.SessionManager;

import org.apache.log4j.Logger;

/**
 * This class performs database operations.
 * @author durandal, Haseeb Yousaf
 */
public class DbUtils {

    private static Logger log = SessionManager.getLog();
    

    /**
     * Disables the auto commit for the current connection  
     * @param conn  the open connection
     */
    public static void disableAutoCommit( Connection conn ) 
            throws ClubsException 
    {
        try {
            conn.setAutoCommit(false);
        } 
        catch( SQLException ex ) {
            log.error( "DbUtils.disableAutoCommit: SQLException on setting auto-commit false ", ex );
            throw new ClubsException( "DbUtils.disableAutoCommit: Transaction error. " + ex.getMessage() );
        }
    }

    /**
     * Enable the auto commit for the current connection  
     * @param conn  the open connection
     */
    public static void enableAutoCommit( Connection conn ) 
            throws ClubsException 
    {
        try {
            conn.setAutoCommit(true);
        } 
        catch( SQLException ex ) {
            log.error( "DbUtils.enableAutoCommit: SQLException on setting auto-commit true ", ex );
            throw new ClubsException( "DbUtils.enableAutoCommit: Transaction error. " + ex.getMessage() );
        }
    }

    /**
     * Commit the transactions for the current connection  
     * @param conn  the open connection
     */
    public static void commit(Connection conn) 
            throws ClubsException 
    {
        try {
            conn.commit();
        } catch (SQLException ex) {
            log.error("DbUtils.commit: SQLException on commit ", ex);
            throw new ClubsException( "DbUtils.commit: SQLException on commit " + ex.getMessage() );
        }
    }

    /**
     * rollback the transactions for the current connection  
     * @param conn  the open connection
     */
    public static void rollback(Connection conn) 
            throws ClubsException 
    {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            log.error( "DbUtils.rollback: SQLException on rollback ", ex );
            throw new ClubsException( "DbUtils.rollback: Unable to rollback transaction. " + ex.getMessage() );
        }
    }

    /**
     * Establish a JDBC connection to the database.
     * @return  a database connection object
     * @throws  GVException
     */
    public static Connection connect() 
            throws ClubsException 
    {
        try {
            Class.forName(DbAccessConfig.DB_DRIVE_NAME);
        } 
        catch (ClassNotFoundException ex) {
            log.error( "DbUtils.connect:  unable to find JDBC Driver", ex );
            throw new ClubsException( "DbUtils.connect: Unable to find Driver" );
        }
        try {
            return DriverManager.getConnection( DbAccessConfig.DB_CONNECTION_URL,
                                                DbAccessConfig.DB_CONNECTION_USERNAME,
                                                DbAccessConfig.DB_CONNECTION_PWD );
        } 
        catch (SQLException ex) {
            log.error( "DbUtils.connect: Unable to connect to database", ex );
            throw new ClubsException( "DbUtils.connect: Unable to connect to database " + ex.getMessage() );
        }
    }

}
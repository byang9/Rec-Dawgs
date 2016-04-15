package edu.uga.cs.recdawgs.persistence.impl;


 /* This class defines configuration parameters for accessing a database using JDBC.
 */
public abstract class DbAccessConfig
{
    /** The fully qualified name of the JDBC driver.
     */
    static final String DB_DRIVE_NAME  = "com.mysql.jdbc.Driver";
    
    /** The database name
     */
    static final String DB_NAME        = "team9";
    
    /** The database server name for the connection pool
     */
    static final String DB_SERVER_NAME = "uml.cs.uga.edu";

    /** The JDBC connection string/URL.
     */
    //static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/clubs";
    static final String DB_CONNECTION_URL = "jdbc:mysql://uml.cs.uga.edu/team9";
    
    /** The database user name.
     */
    static  String DB_CONNECTION_USERNAME = "team9";

    /** The password for the database user.
     */
    static  String DB_CONNECTION_PWD = "oomethods";

}


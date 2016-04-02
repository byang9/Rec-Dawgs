package edu.uga.clubs.persistence.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;

class PersonManager
{
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;
    
    public PersonManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save( Person person ) 
            throws ClubsException
    {
        String               insertPersonSql = "insert into person ( username, userpass, email, firstname, lastname, address, phone ) values ( ?, ?, ?, ?, ?, ?, ? )";              
        String               updatePersonSql = "update person  set username = ?, userpass = ?, email = ?, firstname = ?, lastname = ?, address = ?, phone = ? where id = ?";              
        PreparedStatement    stmt;
        int                  inscnt;
        long                 personId;
        
        try {
            
            if( !person.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertPersonSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updatePersonSql );

            if( person.getUserName() != null ) // clubsuser is unique, so it is sufficient to get a person
                stmt.setString( 1, person.getUserName() );
            else 
                throw new ClubsException( "PersonManager.save: can't save a Person: userName undefined" );

            if( person.getPassword() != null )
                stmt.setString( 2, person.getPassword() );
            else
                throw new ClubsException( "PersonManager.save: can't save a Person: password undefined" );

            if( person.getEmail() != null )
                stmt.setString( 3,  person.getEmail() );
            else
                throw new ClubsException( "PersonManager.save: can't save a Person: email undefined" );

            if( person.getFirstName() != null )
                stmt.setString( 4, person.getFirstName() );
            else
                throw new ClubsException( "PersonManager.save: can't save a Person: first name undefined" );

            if( person.getLastName() != null )
                stmt.setString( 5, person.getLastName() );
            else
                throw new ClubsException( "PersonManager.save: can't save a Person: last name undefined" );

            if( person.getAddress() != null )
                stmt.setString( 6, person.getAddress() );
            else
                stmt.setNull(6, java.sql.Types.VARCHAR);

            if( person.getPhone() != null )
                stmt.setString( 7,  person.getPhone() );
            else
                stmt.setNull(7, java.sql.Types.VARCHAR);
            
            if( person.isPersistent() )
                stmt.setLong( 8, person.getId() );

            inscnt = stmt.executeUpdate();

            if( !person.isPersistent() ) {
                // in case this this object is stored for the first time,
                // we need to establish its persistent identifier (primary key)
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result
                        // retrieve the result
                        ResultSet r = stmt.getResultSet();
                        // we will use only the first row!
                        while( r.next() ) {
                            // retrieve the last insert auto_increment value
                            personId = r.getLong( 1 );
                            if( personId > 0 )
                                person.setId( personId ); // set this person's db id (proxy object)
                        }
                    }
                }
            }
            else {
                if( inscnt < 1 )
                    throw new ClubsException( "PersonManager.save: failed to save a Person" ); 
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new ClubsException( "PersonManager.save: failed to save a Person: " + e );
        }
    }

    public Iterator<Person> restore( Person modelPerson ) 
            throws ClubsException
    {
        String       selectPersonSql = "select id, username, userpass, email, firstname, lastname, address, phone from person";              
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Person object instance
        query.append( selectPersonSql );
        
        if( modelPerson != null ) {
            if( modelPerson.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " where id = " + modelPerson.getId() );
            else if( modelPerson.getUserName() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " where username = '" + modelPerson.getUserName() + "'" );
            else {
                if( modelPerson.getPassword() != null )
                    condition.append( " password = '" + modelPerson.getPassword() + "'" );

                if( modelPerson.getEmail() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " email = '" + modelPerson.getEmail() + "'" );
                }

                if( modelPerson.getFirstName() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " firstName = '" + modelPerson.getFirstName() + "'" );
                }

                if( modelPerson.getLastName() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " lastName = '" + modelPerson.getLastName() + "'" );
                }

                if( modelPerson.getAddress() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " address = '" + modelPerson.getAddress() + "'" );
                }        

                if( modelPerson.getPhone() != null ) {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " phone = '" + modelPerson.getPhone() + "'" );
                }

                if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }
            }
        }
        
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new PersonIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new ClubsException( "PersonManager.restore: Could not restore persistent Person object; Root cause: " + e );
        }
        
        // if we get to this point, it's an error
        throw new ClubsException( "PersonManager.restore: Could not restore persistent Person object" );
    }
    
    public Iterator<Club> restoreEstablishedBy( Person person ) 
            throws ClubsException
    {
        String selectPersonSql = "select c.id, c.name, c.address, c.established, p.id, " +
                                 "p.username, p.userpass, p.email, p.firstname, p.lastname, p.address, " +
                                 "p.phone from club c, person p where c.founderid = p.id";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );

        condition.setLength( 0 );
        
        // form the query based on the given Person object instance
        query.append( selectPersonSql );
        
        if( person != null ) {
            if( person.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " and p.id = " + person.getId() );
            else if( person.getUserName() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " and p.username = '" + person.getUserName() + "'" );
            else {
                if( person.getPassword() != null )
                    condition.append( " p.password = '" + person.getPassword() + "'" );

                if( person.getEmail() != null && condition.length() == 0 )
                    condition.append( " p.email = '" + person.getEmail() + "'" );
                else
                    condition.append( " AND p.email = '" + person.getEmail() + "'" );

                if( person.getFirstName() != null && condition.length() == 0 )
                    condition.append( " p.firstname = '" + person.getFirstName() + "'" );
                else
                    condition.append( " AND p.firstname = '" + person.getFirstName() + "'" );

                if( person.getLastName() != null && condition.length() == 0 )
                    condition.append( " p.lastname = '" + person.getLastName() + "'" );
                else
                    condition.append( " AND p.lastname = '" + person.getLastName() + "'" );

                if( person.getAddress() != null && condition.length() == 0 )
                    condition.append( " p.address = '" + person.getAddress() + "'" );
                else
                    condition.append( " AND p.address = '" + person.getAddress() + "'" );         

                if( person.getPhone() != null && condition.length() == 0 )
                    condition.append( " p.phone = '" + person.getPhone() + "'" );
                else
                    condition.append( " AND p.phone = '" + person.getPhone() + "'" );
                
                if( condition.length() > 0 ) {
                    query.append( condition );
                }
            }
        }
                
        try {

            stmt = conn.createStatement();

            // retrieve the persistent Person object
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet r = stmt.getResultSet();
                return new ClubIterator( r, objectLayer );
            }
        }
        catch( Exception e ) {      // just in case...
            throw new ClubsException( "PersonManager.restoreEstablishedBy: Could not restore persistent Club objects; Root cause: " + e );
        }

        throw new ClubsException( "PersonManager.restoreEstablishedBy: Could not restore persistent Club objects" );
    }
    
    public void delete( Person person ) 
            throws ClubsException
    {
        String               deletePersonSql = "delete from person where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        // form the query based on the given Person object instance
        if( !person.isPersistent() ) // is the Person object persistent?  If not, nothing to actually delete
            return;
        
        try {
            
            //DELETE t1, t2 FROM t1, t2 WHERE t1.id = t2.id;
            //DELETE FROM t1, t2 USING t1, t2 WHERE t1.id = t2.id;
            stmt = (PreparedStatement) conn.prepareStatement( deletePersonSql );
            
            stmt.setLong( 1, person.getId() );
            
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 0 ) {
                throw new ClubsException( "PersonManager.delete: failed to delete this Person" );
            }
        }
        catch( SQLException e ) {
            throw new ClubsException( "PersonManager.delete: failed to delete this Person: " + e.getMessage() );
        }
    }
}

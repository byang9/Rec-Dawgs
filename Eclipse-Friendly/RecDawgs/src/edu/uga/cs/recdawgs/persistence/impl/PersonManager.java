package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class PersonManager {
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public PersonManager(Connection conn, ObjectLayer objectLayer){
    	this.conn = conn;
    	this.objectLayer = objectLayer;
    }

 
    public void save(Student user) throws RDException{
        String insertPersonSql = "insert into person ( firstname, lastname, username, password, email, isStudent, studentID, address) values ( ?, ?, ?, ?, ?, ?, ?, ? )";              
        String updatePersonSql = "update person set firstname = ?, lastname = ?, username = ?, password = ?, email = ?, isStudent = ?, address = ?";              
        PreparedStatement stmt;
        int inscnt;
        long personId;


        try{
            if (!user.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement( insertPersonSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updatePersonSql );
            //first name
            if (user.getFirstName()!= null){
                stmt.setString(1, user.getFirstName());
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: firstName undefined" );
            
            //last name
            if (user.getLastName()!= null){
                stmt.setString(2, user.getLastName());
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: lastName undefined" );
            
            //get user name
            if (user.getUserName()!= null){
                stmt.setString(3, user.getUserName());
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: userName undefined" );
            
            //get password
            if (user.getPassword()!=null)
                stmt.setString(4, user.getPassword());
            else
                throw new RDException("PersonManager.save: can't save a Person: password undefined" );
            
            //get email
            if (user.getEmailAddress()!= null)
                stmt.setString(5, user.getEmailAddress());
            else
                throw new RDException("PersonManager.save: can't save a Person: email undefined" );
            
            //get isStudent
            stmt.setBoolean(6, true);
            
            //get studentId
            if (user.getStudentId() != null)
                stmt.setString(7, user.getStudentId());
            else
                throw new RDException("PersonManager.save: can't save a Person: studentId undefined");
            //get address
            if (user.getAddress() != null)
                stmt.setString(8, user.getAddress());
            else
                throw new RDException("PersonManager.save: can't save a Person: address undefined");

            if (user.isPersistent())
                stmt.setLong(1, user.getId());

            inscnt = stmt.executeUpdate();

            if (!user.isPersistent()){
                //if this person is being stored for the first time, its primary key id needs to 
                //be established
                if (inscnt == 1){
                    String sql = "select last_insert_id()";
                    if (stmt.execute(sql)){
                        ResultSet r = stmt.getResultSet();
                        while (r.next()){
                            personId = r.getLong( 1 );
                            if (personId > 0)
                                user.setId(personId);
                        }
                    }
                }
            }
            else{
                if (inscnt < 1)
                    throw new RDException("PersonManager.save: failed to save a Person");
            }


        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RDException("PersonManager.save: failed to save a Person: " + e );
        }

    }//save
    
    public void save(Administrator user) throws RDException{
        String insertPersonSql = "insert into person ( firstname, lastname, username, password, email, isStudent) values ( ?, ?, ?, ?, ?, ? )";              
        String updatePersonSql = "update person set firstname = ?, lastname = ?, username = ?, password = ?, email = ?, isStudent = ?";              
        PreparedStatement stmt;
        int inscnt;
        long personId;


        try{
            if (!user.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement( insertPersonSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updatePersonSql );
            //first name
            if (user.getFirstName()!= null){
                stmt.setString(1, user.getFirstName());
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: firstName undefined" );
            
            //last name
            if (user.getLastName()!= null){
                stmt.setString(2, user.getLastName());
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: lastName undefined" );
            
            //get user name
            if (user.getUserName()!= null){
                stmt.setString(3, user.getUserName());
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: userName undefined" );
            
            //get password
            if (user.getPassword()!=null)
                stmt.setString(4, user.getPassword());
            else
                throw new RDException("PersonManager.save: can't save a Person: password undefined" );
            
            //get email
            if (user.getEmailAddress()!= null)
                stmt.setString(5, user.getEmailAddress());
            else
                throw new RDException("PersonManager.save: can't save a Person: email undefined" );
            
            //get isStudent
            stmt.setBoolean(6, false);

            if (user.isPersistent())
                stmt.setLong(1, user.getId());

            inscnt = stmt.executeUpdate();

            if (!user.isPersistent()){
                //if this person is being stored for the first time, its primary key id needs to 
                //be established
                if (inscnt == 1){
                    String sql = "select last_insert_id()";
                    if (stmt.execute(sql)){
                        ResultSet r = stmt.getResultSet();
                        while (r.next()){
                            personId = r.getLong( 1 );
                            if (personId > 0)
                                user.setId(personId);
                        }
                    }
                }
            }
            else{
                if (inscnt < 1)
                    throw new RDException("PersonManager.save: failed to save a Person");
            }


        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RDException("PersonManager.save: failed to save a Person: " + e );
        }

    }//save

    public Iterator<Student> restore(Student modelPerson) throws RDException {
        String selectPersonSql = "p.firstname, p.lastname, p.username, p.password, p.email, p.isStudent, p.studentID, p.address from person p";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);

        condition.setLength(0);
        query.append( selectPersonSql );

        if ( modelPerson != null ){
            if (modelPerson.getId() >= 0)
                query.append(" where id = '" + modelPerson.getId());
            else if (modelPerson.getUserName() != null)
                query.append( " where username = '" + modelPerson.getUserName() + "'");
            else{
                if(modelPerson.getFirstName()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append(" firstname = '" + modelPerson.getFirstName() + "'");

                if(modelPerson.getLastName()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append(" lastname = '" + modelPerson.getLastName() + "'");

                if(modelPerson.getPassword()!= null)
                     if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append(" password = '" + modelPerson.getEmailAddress() + "'");

                if(modelPerson.getEmailAddress()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                    condition.append(" email = '" + modelPerson.getEmailAddress() + "'");

                if(modelPerson.getStudentId()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                    condition.append(" studentId = '" + modelPerson.getStudentId() + "'");

                if(modelPerson.getAddress() != null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                condition.append(" address = '" + modelPerson.getAddress() + "'");

                
            }

        }
        try{
            stmt = conn.createStatement();
            // retrieve the persistent Person object
            if (stmt.execute (query.toString())){
                ResultSet r = stmt.getResultSet();
                return new StudentIterator( r, objectLayer);
            }
        }
        catch(Exception e){
        	System.out.println(selectPersonSql);
            throw new RDException( "PersonManager.restore: Could not restore persistent Person object; Root cause: " + e );
        }
        throw new RDException("PersonManager.restore: Could not restore persistent Person object" );
    }

    public Iterator<Administrator> restore(Administrator modelPerson) throws RDException {
        String selectPersonSql = "u.firstname, u.lastname, u.username, u.password, u.email, u.isStudent from person u";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);

        condition.setLength(0);
        query.append( selectPersonSql );

        if ( modelPerson != null ){
            if (modelPerson.getId() >= 0)
                query.append(" where id = '" + modelPerson.getId());
            else if (modelPerson.getUserName() != null)
                query.append( " where username = '" + modelPerson.getUserName() + "'");
            else{
                if(modelPerson.getFirstName()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append(" firstname = '" + modelPerson.getFirstName() + "'");

                if(modelPerson.getLastName()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append(" lastname = '" + modelPerson.getLastName() + "'");

                if(modelPerson.getPassword()!= null)
                     if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append(" password = '" + modelPerson.getEmailAddress() + "'");

                if(modelPerson.getEmailAddress()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                    condition.append(" email = '" + modelPerson.getEmailAddress() + "'");

                if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }
                
            }

        }
        try{
            stmt = conn.createStatement();
            // retrieve the persistent Person object
            if (stmt.execute (query.toString())){
                ResultSet r = stmt.getResultSet();
                return new AdministratorIterator( r, objectLayer);
            }
        }
        catch(Exception e){
            throw new RDException( "PersonManager.restore: Could not restore persistent Person object; Root cause: " + e );
        }
        throw new RDException("PersonManager.restore: Could not restore persistent Person object" );
    }


    public void delete(User person) throws RDException{
        String  deletePersonSql = "delete from person where id = ?";
        PreparedStatement  stmt = null;
        int inscnt;

        if (!person.isPersistent())
            return;

        try{
            stmt = (PreparedStatement) conn.prepareStatement(deletePersonSql);

            stmt.setLong(1, person.getId() );

            inscnt = stmt.executeUpdate();

            if (inscnt == 0){
                throw new RDException("PersonManager.delete: failed to delete this Person");
            }
        }
        catch (SQLException e ){
            throw new RDException("PersonManager.delete: failed to delete this Person: " + e.getMessage());
        }

    }


}

package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.User;
import edu.uga.cs.recdawgs.object.ObjectLayer;



public class PersonManager {
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public PersonManager(Connection conn, ObjectLayer objectLayer){
    	this.conn = conn;
    	this.objectLayer = objectLayer;
    }

 
    public void save(User user) throws RDException{
        String insertPersonSql = "insert into person ( firstname, lastname, username, password, email, isStudent, studentID, address, phone) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";              
        String updatePersonSql = "update person  set firstname = ?, lastname = ?, username = ?, password = ?, email = ?, isStudent = ?, address = ? phone = ?";              
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
            
            /*
            //get isStudent
            if (user.isStudent() != null)
                stmt.setString(6, user.getIsStudent());
            else
                throw new RDException("PersonManager.save: can't save a Person: isStudent undefined");
            //get studentId
            if (person.studentID != null)
                stmt.setString(7, person.getStudentId());
            else
                throw new RDException("PersonManager.save: can't save a Person: studentId undefined");
            //get address
            if (person.getAddress != null)
                stmt.setString(8, person.getAddress());
            else
                throw new RDException("PersonManager.save: can't save a Person: address undefined");
            //get phone
            if (person.getPhone != null)
                stmt.setString(9, person.getPhone());
            else
                throw new RDException("PersonManager.save: can't save a Person: phone undefined");
                */

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

    public Iterator<User> restore(User modelPerson) throws RDException {
        String selectPersonSql = "u.firstname, u.lastname, u.username, u.password, u.email, u.isStudent, u.studentID, u.address, u.phone";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);

        condition.setLength(0);
        query.append( selectPersonSql );

        if ( modelPerson != null ){
            if (modelPerson.getId() > = 0)
                qury.append(" where username = '" + modelPerson.getId());
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
                    condition.append(" password = '" + modelPerson.getEmail() + "'");

                if(modelPerson.getEmail()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                    condition.append(" email = '" + modelPerson.getEmail() + "'");

                if(modelPerson.getIsStudent != null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                    condition.append(" isStudent '" + modelPerson.getIsStudent() + "'" );

                if(modelPerson.getStudentId()!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                    condition.append(" studentId = '" + modelPerson.getStudentId() + "'");

                if(modelPerson.getAddress!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );

                    condition.append(" address = '" + modelPerson.getAddress() + "'");

                if(modelPerson.getPhone!= null)
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append(" phone = '" + modelPerson.getPhone() + "'");

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
                return new PersonIterator( r, objectLayer);
            }
        }
        catch(Exception e){
            throw new RDException( "PersonManager.restore: Could not restore persistent Person object; Root cause: " + e )
        }
        throw new RDException("PersonManager.restore: Could not restore persistent Person object" )
    }




    public void delete(Person person) throws RDException{
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
            throw new RDException("PersonManager.delete: failed to delete this Person: " + e.getMessage())
        }

    }


}

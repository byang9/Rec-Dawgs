package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

package edu.uga.cs.recdawgs.RDException;

package edu.uga.cs.recdawgs.entity.Person;
package edu.uga.cs.recdawgs.object.ObjectLayer;



public class PersonManager(){
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public PersonManager(Connection conn, ObjectLayer objectLayer){
    	this.conn = conn;
    	this.objectLayer = objectLayer;
    }

    //TODO throws Exception
    public void save(Person person) throws RDException{
        String               insertPersonSql = 
        "insert into person ( firstname, lastname, username, password, email, isStudent, studentID, address, phone) 
        values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";              
        String               updatePersonSql = 
        "update person  set firstname = ?, lastname = ?, username = ?, password = ?, email = ?, isStudent = ?, address = ? phone = ?";              
        PreparedStatement    stmt;
        int                  inscnt;
        long                 personId;


        try{
            if (!person.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement( insertPersonSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updatePersonSql );
            //first name
            if (person.geFirstName()!= null){
                stmt.setString(1, personGetUserName("PersonManager.save: can't save a Person: first name undefined"));
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: last name undefined" );
            //last name
            if (person.getLastName()!= null){
                stmt.setString(2, personGetUserName("PersonManager.save: can't save a Person: userName undefined"));
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: userName undefined" );
            //get user name
            if (person.getUserName()!= null){
                stmt.setString(3, personGetUserName("PersonManager.save: can't save a Person: userName undefined"));
            }
            else
                throw new RDException("PersonManager.save: can't save a Person: userName undefined" );
            //get password
            if (person.getPassword()!=null)
                stmt.setString(4, person.getPassword()));
            else
                throw new RDException("PersonManager.save: can't save a Person: password undefined" );
            //get email
            if (person.getEmail()!= null)
                stmt.setString(5, person.getEmail());
            else
                throw new RDException("PersonManager.save: can't save a Person: email undefined" );
            //get isStudent
            if (person.getIsStudent != null)
                stmt.setString(6, person.getIsStudent());
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

            //TODO
            if (person.isPersistent())
                stmt.setLong(8, person.get(id));

            inscnt = stmt.executeUpdate();

            if (!person.isPersistent()){
                //if this person is being stored for the first time, its primary key id needs to 
                //be established
                if (inscnt == 1){
                    String sql = "select last_insert_id()";
                    if (stmt.execute(sql)){
                        ResultSet r = stmt.getResultSet();
                        while (r.next()){
                            personId = r.getLong( 1 );
                            if (personId > 0)
                                person.setId(personId);
                        }
                    }
                }
            }
            else{
                if (inscnt < 1)
                    throw new ClubsException("PersonnManager.save: failed to save a Person")
            }


        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RDException("PersonManager.save: failed to save a Person: " + e );
        }

    }//save

    public Iterator<Person> restore(Person modelPerson) throws RDException {
        String selectPersonSql = "firstname, lastname, username, password, email, isStudent, studentID, address, phone"
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

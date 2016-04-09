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
            //get address
            if (person.getPhone != null)
                stmt.setString(9, person.getPhone());
            else
                throw new RDException("PersonManager.save: can't save a Person: phone undefined");

            //TODO


        }

    }//save

    public Iterator<Person> restore(Person person){

    }

    public void delete(Person person){

    }


}
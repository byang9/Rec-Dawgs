package edu.uga.cs.recdawgs.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Membership;
import edu.uga.cs.recdawgs.object.ObjectLayer;


public class MembershipManager(){
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public MembershipManager(Connection conn, ObjectLayer objectLayer){
    	this.conn = conn;
    	this.objectLayer = objectLayer;
    }
                                                                                                                         
    public void save(Membership membership) throws RDException{
        String insertMembershipSql = "insert into membership (firstname, lastname, username, password,
                email, isStudent, studentID, address, phone"
        PreparedStatement  stmt = null;
        int inscnt;
        long membershipId;


	
    }

    public Iterator<Person> restore(Membership membership) throws RDException{

    }

    public void delete(Membership membership) throws RDException{
         
    }


}


'''
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstname       VARCHAR(255) NOT NULL,
    lastname        VARCHAR(255) NOT NULL,
    username        VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    isStudent       BOOLEAN NOT NULL,
    studentID       INT UNSIGNED,
    address         VARCHAR(255),
    phone           VARCHAR(255)
    '''
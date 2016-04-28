package edu.uga.cs.recdawgs.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;

public class LoginCtrl
{ 
    private ObjectLayer objectLayer = null;
    
    public LoginCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    
    public String login( Session session, String userName, String password )
            throws RDException
    {
        String ssid = null;
        
        Student modelStudent = objectLayer.createStudent();
        modelStudent.setUserName( userName );
        modelStudent.setPassword( password );
        Iterator<Student> students = objectLayer.findStudent( modelStudent );
        if( students.hasNext() ) {
            Student student = students.next();
            if (!student.getUserName().equals(userName) || !student.getPassword().equals(password))
                throw new RDException( "SessionManager.login: Invalid User Name or Password" );
            session.setUser( student );
            ssid = SessionManager.storeSession( session );
        }
        else
            throw new RDException( "SessionManager.login: Invalid User Name or Password" );
        
        return ssid;
    }
}

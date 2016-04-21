//
// A control class to implement the 'Create student' use case
//
//

package edu.uga.cs.recdawgs.logic.impl;

import java.util.Iterator;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;


public class CreateStudentCtrl 
{
    
    private ObjectLayer objectLayer = null;
    
    public CreateStudentCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createStudent( String username, String password, String email, String firstname, String lastname, String address, String studentId, String major ) throws RDException { 
        Student               student  = null;
        Student               modelStudent  = null;
        Iterator<Student>     studentIter  = null;

        // check if the userName already exists
        modelStudent = objectLayer.createStudent();
        modelStudent.setUserName( username );
        studentIter = objectLayer.findStudent( modelStudent );
        while( studentIter.hasNext() ) {
            student = studentIter.next();
        }
        
        // check if the student actually exists, and if so, throw an exception
        if( student != null )
            throw new RDException( "A student with this user name already exists" );
        
        student = objectLayer.createStudent( firstname, lastname, username, password, email, studentId, major, address);
        objectLayer.storeStudent( student );

        return student.getId();
    }
}


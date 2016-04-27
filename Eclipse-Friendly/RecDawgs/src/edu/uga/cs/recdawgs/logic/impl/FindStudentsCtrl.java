//
// A control class to implement the 'List all students' use case
//
//


package edu.uga.cs.recdawgs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.object.ObjectLayer;




public class FindStudentsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindStudentsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Student> findAllStudents()
            throws RDException
    {
        List<Student>      students  = null;
        Iterator<Student>  studentIter = null;
        Student            student = null;

        students = new LinkedList<Student>();
        
        // retrieve all Club objects
        //
        studentIter = objectLayer.findStudent( null );
        while( studentIter.hasNext() ) {
            student = studentIter.next();
            System.out.println( student );
            students.add( student );
        }

        return students;
    }

    public Student findMyAccount(Student modelStudent)
            throws RDException
    {
        Student            student = null;
        Iterator<Student>  studentIter = null;
        
        // retrieve Student object
        //
        studentIter = objectLayer.findStudent( modelStudent );
        while( studentIter.hasNext() ) {
            student = studentIter.next();
        }

        return student;
    }
}

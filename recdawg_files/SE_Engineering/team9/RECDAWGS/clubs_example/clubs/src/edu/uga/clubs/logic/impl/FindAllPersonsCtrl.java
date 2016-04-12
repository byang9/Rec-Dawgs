//
// A control class to implement the 'List all persons' use case
//
//


package edu.uga.clubs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;




public class FindAllPersonsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindAllPersonsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Person> findAllPersons()
            throws ClubsException
    {
        List<Person>      persons  = null;
        Iterator<Person>  personIter = null;
        Person            person = null;

        persons = new LinkedList<Person>();
        
        // retrieve all Club objects
        //
        personIter = objectLayer.findPerson( null );
        while( personIter.hasNext() ) {
            person = personIter.next();
            System.out.println( person );
            persons.add( person );
        }

        return persons;
    }
}

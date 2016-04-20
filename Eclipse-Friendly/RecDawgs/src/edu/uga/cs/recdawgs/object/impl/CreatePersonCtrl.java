//
// A control class to implement the 'Create person' use case
//
//

package edu.uga.clubs.logic.impl;

import java.util.Iterator;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.object.ObjectLayer;




public class CreatePersonCtrl 
{
    
    private ObjectLayer objectLayer = null;
    
    public CreatePersonCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createPerson( String userName, String password, String email, String firstName, 
                              String lastName, String address, String phone )
          throws ClubsException
    { 
        Person               person  = null;
        Person               modelPerson  = null;
        Iterator<Person>     personIter  = null;

        // check if the userName already exists
        modelPerson = objectLayer.createPerson();
        modelPerson.setUserName( userName );
        personIter = objectLayer.findPerson( modelPerson );
        while( personIter.hasNext() ) {
            person = personIter.next();
        }
        
        // check if the person actually exists, and if so, throw an exception
        if( person != null )
            throw new ClubsException( "A person with this user name already exists" );
        
        person = objectLayer.createPerson( userName, password, email, firstName, lastName, address, phone);
        objectLayer.storePerson( person );

        return person.getId();
    }
}


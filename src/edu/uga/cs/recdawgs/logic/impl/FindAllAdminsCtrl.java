//
// A control class to implement the 'List all admins' use case
//
//


package edu.uga.cs.recdawgs.logic.impl;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uga.cs.recdawgs.RDException;
import edu.uga.cs.recdawgs.entity.Administrator;
import edu.uga.cs.recdawgs.object.ObjectLayer;




public class FindAllAdminsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindAllAdminsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Administrator> findAllAdministrators()
            throws RDException
    {
        List<Administrator>      admins  = null;
        Iterator<Administrator>  adminIter = null;
        Administrator            admin = null;

        admins = new LinkedList<Administrator>();
        
        // retrieve all Club objects
        //
        adminIter = objectLayer.findAdministrator( null );
        while( adminIter.hasNext() ) {
            admin = adminIter.next();
            System.out.println( admin );
            admins.add( admin );
        }

        return admins;
    }
}
